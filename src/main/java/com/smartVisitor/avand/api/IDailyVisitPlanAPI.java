package com.smartVisitor.avand.api;

import com.smartVisitor.avand.entities.DailyVisitPlan;
import com.smartVisitor.avand.entities.DailyVisitPlanDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IDailyVisitPlanAPI {
    @GET("DailyVisitPlan/get/{Token}")
    Call<ApiResult<List<DailyVisitPlan>>> get(@Path("Token") String token);
    @GET("DailyVisitPlan/get/{Token}/{Date}")
    Call<ApiResult<DailyVisitPlan>> get(@Path("Token") String token, @Path("Date") int Date);
    @GET("DailyVisitPlan/getHeader/{Token}")
    Call<ApiResult<List<DailyVisitPlan>>> getHeader(@Path("Token") String token);
    @GET("DailyVisitPlan/getDetail/{Token}")
    Call<ApiResult<List<DailyVisitPlanDetail>>> getDetail(@Path("Token") String token);

}
