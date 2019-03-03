package com.kerimov.adee.apigenerator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.PostViewHolder> {
    List<Comment> mComments;
    Context mContext;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.mContext = context;
        mComments = comments;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.comment_list_item, viewGroup, false);
        return new PostViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        postViewHolder.tvName.setText(mComments.get(i).getName());
        postViewHolder.tvMail.setText(mComments.get(i).getMail());
        postViewHolder.tvText.setText(mComments.get(i).getText());

    }

    @Override
    public int getItemCount() {

        return mComments.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvMail, tvText;

            PostViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvMail = itemView.findViewById(R.id.tv_mail);
            tvText = itemView.findViewById(R.id.tv_text);
        }
    }

}
