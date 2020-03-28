package com.smartVisitor.avand.activities;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.api.APIClient;
import com.smartVisitor.avand.api.ApiResult;
import com.smartVisitor.avand.api.IVisitorAPI;
import com.smartVisitor.avand.classes.Global;
import com.smartVisitor.avand.entities.Visitor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorInfoActivity extends AppCompatActivity {
    private Context context;
    private TextView tv_id_visitor ;
    private TextView tv_code_visitor ;
    private TextView tv_Name_visitor ;
    private TextView tv_ssn_visitor ;
    private TextView tv_Credit_visitor ;
    private TextView tv_more_info_visitor ;


    private FloatingActionButton fab    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = this ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_info);
        initView();
        findVisitor();
    }

    private void initView() {
        tv_id_visitor = findViewById(R.id.tv_id_visitor);
        tv_code_visitor = findViewById(R.id.tv_code_visitor);
        tv_Name_visitor = findViewById(R.id.tv_Name_visitor);
        tv_ssn_visitor = findViewById(R.id.tv_ssn_visitor);
        tv_Credit_visitor = findViewById(R.id.tv_Credit_visitor);
        tv_more_info_visitor = findViewById(R.id.tv_more_info_visitor);
        this.fab = findViewById(R.id.fab_Visitor_Refresh);


        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findVisitor();
            }
        });
    }

    public  void  findVisitor(){
        IVisitorAPI Api = APIClient.getClient().create(IVisitorAPI.class);
        Api.get(Global.IMEI ).enqueue(new Callback<ApiResult<Visitor>>() {
            @Override
            public void onResponse(Call<ApiResult<Visitor>> call, Response<ApiResult<Visitor>> response) {
                try {
                    if (response.isSuccessful()) {
                        Visitor v = response.body().getData();
                        if (  v == null  ){
                            Toast.makeText(context,   context.getString(  R.string.Connection_Failed), Toast.LENGTH_LONG).show();
                        } else {
                        LoadDate(v);
                        }
                    } else {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ApiResult<Visitor>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void LoadDate(Visitor v) {
        tv_id_visitor.setText(String.valueOf(v.getId()));
        tv_code_visitor.setText(v.getCode());
        tv_Name_visitor.setText(v.getName());
        tv_ssn_visitor.setText(v.getSsn());
        tv_Credit_visitor.setText("999,999,999,999");
        tv_more_info_visitor.setText(v.getDescription());
    }


}
