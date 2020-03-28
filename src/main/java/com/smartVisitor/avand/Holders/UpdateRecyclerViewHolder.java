package com.smartVisitor.avand.Holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartVisitor.avand.R;

public class UpdateRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView tv_UpdateHeader, tv_UpdateDetail, tv_UpdateId;
    public ImageView img_UpdateItem;
    private ItemClickListener itemClickListener;

    public UpdateRecyclerViewHolder(@NonNull View v){
        super(v);
        this.tv_UpdateHeader =  v.findViewById(R.id.tv_UpdateHeader);
        this.tv_UpdateDetail =  v.findViewById(R.id.tv_UpdateDetail);
        this.tv_UpdateId =  v.findViewById(R.id.tv_UpdateId);
        this.img_UpdateItem =  v.findViewById(R.id.img_UpdateItem);

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

