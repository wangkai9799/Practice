package com.dankai.recyclerview.recycler.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author dankai
 * @date 3/30/21 10:56 PM
 */

public class MyAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final List<TypeInterface> mData;
    private final Context mContext;
    private int mCurrentPosition = -1;

    public MyAdapter(List<TypeInterface> data, Context context) {
        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        if (mCurrentPosition != -1) {
            return mData.get(mCurrentPosition).onCreateViewHolder(view);
        } else {
            return mData.get(0).onCreateViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.begin(mData.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getLayoutId();
    }

    @Override
    public long getItemId(int position) {
        mCurrentPosition = position;
        return position;
    }
}
