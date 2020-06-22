package com.example.fashion.myadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fashion.R;
import com.example.fashion.model.ItemRecycleView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductDetail_ListViewAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<ItemRecycleView> arrayList;

    public ProductDetail_ListViewAdapter(Context context,ArrayList<ItemRecycleView> arrayList){
        super(context, R.layout.product2);
        this.context = context;
        this.arrayList = arrayList;
    }

    public class ViewHolder{
        TextView tvTitle,tvSubTitle;
        ImageView img;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.product2, null, true);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.productdetail_title);
            viewHolder.tvSubTitle  = (TextView) convertView.findViewById(R.id.productdetail_subtitle);
             viewHolder.img = (ImageView) convertView.findViewById(R.id.productdetail_img);

             convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemRecycleView itemRecycleView = arrayList.get(position);
        viewHolder.tvTitle.setText(itemRecycleView.getName());
        viewHolder.tvSubTitle.setText(itemRecycleView.getPrice()+".000");
        Picasso.get().load(itemRecycleView.getImage())
                .into(viewHolder.img);
        return convertView;
    }
}
