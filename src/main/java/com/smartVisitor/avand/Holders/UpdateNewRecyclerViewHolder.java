package com.smartVisitor.avand.Holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.smartVisitor.avand.R;

import vn.luongvo.widget.iosswitchview.SwitchView;

public class UpdateNewRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView tv_update_header_new, tv_update_Detail_new,tv_update_ID_new,tv_update_Percentage_new;
    public ProgressBar PrcUpdateNew;
    private ItemClickListener itemClickListener;
    public ImageView ImgUpdateOk;
    public ImageView ImgUpdateErr;


    public UpdateNewRecyclerViewHolder(@NonNull View v) {
        super(v);
        this.tv_update_header_new =  v.findViewById(R.id.tv_update_header_new);
        this.tv_update_Detail_new =  v.findViewById(R.id.tv_update_Detail_new);
        this.tv_update_ID_new =  v.findViewById(R.id.tv_update_ID_new);
        this.PrcUpdateNew =  v.findViewById(R.id.PrcUpdateNew);
        this.ImgUpdateOk  =  v.findViewById(R.id.ImgUpdateOk);
        this.ImgUpdateErr  =  v.findViewById(R.id.ImgUpdateErr);
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
