package com.smartVisitor.avand.Holders;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.smartVisitor.avand.R;
public class OrderItemRecyclerViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    public TextView tv_order_Item_Name , tv_order_Item_Qty , tv_order_Item_Rls, tv_order_Item_Total,tv_order_Item_Code;
    private ItemClickListener itemClickListener;
    public CheckBox Chk_OrderItem;
    public OrderItemRecyclerViewHolder(@NonNull View v) {
        super(v);
        this.tv_order_Item_Name =  v.findViewById(R.id.tv_order_Item_Name);
        this.tv_order_Item_Rls = v.findViewById(R.id.tv_order_Item_Rls);
        this.tv_order_Item_Qty = v.findViewById(R.id.tv_order_Item_Qty);
        this.tv_order_Item_Total = v.findViewById(R.id.tv_order_Item_Total);
        this.tv_order_Item_Code = v.findViewById(R.id.tv_order_Item_Code);
        this.Chk_OrderItem = v.findViewById(R.id.Chk_OrderItem);
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
