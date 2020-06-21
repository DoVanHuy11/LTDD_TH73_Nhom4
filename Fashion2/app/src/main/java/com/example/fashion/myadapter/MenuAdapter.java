package com.example.fashion.myadapter;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fashion.R;
import com.example.fashion.model.ItemNavigation;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    ArrayList<ItemNavigation> arrayList;
    LayoutInflater inflater;

    public MenuAdapter(ArrayList<ItemNavigation> arrayList, Context context) {
        this.arrayList = arrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView tvNameMenuItem;
        ImageView imgMenuItem;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_list_view_navigation, null);
            holder = new ViewHolder();
            holder.tvNameMenuItem = (TextView) convertView.findViewById(R.id.nav_title);
            holder.imgMenuItem = (ImageView) convertView.findViewById(R.id.nav_ic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tvNameMenuItem.setText(arrayList.get(position).getTitLe());
        holder.imgMenuItem.setImageResource(arrayList.get(position).getImage());


        return convertView;
    }



}
