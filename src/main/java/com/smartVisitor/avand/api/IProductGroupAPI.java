package com.smartVisitor.avand.api;

import com.smartVisitor.avand.entities.ProductGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProductGroupAPI {
    @GET("productGroup/get/{Token}")
    Call<ApiResult<List<ProductGroup>>> get(@Path("Token") String token);
}
