package com.rikkei.networking;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context mContext;
    private List<Photo> mPhotos;
    private OnItemClickListener mListener;

    public PhotoAdapter(Context context, List<Photo> photos, OnItemClickListener listener) {
        mContext = context;
        mPhotos = photos;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_photo, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindView(mPhotos.get(i));
    }

    @Override
    public int getItemCount() {
        return mPhotos != null ? mPhotos.size() : 0;
    }

    public void insertPhotos(List<Photo> photos) {
        mPhotos.addAll(photos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImagePhoto;
        private TextView mTextTitle;
        private ConstraintLayout mLayoutPhoto;
        private OnItemClickListener mListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
            mImagePhoto = itemView.findViewById(R.id.image_photo);
            mTextTitle = itemView.findViewById(R.id.text_title);
            mLayoutPhoto = itemView.findViewById(R.id.layout_photo);
            mLayoutPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(getAdapterPosition());
                }
            });
        }

        private void bindView(Photo photo) {
            mTextTitle.setText(photo.getTitle());
            Glide.with(mContext)
                    .load(photo.getUrl())
                    .into(mImagePhoto);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
