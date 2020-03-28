package com.smartVisitor.avand.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inventory {

    @SerializedName("iD_Warehouse")
    @Expose
    public Integer ID_Warehouse;
    @SerializedName("iD_Product")
    @Expose
    public Integer ID_Product;
    @SerializedName("quantity")
    @Expose
    public Integer Quantity;
    @SerializedName("id")
    @Expose
    public Integer ID;

}