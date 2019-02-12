package com.rikkei.networking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context mContext;
    private List<Post> mPosts;
    private OnItemClickListener mListener;

    public PostAdapter(Context context, List<Post> posts, OnItemClickListener listener) {
        mContext = context;
        mPosts = posts;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_post, viewGroup, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindView(mPosts.get(i));
    }

    @Override
    public int getItemCount() {
        return mPosts != null ? mPosts.size() : 0;
    }

    public void insertPosts(List<Post> post) {
        mPosts.addAll(post);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextUserId;
        private TextView mTextTitle;
        private ConstraintLayout mLayoutPost;
        private OnItemClickListener mListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
            mTextTitle = itemView.findViewById(R.id.text_title);
            mTextUserId = itemView.findViewById(R.id.text_user_id);
            mLayoutPost = itemView.findViewById(R.id.layout_post);
            mLayoutPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(getAdapterPosition());
                }
            });
        }

        private void bindView(Post post) {
            mTextUserId.setText(String.valueOf(post.getUserId()));
            mTextTitle.setText(String.valueOf(post.getTitle()));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
