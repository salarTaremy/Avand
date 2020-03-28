package com.smartVisitor.avand.entities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("idBrand")
    @Expose
    public Integer idBrand;
    @SerializedName("idGroup")
    @Expose
    public Integer idGroup;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("countInBox")
    @Expose
    public Integer countInBox;
    @SerializedName("weight")
    @Expose
    public Integer weight;
    @SerializedName("detail")
    @Expose
    public String detail;
    @SerializedName("rate")
    @Expose
    public Integer rate;

    public Integer SelectedCount;
    public Integer SelectedOffer;
    public Integer SelectedTax;
    public Integer SelectedPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Integer idBrand) {
        this.idBrand = idBrand;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCountInBox() {
        return countInBox;
    }

    public void setCountInBox(Integer countInBox) {
        this.countInBox = countInBox;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

}
