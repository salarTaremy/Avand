package com.smartVisitor.avand.Holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smartVisitor.avand.R;

public class CustomerRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

    public TextView tv_CustomerId,tv_CustomerCode,tv_CustomerName,tv_CustomerDetail,tv_CustomerPhone,tv_CustomerMobile,tv_CustomerMail;
    private ItemClickListener itemClickListener;
    public CustomerRecyclerViewHolder(@NonNull View v) {
        super(v);
        this.tv_CustomerId =  v.findViewById(R.id.tv_UpdateId);
        this.tv_CustomerCode =  v.findViewById(R.id.tv_CustomerCode);
        this.tv_CustomerName =  v.findViewById(R.id.tv_CustomerName);
        this.tv_CustomerDetail =  v.findViewById(R.id.tv_CustomerDetail);
        this.tv_CustomerPhone =  v.findViewById(R.id.tv_CustomerPhone);
        this.tv_CustomerMobile =  v.findViewById(R.id.tv_CustomerMobile);
        this.tv_CustomerMail =  v.findViewById(R.id.tv_CustomerMail);
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
