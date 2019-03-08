package com.kerimov.adee.apigenerator;


import android.content.Intent;
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
public class PostFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Post> mPostList;
    private List<Weather> mWeatherList;
    private int BishkekWeatherId = 1528334;
    private int OshWeahterId = 1527534;
    private int CholponAtaWeahterId = 1528512;
    private int NarynWeatherId = 1527592;
    private int[] IdArray = {BishkekWeatherId, OshWeahterId, CholponAtaWeahterId, NarynWeatherId};


    final int[] randomArray = getRandomArray(10, 100);


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_post, container, false);
        recyclerView =  view.findViewById(R.id.post_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);


        final TextView randomTv = view.findViewById(R.id.tv_random);



////        int[] random = getRandomArrayWeather(IdArray);
//
//        String randomText = random[0] + "\n";
//        randomText += random[1] + "\n";
//        randomText += random[2] + "\n";
//        randomText += random[3] + "\n";
//
//        randomTv.setText(randomText);


        Call<List<Post>> call = NetworkService
                .getInstance()
                .getJSONApi()
                .getPost(randomArray);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                mPostList = response.body();

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

        Call<List<Weather>> callWeather = NetworkService
                .getInstance()
                .getJSONApiWeather()
                .getWeather(IdArray);
        callWeather.enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                mWeatherList = response.body();
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {

            }
        });

        mAdapter = new PostAdapter(view.getContext(),mPostList, mWeatherList, randomArray);

        recyclerView.setAdapter(mAdapter);

        return view;
    }


    public int[] getRandomArray(int ten, int diapason){
        int[] array = new int[ten];
        for (int i = 0; i < 10; i++){
            array[i] = (int)(Math.random() * diapason);
        }
        return array;
    }

//    public int[] getRandomArrayWeather(int[] idRandomArray){
//        int[] array = new int[4];
//        for (int i = 0; i < 4; i++){
//            array[i] = idRandomArray[(int) Math.floor(Math.random() * idRandomArray.length)];
//        }
//        return array;
//    }

}
