package com.smartVisitor.avand.api;

import com.smartVisitor.avand.entities.Customer;
import com.smartVisitor.avand.ViewModels.CustomerViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICustomerAPI {
    @GET("Customer/get/{Token}")
    Call<ApiResult<List<Customer>>>get(@Path("Token") String token);

    @GET("Customer/getWithDependency/{Token}")
    Call<ApiResult<CustomerViewModel>> getWithDependency(@Path("Token") String token);

    @GET("Customer/get/{Token}/{id}")
    Call<ApiResult<Customer>> get(@Path("Token") String token, @Path("id") int id);

}
