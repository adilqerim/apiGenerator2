package com.kerimov.adee.apigenerator;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment{

    private static final String TAG = "MainActivity";
    private PhotoAdapter mPhotoAdapter;
    private RecyclerView recyclerView;
    private List<Photo> mPhotoList;
//    private ImageView mImageView;


    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_photo, container, false);

        recyclerView =  view.findViewById(R.id.photo_recycle_view);
//        mImageView = view.findViewById(R.id.image_view);

        Call<List<Photo>> call = NetworkService
                .getInstance()
                .getJSONApi()
                .getPhoto(getRandomArray(50,100));
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

                mPhotoList = response.body();

                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

                mPhotoAdapter = new PhotoAdapter(view.getContext(), mPhotoList);

                recyclerView.setAdapter(mPhotoAdapter);

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
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


