package com.smartVisitor.avand.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriceList  implements Serializable {


    public PriceList() {
        this.priceListDetails = new ArrayList<PriceListDetail>();
    }

    @SerializedName("beginDate")
    @Expose
    public Integer BeginDate;

    @SerializedName("endDate")
    @Expose
    public Integer EndDate;

    @SerializedName("description")
    @Expose
    public String Description;

    @SerializedName("priceListDetails")
    @Expose
    public List<PriceListDetail> priceListDetails ;

    @SerializedName("id")
    @Expose
    public Integer ID;


}
