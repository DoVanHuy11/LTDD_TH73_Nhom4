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

public class ProductRecycleViewAdapter extends RecyclerView.Adapter<ProductRecycleViewAdapter.ViewHolder>  {
    ArrayList<String> arrayKeys;
    ArrayList<ItemRecycleView> arrayList;
    Context context;
    public ProductRecycleViewAdapter(Context context, ArrayList<ItemRecycleView> arrayList, ArrayList<String> arrayKeys){
        this.context=context;
        this.arrayList=arrayList;
        this.arrayKeys=arrayKeys;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view,null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemRecycleView itemRecycleView = arrayList.get(position);
        holder.tvProductTitle.setText(itemRecycleView.getName());
        holder.tvSubTitle.setText(itemRecycleView.getPrice());
        Picasso.get().load(itemRecycleView.getImage())
                .into(holder.imgRecycleItem);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgRecycleItem;
        public TextView tvProductTitle,tvSubTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRecycleItem=(ImageView)itemView.findViewById(R.id.imgItemRecycle);
            tvProductTitle=(TextView) itemView.findViewById(R.id.titleItemRecycle);
            tvSubTitle=(TextView) itemView.findViewById(R.id.subTitleItemRecycle);
        }
    }
}
