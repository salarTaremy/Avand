package com.smartVisitor.avand.api;

import com.smartVisitor.avand.entities.Warehouse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IWarehouseAPI {
    @GET("Warehouse/get/{Token}")
    Call<ApiResult<List<Warehouse>>> get(@Path("Token") String token);
}
