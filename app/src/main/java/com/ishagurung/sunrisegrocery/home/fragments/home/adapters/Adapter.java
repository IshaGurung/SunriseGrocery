package com.ishagurung.sunrisegrocery.home.fragments.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ishagurung.sunrisegrocery.R;
import com.ishagurung.sunrisegrocery.api.response.Product;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    List<Product> productDataList;
    private Context context;

    public Adapter(List<Product> productDataList, Context context) {
        this.productDataList = productDataList;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nameTV.setText(productDataList.get(position).getName());
        holder.priceTv.setText(productDataList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView productIV, removeCartIV;
        TextView nameTV, priceTv, discountPrice, discountPercent, quantityTV;
        public MyViewHolder(View itemView) {
            super(itemView);
            productIV = itemView.findViewById(R.id.productIV);
            nameTV = itemView.findViewById(R.id.productNameTV);
            priceTv = itemView.findViewById(R.id.oldPriceTV);
            discountPrice = itemView.findViewById(R.id.discountPriceTV);
        }
    }
}
