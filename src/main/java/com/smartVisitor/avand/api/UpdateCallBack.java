package com.smartVisitor.avand.api;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract class   UpdateCallBack<T> implements Callback<T> {
    Context context;
    public UpdateCallBack(Context context) {
        this.context = context;
    }
    abstract void onResponseIsSuccessful(Response<T> response);
    abstract void OnError();
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            if (response.isSuccessful()) {

                onResponseIsSuccessful(response);

            } else {
                OnError();
                Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            OnError();
            Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        OnError();
        Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}