package com.example.s3.Adapter;

import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass {
    int image;
    String title,description;
    GradientDrawable g;

    public CategoriesHelperClass(GradientDrawable g,int image, String title,String description) {
        this.image = image;
        this.title = title;
        this.description = description;
  this.g=g;
    }

    public int getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public GradientDrawable getGradient() {
        return g;
    }
}
