package com.smartVisitor.avand.entities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PriceListDetail implements Serializable {

    @SerializedName("iD_PriceList")
    @Expose
    public Integer ID_PriceList;
    @SerializedName("iD_Product")
    @Expose
    public Integer ID_Product;
    @SerializedName("price")
    @Expose
    public Integer Price;
    @SerializedName("consumerPrice")
    @Expose
    public Integer ConsumerPrice;
    @SerializedName("tax")
    @Expose
    public Integer Tax;
    @SerializedName("duration")
    @Expose
    public Integer Duration;
    @SerializedName("id")
    @Expose
    public Integer ID;

    public void setID_PriceList(Integer ID_PriceList) {
        this.ID_PriceList = ID_PriceList;
    }

    public void setiD_Product(Integer iD_Product) {
        this.ID_Product = iD_Product;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public void setConsumerPrice(Integer consumerPrice) {
        ConsumerPrice = consumerPrice;
    }

    public void setTax(Integer tax) {
        Tax = tax;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}