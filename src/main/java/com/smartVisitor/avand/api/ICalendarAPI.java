package com.smartVisitor.avand.api;


import com.smartVisitor.avand.entities.MyCalendar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICalendarAPI {
    @GET("Calendar/get")
    Call<ApiResult<List<MyCalendar>>> get();
}