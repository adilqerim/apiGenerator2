package com.kerimov.adee.apigenerator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumFragment extends Fragment{

    private static final String TAG = "MainActivity";
    private AlbumAdapter mAlbumAdapter;
    private RecyclerView recyclerView;
    private List<Album> mAlbumList;


    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_album, container, false);

        recyclerView =  view.findViewById(R.id.album_recycler_view);
        final int numberOfColumns = 3;




        Call<List<Album>> call = NetworkService
                .getInstance()
                .getJSONApi()
                .getAlbum(getRandomArray(10,100));
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                mAlbumList = response.body();


                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));

                mAlbumAdapter = new AlbumAdapter(getContext(), mAlbumList);

                recyclerView.setAdapter(mAlbumAdapter);

            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Toast.makeText(view.getContext(), "wrong", Toast.LENGTH_SHORT).show();
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


