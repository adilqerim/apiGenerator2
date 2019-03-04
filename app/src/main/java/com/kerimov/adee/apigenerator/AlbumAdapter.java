package com.kerimov.adee.apigenerator;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    List<Album> albums;
    Context mContext;
    private LayoutInflater mInflater;

    public AlbumAdapter(Context context, List<Album> albums) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.album_list_item, viewGroup, false);
        return new AlbumViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder albumViewHolder, @SuppressLint("RecyclerView") final int i) {
        albumViewHolder.tvTitle.setText(albums.get(i).getTitle());

        albumViewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String position = "Position: " + String.valueOf(i);

                Toast.makeText(mContext,position,Toast.LENGTH_SHORT).show();

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new PhotoFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {

        return albums.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        LinearLayout parent_layout;
        AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title_album);
            parent_layout = itemView.findViewById(R.id.parent_layout_album);

        }
    }

}
