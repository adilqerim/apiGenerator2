package com.kerimov.adee.apigenerator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import java.util.Collections;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Post> mPosts;
    List<Weather> mWeathersList;
    private static final String TAG = "PostAdapter";
    Context mContext;
    Bundle mBundle;
    int[] randomArray;
    private static final int TYPE_HEADER_POST = 0;
    private static final int TYPE_HEADER_WEATHER = 1;

    public PostAdapter(Context context,List<Post> posts,List<Weather> weathers) {
        this.mContext = context;
        mPosts = posts;
        mWeathersList = weathers;
        Collections.shuffle(mPosts);
        Collections.shuffle(mWeathersList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_HEADER_POST) {
            return TYPE_HEADER_WEATHER;
        } else {
            return TYPE_HEADER_POST;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_HEADER_POST) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.post_list_item, viewGroup, false);
            return new PostsViewHolder(view);
        }
        else {
                View view = LayoutInflater.from(mContext).inflate(R.layout.weather_list_item, viewGroup, false);
                return new WeatherViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int i) {

        if (viewHolder.getItemViewType() == TYPE_HEADER_POST) {
            PostsViewHolder postsViewHolder = (PostsViewHolder) viewHolder;
            postsViewHolder.tvTitle.setText(mPosts.get(i).getTitle());
            postsViewHolder.tvText.setText(mPosts.get(i).getText());

            mBundle = new Bundle();
            mBundle.putInt("PostPosition",i);

            PostsViewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: called");
                    String position = "Position: " + String.valueOf(i);

                    Toast.makeText(mContext,position,Toast.LENGTH_SHORT).show();

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();

                    Intent intent = new Intent(activity, CommentActivity.class);
                    intent.putExtra("position", String.valueOf(i));
                    intent.putExtra("randomArray", randomArray);
                    activity.startActivity(intent);
                }
            });
        }
         else {
            WeatherViewHolder weatherViewHolder = (WeatherViewHolder) viewHolder;
            weatherViewHolder.tvNameWeather.setText(mWeathersList.get(i).getName());
        }


    }


    @Override
    public int getItemCount() {
        return mPosts.size() + mWeathersList.size();
    }

    static class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvText;
        static LinearLayout parent_layout;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvText = itemView.findViewById(R.id.tv_text);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameWeather;
        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameWeather = itemView.findViewById(R.id.tv_name_weather);

        }
    }

}
