package com.example.fashion.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashion.R;
import com.example.fashion.model.ItemRecycleView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductRecycleViewAdapter.ItemHolder> {
    ArrayList<ItemRecycleView> arrayList;
    ArrayList<String> arrayKeys;
    public ProductRecycleViewAdapter(Context context, ArrayList<ItemRecycleView> arrayList, ArrayList<String> arrayKeys){
        this.context=context;
        this.arrayList=arrayList;
        this.arrayKeys=arrayKeys;
    }
    Context context;
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product,null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        ItemRecycleView itemRecycleView = arrayList.get(position);
        holder.tvProductTitle.setText(itemRecycleView.getName());
        holder.tvSubTitle.setText(itemRecycleView.getPrice().toString());
        Picasso.get().load(itemRecycleView.getImage())
                .into(holder.imgRecycleItem);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imgRecycleItem;
        public TextView tvProductTitle,tvSubTitle;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgRecycleItem=(ImageView)itemView.findViewById(R.id.product_img);
            tvProductTitle=(TextView) itemView.findViewById(R.id.product_title);
            tvProductTitle=(TextView) itemView.findViewById(R.id.product_subtitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
