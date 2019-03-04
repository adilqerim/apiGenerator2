package com.kerimov.adee.apigenerator;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    List<Photo> photos;
    Context mContext;
    boolean isImageFitToScreen;

    public PhotoAdapter(Context context, List<Photo> photos) {
        this.mContext = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.photo_list_item, viewGroup, false);
        return new PhotoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final PhotoViewHolder photoViewHolder, final int i) {
        photoViewHolder.tvDescription.setText(photos.get(i).getDescription());

        Picasso.get()
                .load(photos.get(i).getImageUrl())
                .into(photoViewHolder.mImageView);
        photoViewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    photoViewHolder.mImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    photoViewHolder.mImageView.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
//                    Picasso.get()
//                            .load(photos.get(i).getUrl())
//                            .into(photoViewHolder.mImageView);
//                    photoViewHolder.mImageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                    photoViewHolder.mImageView.setAdjustViewBounds(false);
//                    photoViewHolder.mImageView.setScaleType(ImageView.ScaleType.CENTER);
                    Intent intent = new Intent(mContext, PhotoAcitvity.class);
                    intent.putExtra("url", photos.get(i).getUrl());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return photos.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescription;
        ImageView mImageView;

        PhotoViewHolder(@NonNull View itemView) {
            super(itemView);

                mImageView = itemView.findViewById(R.id.image_view);
                tvDescription = itemView.findViewById(R.id.tv_title_photo);
        }
    }

}
