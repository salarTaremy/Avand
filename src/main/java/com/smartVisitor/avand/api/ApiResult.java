package com.smartVisitor.avand.api;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResult<Tdata> {

    @SerializedName("data")
    @Expose
    private Tdata data;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;

    public Tdata getData() {
        return data;
    }

    public void setData(Tdata data) {
        this.data = data;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}