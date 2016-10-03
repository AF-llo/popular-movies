package de.lokaizyk.popularmovies.network.rx;

import android.util.Log;

import de.lokaizyk.popularmovies.logic.MoviesProvider;
import rx.Subscriber;

/**
 * @author Lars
 * Created on 03.10.16.
 *
 * This is a base class that implements onError and onCompleted. It delegates the the error message
 * to the MoviesProvider.RequestListener if set. Extend this class to parse Results from the API
 * to the local Model that is used to display data to the UI.<br>
 *
 * The Parsing of data should be handled in onNext() and passed by calling RequestSubscriber.onSuccess()
 *
 * @see Subscriber
 * @see de.lokaizyk.popularmovies.logic.MoviesProvider.RequestListener
 */

public abstract class RequestSubscriber<T, V> extends Subscriber<V> {

    private static final String TAG = RequestSubscriber.class.getSimpleName();

    private MoviesProvider.RequestListener<T> mRequestListener;

    public RequestSubscriber(MoviesProvider.RequestListener<T> requestListener) {
        mRequestListener = requestListener;
    }

    @Override
    public void onCompleted() {
        Log.d(TAG, "onCompleted()");
    }

    @Override
    public void onError(Throwable e) {
        onError(e.getMessage());
    }

    protected void onSuccess(T data) {
        if (mRequestListener != null) {
            mRequestListener.onSuccess(data);
        }
    }

    protected void onError(String cause) {
        if (mRequestListener != null) {
            mRequestListener.onError(cause);
        }
    }
}
