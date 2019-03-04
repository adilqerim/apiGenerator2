package com.kerimov.adee.apigenerator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Comment> mCommentList;
    private Post post;
    private TextView tvPost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comment);

        recyclerView =  findViewById(R.id.comment_recycler_view);
        tvPost = findViewById(R.id.post_text_view);


        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        int currentPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        int[] randomArray = getIntent().getIntArrayExtra("randomArray");

        Call<Post> callById = NetworkService
                .getInstance()
                .getJSONApi()
                .getPostById(currentPosition);
        callById.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                post = response.body();

                String postText = "";
                if (post != null) {
                    postText += post.getTitle() + "\n";
                    postText += post.getText() + "\n";
                }

                tvPost.setText(postText);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

        Call<List<Comment>> call = NetworkService
                .getInstance()
                .getJSONApi()
                .getComment(randomArray);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                mCommentList = response.body();

                mAdapter = new CommentAdapter(CommentActivity.this,mCommentList);

                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }
}
