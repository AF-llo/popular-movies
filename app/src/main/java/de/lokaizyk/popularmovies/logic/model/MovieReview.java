package de.lokaizyk.popularmovies.logic.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by lars on 03.10.16.
 */

public class MovieReview implements Parcelable {

    private String id = "";

    private String author = "";

    private String content = "";

    public MovieReview(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (!TextUtils.isEmpty(author)) {
            this.author = author;
        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            this.content = content;
        }
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.content);
    }

    protected MovieReview(Parcel in) {
        this.author = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<MovieReview> CREATOR = new Parcelable.Creator<MovieReview>() {
        @Override
        public MovieReview createFromParcel(Parcel source) {
            return new MovieReview(source);
        }

        @Override
        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };
}
