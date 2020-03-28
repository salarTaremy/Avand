package com.smartVisitor.avand.Holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smartVisitor.avand.R;

public class DictionaryListRecyclerViewHolder extends RecyclerView.ViewHolder {
     public TextView tv_Key , tv_Value ;

    public DictionaryListRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tv_Key = itemView.findViewById(R.id.tv_Key);
        this.tv_Value = itemView.findViewById(R.id.tv_Value);
    }
}
