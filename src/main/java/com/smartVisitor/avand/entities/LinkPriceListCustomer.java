package com.smartVisitor.avand.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkPriceListCustomer {
    @SerializedName("iD_PriceList")
    @Expose
    public Integer ID_PriceList;
    @SerializedName("iD_CustomerType")
    @Expose
    public Integer ID_CustomerType;
    @SerializedName("iD_CustomerGroup")
    @Expose
    public Integer ID_CustomerGroup;
    @SerializedName("id")
    @Expose
    public Integer ID;
}


