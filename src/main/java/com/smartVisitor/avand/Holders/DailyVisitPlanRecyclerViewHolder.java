package com.smartVisitor.avand.Holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smartVisitor.avand.R;

public class DailyVisitPlanRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    private  TextView Tv_DailyVisitPlanItem_Count,Tv_DailyVisitPlanItem_Date;
    private ItemClickListener itemClickListener;
    public DailyVisitPlanRecyclerViewHolder(@NonNull View v) {
        super(v);
        this.Tv_DailyVisitPlanItem_Count =  v.findViewById(R.id.Tv_DailyVisitPlanItem_Count);
        this.Tv_DailyVisitPlanItem_Date =  v.findViewById(R.id.Tv_DailyVisitPlanItem_Date);
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
