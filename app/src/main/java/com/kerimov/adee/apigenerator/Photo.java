package com.kerimov.adee.apigenerator;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("thumbnailUrl")
    String imageUrl;
    @SerializedName("url")
    String url;
    @SerializedName("title")
    String description;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
