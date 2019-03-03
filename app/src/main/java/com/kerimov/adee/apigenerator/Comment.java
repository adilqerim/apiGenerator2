package com.kerimov.adee.apigenerator;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("name")
    private String mName;
    @SerializedName("email")
    private String mMail;
    @SerializedName("body")
    private String mText;

    public String getName() {
        return mName;
    }

    public String getMail() {
        return mMail;
    }

    public String getText() {
        return mText;
    }
}
