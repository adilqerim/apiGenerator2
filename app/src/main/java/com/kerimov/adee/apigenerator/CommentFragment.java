package com.kerimov.adee.apigenerator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Comment> mCommentList;
    private Post post;
    private TextView tvPost;





    public CommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_comment, container, false);

        recyclerView =  view.findViewById(R.id.comment_recycler_view);
        tvPost = view.findViewById(R.id.post_text_view);


        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final int currentPosition = getArguments().getInt("PostPosition");
        final int[] randomArray =  getArguments().getIntArray("Array");

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
                .getComment(getRandomArray(10,100));
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                mCommentList = response.body();

                mAdapter = new CommentAdapter(view.getContext(),mCommentList);

                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });


        return view;
    }

    public int[] getRandomArray(int ten, int diapason){
        int[] array = new int[ten];
        for (int i = 0; i < ten; i++){
            array[i] = (int)(Math.random() * diapason);
        }
        return array;
    }

}
