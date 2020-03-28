package com.smartVisitor.avand.api;

import com.smartVisitor.avand.entities.CustomerGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICustomerGroupAPI {
    @GET("CustomerGroup/get/{Token}")
    Call<ApiResult<List<CustomerGroup>>> get(@Path("Token") String token);
}
