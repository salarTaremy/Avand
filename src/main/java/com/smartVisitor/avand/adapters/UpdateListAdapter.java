package com.smartVisitor.avand.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.Holders.ItemClickListener;
import com.smartVisitor.avand.Holders.UpdateRecyclerViewHolder;
import com.smartVisitor.avand.activities.UpdateActivity;
import com.smartVisitor.avand.classes.Update.UpdateGroup;


import java.util.List;


public class UpdateListAdapter extends RecyclerView.Adapter<UpdateRecyclerViewHolder>  {

    private  List<UpdateGroup> Updates ;
    private Context context;

    public UpdateListAdapter( Context context ,  List<UpdateGroup>   Updates) {
        this.Updates = Updates;
        this.context = context;
    }

    @NonNull
    @Override
    public UpdateRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_update_list,parent, false);
        return new UpdateRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateRecyclerViewHolder holder, int i) {
        final UpdateGroup updt =  Updates.get(i);
        holder.tv_UpdateHeader.setText(updt.name) ;
        holder.tv_UpdateDetail.setText(updt.description) ;
        holder.tv_UpdateId.setText(updt.id.toString());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

               // MyDictionary Dic = customers.get(position);
                if (isLongClick) {
                     //Code

                } else {
                    ((UpdateActivity)context).Update(updt.id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return Updates.size() ;
    }
}
