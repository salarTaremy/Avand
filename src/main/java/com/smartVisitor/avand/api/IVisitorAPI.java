package com.smartVisitor.avand.api;


import com.smartVisitor.avand.entities.Visitor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IVisitorAPI {
    @GET("Visitor/get/{Token}")
    Call<ApiResult<Visitor>> get(@Path("Token") String token);


}

