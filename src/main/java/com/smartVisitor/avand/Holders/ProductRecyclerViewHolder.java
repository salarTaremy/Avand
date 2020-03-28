package com.smartVisitor.avand.Holders;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartVisitor.avand.R;
public class ProductRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    public TextView tv_Id , tv_Code , tv_Name, tv_Weight,tv_Count,tv_Price,tv_currency,tv_Tax,tv_Detail,tv_selectedCount;
    public ImageView Img ;
    private ItemClickListener itemClickListener;
    public ProductRecyclerViewHolder(@NonNull View v) {
        super(v);
        this.tv_Id =  v.findViewById(R.id.tv_Id);
        this.tv_Code = v.findViewById(R.id.tv_Code);
        this.tv_Name = v.findViewById(R.id.tv_Name_visitor);
        this.tv_Weight = v.findViewById(R.id.tv_Weight);
        this.tv_Count = v.findViewById(R.id.tv_Count);
        this.tv_Price = v.findViewById(R.id.tv_Price);
        this.tv_currency = v.findViewById(R.id.tv_currency);
        this.tv_Tax = v.findViewById(R.id.tv_Tax);
        this.tv_Detail = v.findViewById(R.id.tv_Detail);
        this.tv_selectedCount = v.findViewById(R.id.tv_selectedCount);
        this.Img = v.findViewById(R.id.Img_CheckForOrder);
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

