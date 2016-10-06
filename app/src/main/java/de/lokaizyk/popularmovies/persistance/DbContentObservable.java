package de.lokaizyk.popularmovies.persistance;

import java.util.Observable;

/**
 * Created by lars on 06.10.16.
 */

public class DbContentObservable extends Observable {

    public void setChangedNow() {
        setChanged();
    }

}
