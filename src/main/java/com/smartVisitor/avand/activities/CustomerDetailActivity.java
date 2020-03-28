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
import com.smartVisitor.avand.adapters.DictionaryListAdapter;
import com.smartVisitor.avand.api.APIClient;
import com.smartVisitor.avand.api.ApiResult;
import com.smartVisitor.avand.api.ICustomerAPI;
import com.smartVisitor.avand.classes.Global;
import com.smartVisitor.avand.classes.MyDictionary;
import com.smartVisitor.avand.database.CustomerDbHelper;
import com.smartVisitor.avand.database.CustomerGroupDbHelper;
import com.smartVisitor.avand.database.CustomerTypeIDbHelper;
import com.smartVisitor.avand.entities.Customer;
import com.smartVisitor.avand.entities.CustomerGroup;
import com.smartVisitor.avand.entities.CustomerType;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerDetailActivity extends AppCompatActivity {
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
    private RatingBar ratingBarCustomer	;
    private int customer_id             ;
    private Customer customer           ;
    private FloatingActionButton fab    ;
    private RecyclerView rv             ;
    public Context context              ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        initView();
        loadData();
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findCustomer(customer_id);
            }
        });
    }
    private void initView() {
        this.context                = this.getApplicationContext();
        this.customer_id             = getIntent().getIntExtra("Customer_Id",0);
        this.rv = findViewById(R.id.rv_Update);
        this.tv_Header = findViewById(R.id.tv_Update_Header);
        this.ratingBarCustomer = findViewById(R.id.Customer_ratingBar);
        this.fab				    = findViewById(R.id.fab_Customer_Refresh);
    }
    private void loadData() {


        CustomerDbHelper db             = new CustomerDbHelper(this);
        CustomerGroupDbHelper dbGrp        = new CustomerGroupDbHelper(this);
        CustomerTypeIDbHelper dbTyp         = new CustomerTypeIDbHelper(this);

        Customer customer=  db.find(this.customer_id);
        CustomerGroup cg = dbGrp.find(customer.getIdGroup());
        CustomerType ct = dbTyp.find(customer.getIdType());
        List<MyDictionary> customerDic = new ArrayList<MyDictionary>();


        this.ratingBarCustomer.setRating(customer.getRate());
        this.tv_Header.setText(customer.getName());


        customerDic.add(new MyDictionary( context.getString(R.string.Customer_id) ,Integer.toString(customer.getId())));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_Code) ,customer.getCode()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_Group) , cg.getName()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_Type) ,ct.getName()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_F_Name) ,customer.getFName()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_L_name) ,customer.getLName()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_AvgDate) , Integer.toString(customer.getAvgDate())));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_Debt) ,Long.toString(customer.getDebt())));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_OpenInvoice) ,Integer.toString(customer.getOpenInvoice())));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_tel) ,customer.getTel()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_mail) ,customer.getMail()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_mob) ,customer.getMobile()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_Address) ,customer.getAddress()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_Description) ,customer.getDescription()));
        customerDic.add(new MyDictionary( context.getString(R.string.Customer_Name) ,customer.getName()));
        //customerDic.add(new MyDictionary( context.getString(R.string.Customer_Group) ,customer.getIdGroup()));
        //customerDic.add(new MyDictionary( context.getString(R.string.Customer_rate) ,customer.getRate()));
        //customerDic.add(new MyDictionary( context.getString(R.string.Customer_Type) ,customer.getIdType()));
        //customerDic.add(new MyDictionary( context.getString(R.string.Customer_x) ,customer.getX()));
        //customerDic.add(new MyDictionary( context.getString(R.string.Customer_y) ,customer.getY()));
        //------------------------------------------------------------------------------------------
        DictionaryListAdapter ad = new DictionaryListAdapter(this.context, customerDic);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.context);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(ad);

    }
    private void findCustomer( int id){
        ICustomerAPI Api = APIClient.getClient().create(ICustomerAPI.class);
        Api.get(Global.IMEI , id).enqueue(new Callback<ApiResult<Customer>>() {
            @Override
            public void onResponse(Call<ApiResult<Customer>> call, Response<ApiResult<Customer>> response) {
                try {
                    if (response.isSuccessful()) {
                        Customer cr = response.body().getData();
                        if (  cr == null  ){
                            Toast.makeText(context, "Data Not recived", Toast.LENGTH_LONG).show();
                        } else {
                            CustomerDbHelper db = new CustomerDbHelper(context);
                            if (db.update(cr)){
                                Toast.makeText(context, R.string.update_successful, Toast.LENGTH_LONG).show();
                                customer = cr;
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
            public void onFailure(Call<ApiResult<Customer>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
