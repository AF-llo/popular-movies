package de.lokaizyk.popularmovies.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.lokaizyk.popularmovies.logic.MoviesProvider;

/**
 * Created by lars on 14.09.16.
 */
public class RequestTask<T> extends AsyncTask<URL, Void, T> {

    private static final String TAG = RequestTask.class.getSimpleName();

    private MoviesProvider.RequestListener<T> listener;

    private JsonParser<T> parser;

    private String error = null;

    public RequestTask(MoviesProvider.RequestListener<T> listener, JsonParser<T> parser) {
        this.listener = listener;
        this.parser = parser;
    }

    @Override
    protected T doInBackground(URL... urls) {
        try {
            return parser.parseJson(requestFromUrl(urls[0]));
        } catch (IOException | JSONException e) {
            error = e.getMessage();
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(T result) {
        if (error != null && listener != null) {
            listener.onError(error);
        } else {
            if (listener != null && result != null) {
                listener.onSuccess(result);
            }
        }
    }

    private String requestFromUrl(URL url) throws IOException {
        Log.d(TAG, "requestFromUrl() called with: " + "url = [" + url + "]");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(RequestHelper.HTTP_PARAM_GET);
        urlConnection.connect();

        // Read the input stream into a Stringh
        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
            // Nothing to do.
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
            // But it does make debugging a *lot* easier if you print out the completed
            // buffer for debugging.
            buffer.append(line + "\n");
        }

        if (buffer.length() == 0) {
            // Stream was empty.  No point in parsing.
            return null;
        }
        return buffer.toString();
    }
}
