package de.lokaizyk.popularmovies.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by lars on 12.09.16.
 */
public abstract class BaseBindingAdapter<T> extends BaseAdapter {

    private static final String TAG = BaseBindingAdapter.class.getSimpleName();

    private ObservableList<T> mItems = new ObservableArrayList<>();

    private int mLayoutId;

    private int mItemDataId;

    public BaseBindingAdapter(int layoutId, int itemDataId) {
        mLayoutId = layoutId;
        mItemDataId = itemDataId;
    }

    public void setItems(ObservableList<T> items) {
        if (items != null) {
            mItems = items;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    @Nullable
    public T getItem(int i) {
        try {
            return mItems.get(i);
        } catch (IndexOutOfBoundsException iobe) {
            Log.e(TAG, iobe.getMessage());
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(mLayoutId, viewGroup, false);
            holder = new ViewHolder();
            holder.mBinding = DataBindingUtil.bind(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        holder.mBinding.setVariable(mItemDataId, mItems.get(i));
        return holder.mBinding.getRoot();
    }

    private class ViewHolder {
        public ViewDataBinding mBinding;
    }

    public interface OnItemClickListener<T> {
        void onItemSelected(int position, T item);
    }
}
