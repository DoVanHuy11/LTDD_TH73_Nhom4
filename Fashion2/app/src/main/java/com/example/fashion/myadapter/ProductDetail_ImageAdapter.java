package com.example.fashion.myadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.fashion.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductDetail_ImageAdapter extends PagerAdapter {
    ArrayList<String> arrayList;
    Context mContext;

    public ProductDetail_ImageAdapter(Context context,ArrayList<String> arrayList){
        this.mContext = context;
        this.arrayList = arrayList;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ImageView) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get().load(arrayList.get(position))
                .into(imageView);
        ((ViewPager)container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        ((ViewPager)container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

}
