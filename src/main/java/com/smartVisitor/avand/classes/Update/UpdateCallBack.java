package com.smartVisitor.avand.classes.Update;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class   UpdateCallBack<T> implements Callback<T> {
    UpdateDetail updateDetail ;
    Context context;
    public UpdateCallBack(Context context, UpdateDetail updateDetail) {
        this.context = context;
        this.updateDetail = updateDetail;
    }
    public UpdateCallBack() {
        this.updateDetail = null;
    }
    protected abstract void onResponseIsSuccessful(Response<T> response);
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            if (response.isSuccessful()) {

                onResponseIsSuccessful(response);

            } else {
                if (this.updateDetail != null){
                    //SetUpdateError(updateDetail);
                }
                Toast.makeText( context , response.errorBody().string(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            if (this.updateDetail != null){
                //SetUpdateError(updateDetail);
            }
            Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (this.updateDetail != null){
            //SetUpdateError(updateDetail);
        }
        Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}