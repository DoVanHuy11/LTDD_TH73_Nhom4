package myadapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.fashion.R;

import java.io.ObjectInput;

public class ImageAdapter extends PagerAdapter {
    Context mcontext;
    public ImageAdapter(Context context){
        this.mcontext= context;
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == ((ImageView) object);
    }

    private int[] sliderImageId= new int[]{
            R.drawable.img1,
            R.drawable.img1_1,
            R.drawable.img1_2,
            R.drawable.img1_3,
            R.drawable.img1_4,
    };

    @Override
    public int getCount() {
        return sliderImageId.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        ImageView imageView = new ImageView(mcontext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(sliderImageId[position]);
        ((ViewPager) container).addView(imageView,0 );
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        ((ViewPager) container).removeView((ImageView) object);
    }




}
