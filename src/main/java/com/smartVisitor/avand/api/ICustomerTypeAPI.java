package com.smartVisitor.avand.api;

import com.smartVisitor.avand.entities.CustomerType;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICustomerTypeAPI {
    @GET("CustomerType/get/{Token}")
    Call<ApiResult<List<CustomerType>>> get(@Path("Token") String token);
}
