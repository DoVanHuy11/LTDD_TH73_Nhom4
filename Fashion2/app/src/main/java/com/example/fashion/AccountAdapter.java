package com.example.fashion;

import android.content.Context;
import android.os.BaseBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AccountAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<Account> accountList;

    public AccountAdapter(Context context, int layout, List<Account> accountList) {
        this.context = context;
        this.layout = layout;
        this.accountList = accountList;
    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tvAcTieuDe;
        EditText tvAcThongTin;
        ImageView imgAccount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();

            viewHolder.tvAcTieuDe = (TextView) convertView.findViewById(R.id.tvAcTieuDe);
            viewHolder.tvAcThongTin = (EditText) convertView.findViewById(R.id.edtAcThongTin);
            viewHolder.imgAccount = (ImageView) convertView.findViewById((R.id.imgAccount));
            convertView.setTag(viewHolder);
        }else viewHolder = (ViewHolder) convertView.getTag();

        //gan gia tri
        viewHolder.tvAcTieuDe.setText(accountList.get(position).tieuDe);
        viewHolder.tvAcThongTin.setText(accountList.get(position).thongTin);
        viewHolder.imgAccount.setImageResource(accountList.get(position).icon);

        return convertView;
    }
}
