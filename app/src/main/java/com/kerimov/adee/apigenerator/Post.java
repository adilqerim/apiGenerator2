package com.kerimov.adee.apigenerator;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("title")
    private String mTitle;
    @SerializedName("body")
    private String mText;

    public String getTitle() {
        return mTitle;
    }

    public String getText() {
        return mText;
    }
}
