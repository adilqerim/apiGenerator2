package com.kerimov.adee.apigenerator;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kerimov.adee.apigenerator.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private List<Post> dataList;

    public PostsAdapter(List<Post> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder postsViewHolder, int i) {
        postsViewHolder.txtUserId.setText(dataList.get(i).getUserId());
        postsViewHolder.txtId.setText(dataList.get(i).getId());
        postsViewHolder.txtTitle.setText(dataList.get(i).getTitle());
        postsViewHolder.txtText.setText(dataList.get(i).getText());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserId,txtId,txtTitle, txtText;

        PostsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtUserId = itemView.findViewById(R.id.txt_userId);
            txtId = itemView.findViewById(R.id.txt_id);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtText = itemView.findViewById(R.id.txt_text);
        }
    }


}
