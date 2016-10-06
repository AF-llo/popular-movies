package de.lokaizyk.popularmovies.persistance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Observer;

import de.lokaizyk.popularmovies.logic.model.MovieDetails;
import de.lokaizyk.popularmovies.logic.model.MovieReview;
import de.lokaizyk.popularmovies.logic.model.MovieVideo;
import de.lokaizyk.popularmovies.persistance.model.DaoMaster;
import de.lokaizyk.popularmovies.persistance.model.DaoSession;
import de.lokaizyk.popularmovies.persistance.model.DbMovieDetails;
import de.lokaizyk.popularmovies.persistance.model.DbMovieReviewDao;
import de.lokaizyk.popularmovies.persistance.model.DbMovieVideoDao;
import de.lokaizyk.popularmovies.util.ModelHelper;

/**
 * Created by lars on 05.10.16.
 */

public class DbManager {

    private static final String TAG = DbManager.class.getSimpleName();

    private static final String DB_NAME = "popularMovies";

    /**
     * DataManager should be alive while the Application is alive. It is suggested to pass the application
     * context.
     */
    private static WeakReference<Context> mContext;

    private static DbManager mInstance;

    private DaoSession daoSession;

    private DbContentObservable contentObservable;

    private DbManager(Context context) {
        mContext = new WeakReference<>(context);
        contentObservable = new DbContentObservable();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext.get(), DB_NAME);
        SQLiteDatabase database = helper.getWritableDatabase();
        daoSession = new DaoMaster(database).newSession();
    }

    /**
     * Must be called once before accessing
     * @param context ApplicationContext
     */
    public static synchronized void init(Context context) {
        if (mInstance == null) {
            mInstance = new DbManager(context);
        }
    }

    /**
     * Gets the Singleton instance of the DataManager
     */
    public static synchronized DbManager getInstance() {
        checkInitialization();
        return mInstance;
    }

    private static void checkInitialization() {
        if (mContext == null) {
            throw new DbManagerNotInitializedException();
        }
    }

    // methods

    public List<DbMovieDetails> loadAllFavorites() {
        return daoSession.getDbMovieDetailsDao().loadAll();
    }

    public synchronized void insertOrUpdateMovieDetails(MovieDetails movieDetails){
        DbMovieVideoDao videoDao = daoSession.getDbMovieVideoDao();
        for (MovieVideo movieVideo : movieDetails.getTrailers()) {
            videoDao.insertOrReplace(ModelHelper.movieVideoToDbModel(movieDetails.getMovieId(), movieVideo));
        }
        DbMovieReviewDao reviewDao = daoSession.getDbMovieReviewDao();
        for (MovieReview review : movieDetails.getReviews()) {
            reviewDao.insertOrReplace(ModelHelper.movieReviewToDbModel(movieDetails.getMovieId(), review));
        }
        daoSession.getDbMovieDetailsDao().insertOrReplace(ModelHelper.movieDetailsAsDbModel(movieDetails));
        notifyObserver();
    }

    public synchronized void deleteMovieDetails(MovieDetails movieDetails) {
        DbMovieVideoDao videoDao = daoSession.getDbMovieVideoDao();
        for (MovieVideo movieVideo : movieDetails.getTrailers()) {
            videoDao.delete(ModelHelper.movieVideoToDbModel(movieDetails.getMovieId(), movieVideo));
        }
        DbMovieReviewDao reviewDao = daoSession.getDbMovieReviewDao();
        for (MovieReview review : movieDetails.getReviews()) {
            reviewDao.delete(ModelHelper.movieReviewToDbModel(movieDetails.getMovieId(), review));
        }
        daoSession.getDbMovieDetailsDao().delete(ModelHelper.movieDetailsAsDbModel(movieDetails));
        notifyObserver();
    }

    public DbMovieDetails loadFavoriteMovie(String id) {
        return daoSession.getDbMovieDetailsDao().load(id);
    }

    public void registerForContentChange(Observer observer) {
        contentObservable.addObserver(observer);
    }

    public void unregisterForContentChange(Observer observer) {
        contentObservable.deleteObserver(observer);
    }

    public void notifyObserver() {
        Log.d(TAG, "notifyObserver");
        new Handler().post(() -> {
            if (contentObservable != null) {
                contentObservable.setChangedNow();
                contentObservable.notifyObservers();
            }
        });
    }

    private static class DbManagerNotInitializedException extends RuntimeException {
        public DbManagerNotInitializedException() {
            super("Please call DataManager.init(Context) before using an instance");
        }
    }

}
