package com.smartVisitor.avand.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("f_Name")
    @Expose
    public String fName;
    @SerializedName("l_name")
    @Expose
    public String lName;
    @SerializedName("idType")
    @Expose
    public Integer idType;
    @SerializedName("idGroup")
    @Expose
    public Integer idGroup;
    @SerializedName("tel")
    @Expose
    public String tel;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("mail")
    @Expose
    public String mail;
    @SerializedName("openInvoice")
    @Expose
    public Integer openInvoice;
    @SerializedName("debt")
    @Expose
    public Long debt;
    @SerializedName("avgDate")
    @Expose
    public Integer avgDate;
    @SerializedName("rate")
    @Expose
    public Integer rate;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("x")
    @Expose
    public String x;
    @SerializedName("y")
    @Expose
    public String y;

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

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getOpenInvoice() {
        return openInvoice;
    }

    public void setOpenInvoice(Integer openInvoice) {
        this.openInvoice = openInvoice;
    }

    public Long getDebt() {
        return debt;
    }

    public void setDebt(Long debt) {
        this.debt = debt;
    }

    public Integer getAvgDate() {
        return avgDate;
    }

    public void setAvgDate(Integer avgDate) {
        this.avgDate = avgDate;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }


}