package de.lokaizyk.popularmovies.persistance.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by lars on 05.10.16.
 */
@Entity
public class DbMovieDetails {

    @Transient
    private static final String IMAGE_URL = "image_url";
    @Transient
    private static final String TITLE = "title";
    @Transient
    private static final String OVERVIEW = "overview";
    @Transient
    private static final String VOTING = "voting";
    @Transient
    private static final String RELEASE_DATE = "release_date";
    @Transient
    private static final String LENGTH = "length";

    @Id
    private String id;

    @Property(nameInDb = IMAGE_URL)
    private String imageUrl;

    @Property(nameInDb = TITLE)
    private String title;

    @Property(nameInDb = OVERVIEW)
    private String overview;

    @Property(nameInDb = VOTING)
    private String voting;

    @Property(nameInDb = RELEASE_DATE)
    private String releaseDate;

    @Property(nameInDb = LENGTH)
    private int runtime;

    @ToMany(referencedJoinProperty = "movieId")
    private List<DbMovieVideo> movies;

    @ToMany(referencedJoinProperty = "movieId")
    private List<DbMovieReview> reviews;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 123953071)
    private transient DbMovieDetailsDao myDao;

    @Generated(hash = 329785477)
    public DbMovieDetails(String id, String imageUrl, String title, String overview,
            String voting, String releaseDate, int runtime) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.overview = overview;
        this.voting = voting;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
    }

    @Generated(hash = 56838543)
    public DbMovieDetails() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return this.overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVoting() {
        return this.voting;
    }

    public void setVoting(String voting) {
        this.voting = voting;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 107759199)
    public List<DbMovieVideo> getMovies() {
        if (movies == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DbMovieVideoDao targetDao = daoSession.getDbMovieVideoDao();
            List<DbMovieVideo> moviesNew = targetDao
                    ._queryDbMovieDetails_Movies(id);
            synchronized (this) {
                if (movies == null) {
                    movies = moviesNew;
                }
            }
        }
        return movies;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 470865646)
    public synchronized void resetMovies() {
        movies = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1247250152)
    public List<DbMovieReview> getReviews() {
        if (reviews == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DbMovieReviewDao targetDao = daoSession.getDbMovieReviewDao();
            List<DbMovieReview> reviewsNew = targetDao
                    ._queryDbMovieDetails_Reviews(id);
            synchronized (this) {
                if (reviews == null) {
                    reviews = reviewsNew;
                }
            }
        }
        return reviews;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2133376601)
    public synchronized void resetReviews() {
        reviews = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1718679096)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDbMovieDetailsDao() : null;
    }
}
