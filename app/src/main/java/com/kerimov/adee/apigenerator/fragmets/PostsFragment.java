package com.kerimov.adee.apigenerator.fragmets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kerimov.adee.apigenerator.MainActivity;
import com.kerimov.adee.apigenerator.NetworkService;
import com.kerimov.adee.apigenerator.PostsAdapter;
import com.kerimov.adee.apigenerator.R;
import com.kerimov.adee.apigenerator.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsFragment extends Fragment {

    RecyclerView recyclerView;
    PostsAdapter adapter;
    RecyclerView.LayoutManager  layoutManager;
    List<Post> posts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_posts);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Call<List<Post>> call = NetworkService.getInstance().getJSONApi()
                .postsList();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                posts = response.body();

                adapter = new PostsAdapter(posts);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}
