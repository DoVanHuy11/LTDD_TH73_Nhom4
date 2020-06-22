package com.example.fashion.myadapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.fashion.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageViewPagerAdapter extends PagerAdapter {
    Context mcontext;
    ArrayList<String> imageAddress;
    public ImageViewPagerAdapter(Context context,ArrayList<String> imageAddress){
        this.mcontext= context;
        this.imageAddress = imageAddress;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == ((ImageView) object);
    }

//    private int[] sliderImageId= new int[]{
//            R.drawable.img1,
//            R.drawable.img1_1,
//            R.drawable.img1_2,
//            R.drawable.img1_3,
//            R.drawable.img1_4,
//    };

    @Override
    public int getCount() {
        return imageAddress.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        ImageView imageView = new ImageView(mcontext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso.get().load(imageAddress.get(position))
                .placeholder(R.drawable.imgload)
                .into(imageView);
        ((ViewPager) container).addView(imageView,0 );
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        ((ViewPager) container).removeView((ImageView) object);
    }


}
