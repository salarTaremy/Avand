package com.smartVisitor.avand.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyVisitPlan {

    @SerializedName("iD_Visitor")
    @Expose
    public Integer ID_Visitor;
    @SerializedName("prDay")
    @Expose
    public Integer PrDay;
    @SerializedName("dailyVisitPlanDetails")
    @Expose
    public List<DailyVisitPlanDetail> DailyVisitPlanDetails = null;
    @SerializedName("id")
    @Expose
    public Integer ID;
}
