package com.smartVisitor.avand.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderItem   implements Serializable {


    public Integer ID ;
    public Integer ID_order ;
    @SerializedName("IDPr")
    @Expose
    public Integer ID_Product ;
    public String ProductCode ;
    public String ProductName ;
    @SerializedName("Qty")
    @Expose
    public Integer Qty ;
    public Integer Offer ;
    public Integer Price ;
    public Integer Tax ;
    public boolean IsChecked;


    public OrderItem() {

    }
    public OrderItem(Integer ID, Integer ID_order,Product product) {
        this.ID = ID;
        this.ID_order = ID_order;
        this.ID_Product = product.id;
        ProductCode = product.code;
        ProductName = product.name;
        Qty = product.SelectedCount;
        Offer = product.SelectedOffer;
        Price = product.SelectedPrice;
        Tax = product.SelectedTax;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID_order() {
        return ID_order;
    }

    public void setID_order(Integer ID_order) {
        this.ID_order = ID_order;
    }

    public Integer getID_Product() {
        return ID_Product;
    }

    public void setID_Product(Integer ID_Product) {
        this.ID_Product = ID_Product;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }

    public Integer getOffer() {
        return Offer;
    }

    public void setOffer(Integer offer) {
        Offer = offer;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public boolean isChecked() {
        return IsChecked;
    }

    public void setChecked(boolean checked) {
        IsChecked = checked;
    }
}
