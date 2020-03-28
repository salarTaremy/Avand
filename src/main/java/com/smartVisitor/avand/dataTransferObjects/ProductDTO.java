package com.smartVisitor.avand.dataTransferObjects;

import com.smartVisitor.avand.entities.PriceListDetail;
import com.smartVisitor.avand.entities.Product;

import java.io.Serializable;

public class ProductDTO extends Product implements Serializable {
    public ProductDTO() {
        this.priceListDetail = new PriceListDetail();
    }

    public  Integer Inventory;
    public  Integer WarehouseCount;
    public PriceListDetail priceListDetail;
}
