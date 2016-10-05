package de.lokaizyk.popularmovies.persistance.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lars on 05.10.16.
 */
@Entity
public class DbMovieVideo {

    @Transient
    private static final String MOVIE_ID = "movieId";
    @Transient
    private static final String TITLE = "title";
    @Transient
    private static final String KEY = "key";

    @Id
    private String id;

    @Property(nameInDb = MOVIE_ID)
    private String movieId;

    @Property(nameInDb = TITLE)
    private String title;

    @Property(nameInDb = KEY)
    private String key;

    @Generated(hash = 813714918)
    public DbMovieVideo(String id, String movieId, String title, String key) {
        this.id = id;
        this.movieId = movieId;
        this.title = title;
        this.key = key;
    }

    @Generated(hash = 1962297032)
    public DbMovieVideo() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
