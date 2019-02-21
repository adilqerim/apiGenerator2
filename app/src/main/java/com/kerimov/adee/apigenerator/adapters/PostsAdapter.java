package com.kerimov.adee.apigenerator.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kerimov.adee.apigenerator.R;
import com.kerimov.adee.apigenerator.model.Post;
import com.kerimov.adee.apigenerator.model.Weather;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final int VIEW_TYPE_POST = 0;
    final int VIEW_TYPE_WEATHER = 1;


    Context context;
    List<Post> posts;
    List<Weather> weather;

    public PostsAdapter(Context context, List<Post> posts,List<Weather> weather) {
        this.context = context;
        this.posts = posts;
        this.weather = weather;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType == VIEW_TYPE_POST){
            return new PostsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_list_item, viewGroup, false));
        }

        if(viewType == VIEW_TYPE_WEATHER){
            return new WeatherViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_list_item, viewGroup, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof PostsViewHolder){
            ((PostsViewHolder) viewHolder).populate(posts.get(position));
        }

        if(viewHolder instanceof WeatherViewHolder){
            ((WeatherViewHolder) viewHolder).populate(weather.get(position - posts.size()));
        }

    }


    @Override
    public int getItemCount() {

        return posts.size() + weather.size();
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

        public void populate(Post post) {
            txtUserId.setText(post.getUserId());
            txtId.setText(post.getId());
            txtTitle.setText(post.getTitle());
            txtText.setText(post.getText());
        }

    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView txtCity, txtTemp;
        WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCity = itemView.findViewById(R.id.txt_city);
            txtTemp = itemView.findViewById(R.id.txt_temp);
        }

        public void populate(Weather weather) {
            txtCity.setText(weather.getName());
            txtTemp.setText(String.valueOf(weather.getTemp()));

        }
    }



}
