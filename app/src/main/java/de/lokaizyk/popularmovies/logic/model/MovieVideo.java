package de.lokaizyk.popularmovies.logic.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by lars on 03.10.16.
 */

public class MovieVideo implements Parcelable {

    private String title = "";

    private String id = "";

    private String key = "";

    public MovieVideo(String id, String key) {
        if (TextUtils.isEmpty(id) | TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("Invalid key od id");
        }
        this.id = id;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.id);
        dest.writeString(this.key);
    }

    protected MovieVideo(Parcel in) {
        this.title = in.readString();
        this.id = in.readString();
        this.key = in.readString();
    }

    public static final Parcelable.Creator<MovieVideo> CREATOR = new Parcelable.Creator<MovieVideo>() {
        @Override
        public MovieVideo createFromParcel(Parcel source) {
            return new MovieVideo(source);
        }

        @Override
        public MovieVideo[] newArray(int size) {
            return new MovieVideo[size];
        }
    };
}
