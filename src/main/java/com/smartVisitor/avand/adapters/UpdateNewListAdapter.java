package com.smartVisitor.avand.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartVisitor.avand.Holders.ItemClickListener;
import com.smartVisitor.avand.Holders.UpdateNewRecyclerViewHolder;
import com.smartVisitor.avand.R;
import com.smartVisitor.avand.classes.Update.UpdateGroup;

import java.util.List;

import vn.luongvo.widget.iosswitchview.SwitchView;
public class UpdateNewListAdapter extends RecyclerView.Adapter<UpdateNewRecyclerViewHolder>  {
    public List<UpdateGroup> Updates ;
    private Context context;
    public UpdateNewListAdapter( Context context ,  List<UpdateGroup>   Updates) {
        this.Updates = Updates;
        this.context = context;
    }

    @NonNull
    @Override
    public UpdateNewRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent,  int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_update_list_new,parent, false);
        return new UpdateNewRecyclerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final UpdateNewRecyclerViewHolder holder,final int i) {
        final UpdateGroup updt =  Updates.get(i);
        holder.tv_update_header_new.setText(updt.name) ;
        holder.tv_update_Detail_new.setText(updt.description) ;
        if (updt.id > 0 ){
            holder.tv_update_ID_new.setText(updt.id.toString());
        }else {
            holder.tv_update_ID_new.setVisibility(View.INVISIBLE);
        }
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

            }
        });
        Updates.get(i).Tv_Percentage = holder.tv_update_Percentage_new ;
        Updates.get(i).Prc = holder.PrcUpdateNew ;
        Updates.get(i).ImgUpdateOk = holder.ImgUpdateOk ;
        Updates.get(i).ImgUpdateErr = holder.ImgUpdateErr ;
    }

    @Override
    public int getItemCount() {
        return Updates.size() ;
    }

}
