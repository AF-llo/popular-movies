package de.lokaizyk.popularmovies.util;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import de.lokaizyk.popularmovies.ui.adapter.BaseBindingAdapter;

/**
 * Created by lars on 12.09.16.
 */
public class GridViewBinding {

    @BindingAdapter("bind:itemClickListener")
    public static <T> void setItemClickListener(GridView gridView, final BaseBindingAdapter.OnItemClickListener<T> onItemClickListener) {
        if (onItemClickListener != null) {
            final BaseBindingAdapter adapter;
            try {
                adapter = (BaseBindingAdapter) gridView.getAdapter();
            } catch (ClassCastException cce) {
                throw new IllegalStateException(gridView.getAdapter().getClass().getSimpleName() + " must extend " + BaseBindingAdapter.class.getSimpleName());
            }
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @SuppressWarnings("unchecked")
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        onItemClickListener.onItemSelected(i, (T) adapter.getItem(i));
                    } catch (ClassCastException cce) {
                        throw new IllegalStateException("Missmatching types. BaseBindingAdapter.OnItemClickListner should be of type " + adapter.getItem(i).getClass().getSimpleName());
                    }
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("bind:items")
    public static <T> void setItems(GridView gridView, ObservableList<T> items) {
        final BaseBindingAdapter adapter;
        try {
            adapter = (BaseBindingAdapter) gridView.getAdapter();
        } catch (ClassCastException cce) {
            throw new IllegalStateException(gridView.getAdapter().getClass().getSimpleName() + " must extend " + BaseBindingAdapter.class.getSimpleName());
        }
        adapter.setItems(items);
    }

}
