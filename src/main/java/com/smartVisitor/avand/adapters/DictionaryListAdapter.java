package com.smartVisitor.avand.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.Holders.DictionaryListRecyclerViewHolder;
import com.smartVisitor.avand.classes.MyDictionary;

import java.util.List;

public class DictionaryListAdapter  extends RecyclerView.Adapter<DictionaryListRecyclerViewHolder>  {

    private  List<MyDictionary> MyDics ;
    private Context context;

    public DictionaryListAdapter( Context context ,  List<MyDictionary>   dic) {
        this.MyDics = dic;
        this.context = context;
    }

    @NonNull
    @Override
    public DictionaryListRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dictionary_list,parent, false);
        return new DictionaryListRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryListRecyclerViewHolder holder, int i) {
        MyDictionary dic = MyDics.get(i);
        holder.tv_Key.setText(dic.getKey()) ;
        holder.tv_Value.setText(dic.getValue()) ;
    }

    @Override
    public int getItemCount() {
        return MyDics.size() ;
    }
}
