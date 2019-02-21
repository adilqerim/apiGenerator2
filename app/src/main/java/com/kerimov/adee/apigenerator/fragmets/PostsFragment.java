package com.kerimov.adee.apigenerator.fragmets;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kerimov.adee.apigenerator.NetworkService;
import com.kerimov.adee.apigenerator.R;
import com.kerimov.adee.apigenerator.adapters.PostsAdapter;
import com.kerimov.adee.apigenerator.model.Post;
import com.kerimov.adee.apigenerator.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsFragment extends Fragment {

    RecyclerView recyclerView;
    PostsAdapter adapter;
    RecyclerView.LayoutManager  layoutManager;
    List<Post> posts;
    List<Weather> weather;
    private int[] randomId = getRandomId();
    private int[] cityId = {1528334, 1527534, 1528512, 1527592};
    private static final String APP_ID = "985601627df7745a7f4a0a1d8b7b4f4b";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_posts);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        getPosts();

        return view;

    }

    private void getPosts(){
        NetworkService networkServicePost = new NetworkService(0);
        NetworkService networkServiceWeather = new NetworkService(1);

        Call<List<Post>> callPost = networkServicePost.getJSONApi()
                .getPost(randomId);
        Call<List<Weather>> callWeather = networkServiceWeather.getJSONApi().getWeather(cityId,APP_ID);
        callPost.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                posts = response.body();

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        callWeather.enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                weather = response.body();
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


            adapter = new PostsAdapter(getActivity(),posts,weather);
            recyclerView.setAdapter(adapter);
    }

    private int[] getRandomId() {
        int[] randomId = new int[10];
        for (int i = 0; i < randomId.length; i++) {
            randomId[i] = (int) (Math.random() * 100);
        }
        return randomId;
    }

}
