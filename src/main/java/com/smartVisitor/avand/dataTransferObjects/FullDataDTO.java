package com.smartVisitor.avand.dataTransferObjects;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartVisitor.avand.entities.Customer;
import com.smartVisitor.avand.entities.CustomerGroup;
import com.smartVisitor.avand.entities.CustomerType;
import com.smartVisitor.avand.entities.Inventory;
import com.smartVisitor.avand.entities.LinkPriceListCustomer;
import com.smartVisitor.avand.entities.MyCalendar;
import com.smartVisitor.avand.entities.PriceList;
import com.smartVisitor.avand.entities.Product;
import com.smartVisitor.avand.entities.ProductBrand;
import com.smartVisitor.avand.entities.ProductGroup;
import com.smartVisitor.avand.entities.Warehouse;

import java.io.Serializable;
import java.util.List;
public class FullDataDTO  implements Serializable {

    Context context;

    public FullDataDTO(Context context)  {
        this.context = context;
    }
    @SerializedName("myCalendars")
    @Expose
    public List<MyCalendar> MyCalendars ;

    @SerializedName("customers")
    @Expose
    public List<Customer> Customers ;


    @SerializedName("customerTypes")
    @Expose
    public List<CustomerType> CustomerTypes ;

    @SerializedName("customerGroups")
    @Expose
    public List<CustomerGroup> CustomerGroups;

    @SerializedName("productBrands")
    @Expose
    public List<ProductBrand> ProductBrands ;

    @SerializedName("productGroups")
    @Expose
    public List<ProductGroup> ProductGroups ;

    @SerializedName("products")
    @Expose
    public List<Product> Products ;

    @SerializedName("warehouses")
    @Expose
    public List<Warehouse> Warehouses ;

    @SerializedName("inventories")
    @Expose
    public List<Inventory> inventories ;

    @SerializedName("priceLists")
    @Expose
    public List<PriceList> PriceLists ;

    @SerializedName("linkPriceListCustomer")
    @Expose
    public List<LinkPriceListCustomer> LinkPriceListCustomer ;

}
