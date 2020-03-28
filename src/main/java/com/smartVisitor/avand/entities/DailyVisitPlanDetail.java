package com.smartVisitor.avand.entities;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyVisitPlanDetail {

    @SerializedName("iD_Plan")
    @Expose
    public Integer ID_Plan;
    @SerializedName("iD_Customer")
    @Expose
    public Integer ID_Customer;
    @SerializedName("id")
    @Expose
    public Integer ID;

}