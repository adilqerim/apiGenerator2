package com.kerimov.adee.apigenerator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    Context context;
    List<Post> mPosts;
    private static final String TAG = "PostAdapter";
    Context mContext;





    public PostAdapter(Context context,List<Post> posts) {
        this.mContext = context;
        mPosts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.post_list_item, viewGroup, false);

        return new PostViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder postViewHolder, int i) {
        postViewHolder.tvTitle.setText(mPosts.get(i).getTitle());
        postViewHolder.tvText.setText(mPosts.get(i).getText());
        final Bundle mBundle = new Bundle();
        mBundle.putInt("PostPosition",i);

        postViewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: called");

//                Toast.makeText(mContext,"hey",Toast.LENGTH_SHORT).show();

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new CommentFragment();
                myFragment.setArguments(mBundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {

        return mPosts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvText;
        LinearLayout parent_layout;
            PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvText = itemView.findViewById(R.id.tv_text);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
