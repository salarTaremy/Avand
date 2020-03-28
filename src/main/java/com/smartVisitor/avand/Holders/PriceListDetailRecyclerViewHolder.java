package com.smartVisitor.avand.Holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smartVisitor.avand.R;

public class PriceListDetailRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    public TextView TV_PriceListDetail_Description,TV_PriceListDetail_Price,TV_PriceListDetail_ConsumerPrice,TV_PriceListDetail_Duration;
    private ItemClickListener itemClickListener;
    public PriceListDetailRecyclerViewHolder(@NonNull View v) {
        super(v);
        this.TV_PriceListDetail_Description =  v.findViewById(R.id.TV_PriceListDetail_Description);
        this.TV_PriceListDetail_Price =  v.findViewById(R.id.TV_PriceListDetail_Price);
        this.TV_PriceListDetail_ConsumerPrice =  v.findViewById(R.id.TV_PriceListDetail_ConsumerPrice);
        this.TV_PriceListDetail_Duration =  v.findViewById(R.id.TV_PriceListDetail_Duration);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener icl) {
        this.itemClickListener = icl;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), true);
        return true;
    }
}
