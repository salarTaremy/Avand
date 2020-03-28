package com.smartVisitor.avand.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.Holders.PriceListDetailRecyclerViewHolder;
import com.smartVisitor.avand.classes.Convert;
import com.smartVisitor.avand.dataTransferObjects.PriceListDetailDTO;

import java.util.List;

public class PriceListDetailAdapter extends RecyclerView.Adapter<PriceListDetailRecyclerViewHolder>  {
    private List<PriceListDetailDTO> Items;
    private Context context;
    private  boolean CallFromOrder;
    public PriceListDetailAdapter(Context context, List<PriceListDetailDTO> Items ) {
        this.context = context;
        this.Items = Items;
        this.CallFromOrder =  false;
    }
    @NonNull
    @Override
    public PriceListDetailRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_price_list_detail, parent,false);
        return new PriceListDetailRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceListDetailRecyclerViewHolder holder, int i) {
        PriceListDetailDTO obj = this.Items.get(i);
        holder.TV_PriceListDetail_Description.setText(obj.Description);
        holder.TV_PriceListDetail_Price.setText(Convert.toSeprate(obj.Price));
        holder.TV_PriceListDetail_ConsumerPrice.setText(Convert.toSeprate(obj.ConsumerPrice));
        holder.TV_PriceListDetail_Duration.setText(Convert.toSeprate(obj.Duration));
    }

    @Override
    public int getItemCount() {
        return this.Items.size();
    }
}
