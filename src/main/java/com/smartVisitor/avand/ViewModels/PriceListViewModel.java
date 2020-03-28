package com.smartVisitor.avand.ViewModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartVisitor.avand.entities.LinkPriceListCustomer;
import com.smartVisitor.avand.entities.PriceList;

import java.util.List;

public class PriceListViewModel {

    @SerializedName("priceLists")
    @Expose
    public List<PriceList> PriceLists = null;
    @SerializedName("links")
    @Expose
    public List<LinkPriceListCustomer> Links = null;
}
