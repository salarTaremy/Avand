package com.smartVisitor.avand.api;

import com.smartVisitor.avand.entities.Product;
import com.smartVisitor.avand.ViewModels.ProductViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProductAPI {
    @GET("product/get/{Token}")
    Call<ApiResult<List<Product>>> get(@Path("Token") String token);

    @GET("product/getWithDependency/{Token}")
    Call<ApiResult<ProductViewModel>> getWithDependency(@Path("Token") String token);

    @GET("product/get/{Token}/{id}")
    Call<ApiResult<Product>> get(@Path("Token") String token,@Path("id") int id);
}
