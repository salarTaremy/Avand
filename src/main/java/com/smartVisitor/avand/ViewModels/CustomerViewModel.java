
package com.smartVisitor.avand.ViewModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.smartVisitor.avand.entities.Customer;
import com.smartVisitor.avand.entities.CustomerGroup;
import com.smartVisitor.avand.entities.CustomerType;

import java.io.Serializable;
import java.util.List;

public class CustomerViewModel implements Serializable {

    @SerializedName("customers")
    @Expose
    private List<Customer> customers = null;
    @SerializedName("customerTypes")
    @Expose
    private List<CustomerType> customerTypes = null;
    @SerializedName("customerGroups")
    @Expose
    private List<CustomerGroup> customerGroups = null;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<CustomerType> getCustomerTypes() {
        return customerTypes;
    }

    public void setCustomerTypes(List<CustomerType> customerTypes) {
        this.customerTypes = customerTypes;
    }

    public List<CustomerGroup> getCustomerGroups() {
        return customerGroups;
    }

    public void setCustomerGroups(List<CustomerGroup> customerGroups) {
        this.customerGroups = customerGroups;
    }

}