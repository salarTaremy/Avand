package com.smartVisitor.avand.api;

import com.smartVisitor.avand.entities.ProductBrand;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProductBrandAPI {
    @GET("productBrand/get/{Token}")
    Call<ApiResult<List<ProductBrand>>> get(@Path("Token") String token);
}
