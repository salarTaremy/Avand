package com.smartVisitor.avand.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.adapters.PriceListDetailAdapter;
import com.smartVisitor.avand.api.APIClient;
import com.smartVisitor.avand.api.ApiResult;
import com.smartVisitor.avand.api.IProductAPI;
import com.smartVisitor.avand.classes.Convert;
import com.smartVisitor.avand.classes.Global;
import com.smartVisitor.avand.dataTransferObjects.PriceListDetailDTO;
import com.smartVisitor.avand.database.GeneralDbHelper;
import com.smartVisitor.avand.database.ProductBrandIDbHelper;
import com.smartVisitor.avand.database.ProductDbHelper;
import com.smartVisitor.avand.database.ProductGroupDbHelper;
import com.smartVisitor.avand.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetailActivity extends AppCompatActivity  {
    private TextView tv_Id				;
    private TextView tv_Code			;
    private TextView tv_Name			;
    private TextView tv_Brand			;
    private TextView tv_Group			;
    private TextView tv_Price			;
    private TextView tv_ConsumerPrice	;
    private TextView tv_Count			;
    private TextView tv_CountInBox		;
    private TextView tv_Weight			;
    private TextView tv_Duration		;
    private TextView tv_Detail			;
    private TextView tv_Header			;
    private RatingBar ratingBar			;
    private int product_id              ;
    private Product product             ;
    private FloatingActionButton fab    ;
    public Context context              ;
    private  RecyclerView rv;

    public ProductDetailActivity() {
        this.context = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
        loadData();
        loadPriceList(product_id);
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findProduct(product_id);
            }
        });
    }



    private void initView() {
        ProductDbHelper db          = new ProductDbHelper(this);
        //this.context                = this.getApplicationContext();
        this.product_id             = getIntent().getIntExtra("Product_Id",0);
        this.product                =  db.find(this.product_id);
        this.tv_Id					= findViewById(R.id.tv_id_visitor);
        this.tv_Code				= findViewById(R.id.tv_code_visitor);
        this.tv_Name				= findViewById(R.id.tv_Name_visitor);
        this.tv_Group				= findViewById(R.id.tv_Credit_visitor);
        this.tv_Brand				= findViewById(R.id.tv_ssn_visitor);
        //this.tv_Price				= findViewById(R.id.tv_price_visitor);
        //this.tv_ConsumerPrice		= findViewById(R.id.tv_consumerPrice_visitor);
        this.tv_Count				= findViewById(R.id.tv_more_info_visitor);
        this.tv_CountInBox			= findViewById(R.id.tv_countInBox_visitor);
        this.tv_Weight				= findViewById(R.id.tv_weight_visitor);
        //this.tv_Duration			= findViewById(R.id.tv_duration_visitor);
        this.tv_Detail				= findViewById(R.id.tv_detail_visitor);
        this.ratingBar				= findViewById(R.id.Customer_ratingBar);
        this.tv_Header				= findViewById(R.id.tv_Header			);
        this.fab				    = findViewById(R.id.fab_Visitor_Refresh);
        this.rv                     = findViewById(R.id.Rv_ProductListDetailPriceList);
    }
    private void loadData() {
        ProductBrandIDbHelper dbBrand = new ProductBrandIDbHelper(this);
        String brand = dbBrand.find(product.getIdBrand()).getName();
        ProductGroupDbHelper dbGroup = new ProductGroupDbHelper(this);
        String group = dbGroup.find(product.getIdGroup()).getName();
        this.tv_Id.setText( Integer.toString(product.getId()));
        this.tv_Code.setText( product.getCode());
        this.tv_Name.setText(product.getName());
        this.tv_Brand.setText(brand);
        this.tv_Group.setText(group);
        this.tv_Count.setText(Convert.toSeprate(product.getCount()));
        this.tv_CountInBox.setText(Convert.toSeprate(product.getCountInBox()));
        this.tv_Weight.setText(Convert.toSeprate(product.getWeight()));
        this.tv_Detail.setText(product.getDetail());
        this.ratingBar.setRating(  (float)product.getRate() );
        this.tv_Header.setText(product.getName());
    }
    private void loadPriceList(int product_id) {
        GeneralDbHelper db = new GeneralDbHelper(context);
        List<PriceListDetailDTO> lst = db.PriceListDetailGetByProductID(product_id);


        PriceListDetailAdapter adapter = new PriceListDetailAdapter(getApplicationContext(), lst);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        this.rv.setAdapter(null);
        if (lst != null) {
            this.rv.setLayoutManager(layoutManager);
            this.rv.setItemAnimator(new DefaultItemAnimator());
            this.rv.setAdapter(adapter);
        }
        else {
            this.rv.setAdapter(null);
        }
    }
    private void findProduct( int id) {
        IProductAPI Api = APIClient.getClient().create(IProductAPI.class);
        Api.get(Global.IMEI , id).enqueue(new Callback<ApiResult<Product>>() {
            @Override
            public void onResponse(Call<ApiResult<Product>> call, Response<ApiResult<Product>> response) {
                try {
                    if (response.isSuccessful()) {
                        Product p = response.body().getData();
                        if (  p == null  ){
                            Toast.makeText(context, "Data Not recived", Toast.LENGTH_LONG).show();
                        } else {
                            ProductDbHelper db = new ProductDbHelper(context);
                            if (db.update(p)){
                                Toast.makeText(context, R.string.update_successful, Toast.LENGTH_LONG).show();
                                product = p;
                                loadData();
                            }
                            else {
                                Toast.makeText(context,R.string.update_Not_successful, Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ApiResult<Product>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
