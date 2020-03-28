package com.smartVisitor.avand.api;

import com.smartVisitor.avand.ViewModels.PriceListViewModel;
import com.smartVisitor.avand.entities.LinkPriceListCustomer;
import com.smartVisitor.avand.entities.PriceList;
import com.smartVisitor.avand.entities.PriceListDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IPriceListApi {
    @GET("PriceList/get/{Token}/{id}")
    Call<ApiResult<PriceList>> get(@Path("Token") String token, @Path("id") int id);

    @GET("PriceList/get/{Token}")
    Call<ApiResult<List<PriceList>>> get(@Path("Token") String token);

    @GET("PriceList/GetWithDependency/{Token}")
    Call<ApiResult<PriceListViewModel>> GetWithDependency(@Path("Token") String token);

    @GET("PriceList/GetHeaders/{Token}")
    Call<ApiResult<List<PriceList>>> GetHeaders(@Path("Token") String token);

    @GET("PriceList/GetDetails/{Token}")
    Call<ApiResult<List<PriceListDetail>>> GetDetails(@Path("Token") String token);

    @GET("PriceList/GetLinkPriceListCustomer/{Token}")
    Call<ApiResult<List<LinkPriceListCustomer>>> GetLinkPriceListCustomer(@Path("Token") String token);



}
