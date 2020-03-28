package com.smartVisitor.avand.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Warehouse {

    @SerializedName("name")
    @Expose
    public String Name;
    @SerializedName("id")
    @Expose
    public Integer ID;

    public void setName(String name) {
        Name = name;
    }

    public void setId(Integer id) {
        ID = id;
    }
}