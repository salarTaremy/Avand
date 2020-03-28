package com.smartVisitor.avand.api;

import com.smartVisitor.avand.dataTransferObjects.FullDataDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IFullDataAPI {
    @GET("FullData/get/{Token}")
    Call<FullDataDTO> get(@Path("Token") String token);
}




