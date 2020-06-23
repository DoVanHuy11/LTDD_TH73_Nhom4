package com.example.fashion.myadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion.R;
import com.example.fashion.interf.StartActiFromListView;
import com.example.fashion.model.ItemRecycleView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductDetail_ListViewAdapter extends RecyclerView.Adapter<ProductDetail_ListViewAdapter.ViewHolder> {
    private ArrayList<ItemRecycleView> arrayList;
    private StartActiFromListView startActiFromListView;
    private ArrayList<String> arrayKeys;

    public ProductDetail_ListViewAdapter(ArrayList<ItemRecycleView> arrayList, ArrayList<String> arrayKeys, StartActiFromListView startActiFromListView) {
        this.arrayList = arrayList;
        this.startActiFromListView = startActiFromListView;
        this.arrayKeys = arrayKeys;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product2,null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ItemRecycleView itemRecycleView = arrayList.get(position);
        holder.tvTitle.setText(itemRecycleView.getName());
        holder.tvSubTitle.setText(itemRecycleView.getPrice()+".000");
        Picasso.get().load(itemRecycleView.getImage())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvSubTitle;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.productdetail_title);
            tvSubTitle  = (TextView) itemView.findViewById(R.id.productdetail_subtitle);
            img = (ImageView) itemView.findViewById(R.id.productdetail_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActiFromListView.StartActivity(arrayKeys.get(getAdapterPosition()),arrayList.get(getAdapterPosition()));
                }
            });
        }
    }

}
