package com.smartVisitor.avand.ViewModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartVisitor.avand.entities.Product;
import com.smartVisitor.avand.entities.ProductBrand;
import com.smartVisitor.avand.entities.ProductGroup;

import java.util.List;

public class ProductViewModel {

    @SerializedName("productBrands")
    @Expose
    private List<ProductBrand> productBrands = null;
    @SerializedName("productGroups")
    @Expose
    private List<ProductGroup> productGroups = null;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    public List<ProductBrand> getProductBrands() {
        return productBrands;
    }

    public void setProductBrands(List<ProductBrand> productBrands) {
        this.productBrands = productBrands;
    }

    public List<ProductGroup> getProductGroups() {
        return productGroups;
    }

    public void setProductGroups(List<ProductGroup> productGroups) {
        this.productGroups = productGroups;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
