package com.smartVisitor.avand.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyCalendar  implements Serializable {
    @SerializedName("prDate")
    @Expose
    private Integer prDate;
    @SerializedName("grDate")
    @Expose
    private Integer grDate;
    @SerializedName("dayOfWeek")
    @Expose
    private Integer dayOfWeek;
    @SerializedName("dayCounter")
    @Expose
    private Integer dayCounter;

    public Integer getPrDate() {
        return prDate;
    }

    public void setPrDate(Integer prDate) {
        this.prDate = prDate;
    }

    public Integer getGrDate() {
        return grDate;
    }

    public void setGrDate(Integer grDate) {
        this.grDate = grDate;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(Integer dayCounter) {
        this.dayCounter = dayCounter;
    }
}
