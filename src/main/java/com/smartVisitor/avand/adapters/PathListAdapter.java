package com.smartVisitor.avand.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.smartVisitor.avand.Holders.DailyVisitPlanRecyclerViewHolder;
import com.smartVisitor.avand.entities.DailyVisitPlan;

import java.util.List;

public class PathListAdapter extends RecyclerView.Adapter<DailyVisitPlanRecyclerViewHolder> {

    private List<DailyVisitPlan> customers;
    private Context context;

    @NonNull
    @Override
    public DailyVisitPlanRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyVisitPlanRecyclerViewHolder dailyVisitPlanRecyclerViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return this.customers.size();
    }
}
