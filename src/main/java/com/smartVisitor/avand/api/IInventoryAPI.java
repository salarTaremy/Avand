package com.smartVisitor.avand.api;

import com.smartVisitor.avand.entities.Inventory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IInventoryAPI {
    @GET("Inventory/get/{Token}")
    Call<ApiResult<List<Inventory>>> get(@Path("Token") String token);
}
