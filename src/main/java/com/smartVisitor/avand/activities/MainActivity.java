package com.smartVisitor.avand.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.smartVisitor.avand.R;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    ImageView img_visit_path;
    ImageView img_view_orders;
    ImageView img_product_info;
    ImageView img_catalog;
    ImageView img_customer;
    ImageView img_visitor_info;
    ImageView img_update;
    ImageView img_settings;
    ImageView img_visit_path_Total;
    ImageView img_Tracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setEventListener();
        GetPermission();
    }

    private void GetPermission() {
        ActivityCompat.requestPermissions(this, new String[]
            {Manifest.permission.INTERNET
                , Manifest.permission.VIBRATE
                , Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.CALL_PHONE
                , Manifest.permission.INTERNET
                , Manifest.permission.ACCESS_COARSE_LOCATION
                , Manifest.permission.ACCESS_FINE_LOCATION
                , Manifest.permission.ACCESS_NETWORK_STATE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 7);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setEventListener() {
        this.img_visit_path.setOnTouchListener(this);
        this.img_view_orders.setOnTouchListener(this);
        this.img_product_info.setOnTouchListener(this);
        this.img_catalog.setOnTouchListener(this);
        this.img_customer.setOnTouchListener(this);
        this.img_visitor_info.setOnTouchListener(this);
        this.img_update.setOnTouchListener(this);
        this.img_settings.setOnTouchListener(this);
        this.img_visit_path_Total.setOnTouchListener(this);
        this.img_Tracking.setOnTouchListener(this);
    }

    private void initView() {
        img_visit_path = findViewById(R.id.img_visit_path);
        img_view_orders = findViewById(R.id.img_view_orders);
        img_product_info = findViewById(R.id.img_product_info);
        img_catalog = findViewById(R.id.img_catalog);
        img_customer = findViewById(R.id.img_customer);
        img_visitor_info = findViewById(R.id.img_visitor_info);
        img_update = findViewById(R.id.img_update);
        img_settings = findViewById(R.id.img_settings);
        img_visit_path_Total = findViewById(R.id.img_visit_path_Total);
        img_Tracking = findViewById(R.id.img_Tracking);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //int eventaction = event.getAction();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (v instanceof ImageView) {
                    ImageView img = (ImageView) v;
                    img.setColorFilter(R.color.colorPrimary);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (v instanceof ImageView) {
                    ImageView img = (ImageView) v;
                    img.setColorFilter(null);
                }
                onClick(v);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == img_visit_path) {
            Intent intent = new Intent(this, CustomerListActivity.class);
            intent.putExtra("CallFromOrder", true);
            startActivity(intent);
        } else if (v == img_view_orders) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        } else if (v == img_product_info) {
            Intent intent = new Intent(this, ProductListActivity.class);
            startActivity(intent);
        } else if (v == img_catalog) {

        }
        else if(  v == img_customer){
            Intent intent = new Intent(this, CustomerListActivity.class);
            startActivity(intent);
        }
        else if(  v == img_visitor_info){
            Intent intent = new Intent(this, VisitorInfoActivity.class);
            startActivity(intent);
        }
        else if(  v == img_update){
            //Intent intent = new Intent(this, UpdateActivity.class);
            Intent intent = new Intent(this, NewUpdateActivity.class);
            startActivity(intent);
        }
        else if(  v == img_settings){


        }
        else if(  v == img_Tracking){
            Intent intent = new Intent(this, TrackingActivity.class);
            startActivity(intent);
        }
        else if(  v == img_visit_path_Total){
            Intent intent = new Intent(this, DailyVisitPlanActivity.class);
            startActivity(intent);
        }
    }
}

