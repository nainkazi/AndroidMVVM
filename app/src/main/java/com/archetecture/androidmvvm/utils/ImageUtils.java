package com.archetecture.androidmvvm.utils;

import android.content.Context;
import android.widget.ImageView;

import com.archetecture.androidmvvm.R;
import com.bumptech.glide.Glide;

public class ImageUtils {


    public static void loadImage(Context context, String imgPath, ImageView imageView) {
        try {
            Glide.with(context).load(imgPath).placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(imageView);
        } catch (Exception e) {

        }
    }

}
