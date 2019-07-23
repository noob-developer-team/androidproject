package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerVadapater2 extends RecyclerView.Adapter<RecyclerVadapater2.MyViewHolder> {

    private Context mContext;
    private List<DMY> mData;

    public RecyclerVadapater2(Context mContext, List<DMY> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.schedule_item_list,parent,false);
        MyViewHolder vHolder= new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtday.setText(mData.get(position).getDay());
        holder.txtdate.setText(mData.get(position).getMonth());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtday;
        TextView txtdate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           txtday = (TextView) itemView.findViewById(R.id.txt_day);
            txtdate = (TextView) itemView.findViewById(R.id.txt_date);
        }
    }
}
