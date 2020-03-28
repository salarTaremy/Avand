package com.smartVisitor.avand.entities;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartVisitor.avand.ViewModels.OrderCellInfoViewModel;
import com.smartVisitor.avand.classes.MyCellInfo;
import com.smartVisitor.avand.database.GeneralDbHelper;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order  implements Serializable {
    public Order(){
        this.Items = new ArrayList<OrderItem>();
    }

    @SerializedName("UUID")
    @Expose
    public String UUID;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("PaymentType")
    @Expose
    public Integer PaymentType;

    @SerializedName("Type")
    @Expose
    public Integer Type;

    @SerializedName("ClientDate")
    @Expose
    public Integer ClientDate;

    @SerializedName("ClientTime")
    @Expose
    public Integer ClientTime;

    @SerializedName("Description")
    @Expose
    public String Description;

    @SerializedName("Items")
    @Expose
    public List<OrderItem> Items;

    @SerializedName("customer")
    @Expose
    public  Customer customer;

    @SerializedName("priceList")
    @Expose
    public  PriceList priceList;

    @SerializedName("Latitude")
    @Expose
    public  double Latitude;

    @SerializedName("Longitude")
    @Expose
    public  double Longitude;

    public List<MyCellInfo> MyCells;


}
