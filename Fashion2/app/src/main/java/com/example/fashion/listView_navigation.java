package com.example.fashion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class listView_navigation extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] navtitle;
    private final Integer[] imgnav;

    public listView_navigation(Activity context, String[] navtitle, Integer[] imgnav){
        super(context, R.layout.activity_list_view_navigation, navtitle);
        this.context = context;
        this.navtitle = navtitle;
        this.imgnav = imgnav;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater= context.getLayoutInflater();
        View row= inflater.inflate(R.layout.activity_list_view_navigation, null, true);
        TextView title = (TextView) row.findViewById(R.id.nav_title);
        ImageView imageView = (ImageView) row.findViewById(R.id.nav_ic);

        title.setText(navtitle[position]);
        imageView.setImageResource(imgnav[position]);
        return row;
    }
   }