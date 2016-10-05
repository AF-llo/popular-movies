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
public class DbMovieReview {

    @Transient
    private static final String MOVIE_ID = "movieId";
    @Transient
    private static final String AUTHOR = "author";
    @Transient
    private static final String CONTENT = "content";

    @Id
    private String id;

    @Property(nameInDb = MOVIE_ID)
    private String movieId;

    @Property(nameInDb = AUTHOR)
    private String author;

    @Property(nameInDb = CONTENT)
    private String content;

    @Generated(hash = 119618453)
    public DbMovieReview(String id, String movieId, String author, String content) {
        this.id = id;
        this.movieId = movieId;
        this.author = author;
        this.content = content;
    }

    @Generated(hash = 1942957552)
    public DbMovieReview() {
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

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
