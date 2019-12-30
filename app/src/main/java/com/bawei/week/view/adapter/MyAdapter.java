package com.bawei.week.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week.Model.Bean.GsonBean;
import com.bawei.week.R;
import com.bawei.week.util.NetUtil;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    List<GsonBean.DataBean> data;
    public MyAdapter(List<GsonBean.DataBean> data) {
        this.data=data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.child, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GsonBean.DataBean dataBean = data.get(position);
        holder.name.setText(dataBean.getGoods_name());
        holder.price.setText(dataBean.getCurrency_price());
        NetUtil.getInstance().getPho(dataBean.getGoods_thumb(),holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickLisetner.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView name;
        private final TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.c_image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
    onItemClickLisetner onItemClickLisetner;

    public void setOnItemClickLisetner(MyAdapter.onItemClickLisetner onItemClickLisetner) {
        this.onItemClickLisetner = onItemClickLisetner;
    }

    public interface onItemClickLisetner{
        void onItemClick(int i);
    }
}
