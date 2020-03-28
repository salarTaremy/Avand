package com.smartVisitor.avand.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.ViewModels.PriceListViewModel;
import com.smartVisitor.avand.adapters.UpdateListAdapter;
import com.smartVisitor.avand.api.APIClient;
import com.smartVisitor.avand.api.ApiResult;
import com.smartVisitor.avand.api.ICalendarAPI;
import com.smartVisitor.avand.api.ICustomerAPI;
import com.smartVisitor.avand.api.IDailyVisitPlanAPI;
import com.smartVisitor.avand.api.IInventoryAPI;
import com.smartVisitor.avand.api.IPriceListApi;
import com.smartVisitor.avand.api.IProductAPI;
import com.smartVisitor.avand.api.IWarehouseAPI;
import com.smartVisitor.avand.classes.Global;
import com.smartVisitor.avand.database.CustomerDbHelper;
import com.smartVisitor.avand.database.CustomerGroupDbHelper;
import com.smartVisitor.avand.database.CustomerTypeIDbHelper;
import com.smartVisitor.avand.database.DailyVisitPlanDbHelper;
import com.smartVisitor.avand.database.DailyVisitPlanDetailDbHelper;
import com.smartVisitor.avand.database.InventoryDbHelper;
import com.smartVisitor.avand.database.LinkPriceListCustomerDbhelper;
import com.smartVisitor.avand.database.MyCalendarIDbHelper;
import com.smartVisitor.avand.database.PriceListDbHelpr;
import com.smartVisitor.avand.database.PriceListDetailIDbHelper;
import com.smartVisitor.avand.database.ProductBrandIDbHelper;
import com.smartVisitor.avand.database.ProductDbHelper;
import com.smartVisitor.avand.database.ProductGroupDbHelper;
import com.smartVisitor.avand.database.WarehouseDbHelper;
import com.smartVisitor.avand.entities.Customer;
import com.smartVisitor.avand.entities.CustomerGroup;
import com.smartVisitor.avand.entities.CustomerType;
import com.smartVisitor.avand.ViewModels.CustomerViewModel;
import com.smartVisitor.avand.entities.DailyVisitPlan;
import com.smartVisitor.avand.entities.DailyVisitPlanDetail;
import com.smartVisitor.avand.entities.Inventory;
import com.smartVisitor.avand.entities.LinkPriceListCustomer;
import com.smartVisitor.avand.entities.MyCalendar;
import com.smartVisitor.avand.entities.PriceList;
import com.smartVisitor.avand.entities.PriceListDetail;
import com.smartVisitor.avand.entities.Product;
import com.smartVisitor.avand.entities.ProductBrand;
import com.smartVisitor.avand.entities.ProductGroup;
import com.smartVisitor.avand.ViewModels.ProductViewModel;
import com.smartVisitor.avand.classes.Update.UpdateGroup;
import com.smartVisitor.avand.entities.Warehouse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    private RecyclerView Rv_Update;
    TextView tv_Update_Header ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.activity_update);
        initView();
        loadData();
        setEventListener();
    }

    private void setEventListener() {
//        this.cl_UpdateAll.setOnClickListener(this);
//        this.cl_UpdateProduct.setOnClickListener(this);
//        this.cl_UpdateInvertory.setOnClickListener(this);
//        this.cl_UpdateCustomer.setOnClickListener(this);
//        this.cl_UpdatePrice.setOnClickListener(this);
    }

    private void initView() {
        this.Rv_Update = findViewById(R.id.rv_Update);
        this.tv_Update_Header = findViewById(R.id.tv_Update_Header);
    }
    private void loadData() {
        List<UpdateGroup> Lst = new ArrayList<UpdateGroup>();

        Lst.add(new UpdateGroup( 0,this.getString(R.string.update_All),this.getString(R.string.update_Detail_All)));
        Lst.add(new UpdateGroup( 1,this.getString(R.string.update_Customer),this.getString(R.string.update_Detail_Customer)));
        Lst.add(new UpdateGroup( 2,this.getString(R.string.update_Product),this.getString(R.string.update_Detail_Product)));
        Lst.add(new UpdateGroup( 3,this.getString(R.string.update_Inventory),this.getString(R.string.update_Detail_Inventory)));
        Lst.add(new UpdateGroup( 4,this.getString(R.string.update_Price),this.getString(R.string.update_Detail_Price)));
        Lst.add(new UpdateGroup( 5,this.getString(R.string.update_Plans),this.getString(R.string.update_Detail_Plan)));

        UpdateListAdapter Ad = new UpdateListAdapter(this, Lst);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        Rv_Update.setLayoutManager(layoutManager);
        Rv_Update.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        Rv_Update.setAdapter(Ad);
    }

    @Override
    public void onClick(View v) {
    }

    public  void  Update(int id)
    {
        if (id==0) {
             //new Taskinto().execute(1,2,3);
            updateMyCalendar();
            updateCustomers();
            updateWarehouse();
            updateInventory();
            updateProduct();
            updatePriceList();
            updatePlans();
        }
        if (id==1) {
            updateCustomers();
        }
        if (id==2) {
            updateProduct();
        }
        if (id==3) {
            updateWarehouse();
            updateInventory();
        }
        if (id==4) {
            updatePriceList();
        }
        if (id==5) {
            updatePlans();
        }
    }
    private void updateMyCalendar() {

        if (   new MyCalendarIDbHelper(context).Count() > 0  ){
            return;
        }
        try {
            final ProgressDialog P = ProgressDialog.show(context, getString(R.string.update), getString(R.string.update_BaseData), true);
            ICalendarAPI api_1  = APIClient.getClient().create(ICalendarAPI.class);
            api_1.get().enqueue(new Callback<ApiResult<List<MyCalendar>>>() {
                @Override
                public void onResponse(Call<ApiResult<List<MyCalendar>>> call, Response<ApiResult<List<MyCalendar>>> response) {
                    try {
                        if (response.isSuccessful()) {
                            final List<MyCalendar>  obj = response.body().getData();
                            final MyCalendarIDbHelper db = new MyCalendarIDbHelper(context.getApplicationContext() );
                            db.delete() ;
                            for ( MyCalendar item: obj) {
                                db.create(item);
                            }
                            P.dismiss();
                            Toast.makeText( context.getApplicationContext() , String.valueOf(R.string.update_Base_successful), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ApiResult<List<MyCalendar>>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void updateCustomers() {
        try {
            final ProgressDialog P = ProgressDialog.show(context, getString(R.string.update), getString(R.string.update_Customer), true);
            ICustomerAPI api  = APIClient.getClient().create(ICustomerAPI.class);
            api.getWithDependency(Global.IMEI).enqueue(new Callback<ApiResult<CustomerViewModel>>() {
                @Override
                public void onResponse(Call<ApiResult<CustomerViewModel>> call, Response<ApiResult<CustomerViewModel>> response) {
                    try {
                        if (response.isSuccessful()) {
                            final CustomerViewModel  obj = response.body().getData();
                            //--------
                            CustomerTypeIDbHelper db_t = new CustomerTypeIDbHelper(context.getApplicationContext() );
                            db_t.delete() ;
                            for ( CustomerType item: obj.getCustomerTypes()) {
                                db_t.create(item);
                            }
                            //--------
                            CustomerGroupDbHelper db_g = new CustomerGroupDbHelper(context.getApplicationContext() );
                            db_g.delete() ;
                            for ( CustomerGroup item: obj.getCustomerGroups()) {
                                db_g.create(item);
                            }
                            //--------
                            final CustomerDbHelper db_c = new CustomerDbHelper(context.getApplicationContext() );
                            db_c.delete() ;
                            for ( Customer item: obj.getCustomers()) {
                                db_c.create(item);
                            }
                            P.dismiss();
                            Toast.makeText( context.getApplicationContext() , R.string.update_Customer_successful, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ApiResult<CustomerViewModel>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void updateProduct() {
        try {
            final ProgressDialog P = ProgressDialog.show(context, getString(R.string.update), getString(R.string.update_Product), true);
            IProductAPI api2  = APIClient.getClient().create(IProductAPI.class);
            api2.getWithDependency(Global.IMEI).enqueue(new Callback<ApiResult<ProductViewModel>>() {
                @Override
                public void onResponse(Call<ApiResult<ProductViewModel>> call, Response<ApiResult<ProductViewModel>> response) {
                    try {
                        if (response.isSuccessful()) {
                            final ProductViewModel  obj= response.body().getData();
                            ProductDbHelper db = new ProductDbHelper(context.getApplicationContext() );
                            //--------
                            ProductBrandIDbHelper db_Brand = new ProductBrandIDbHelper(context.getApplicationContext() );
                            db_Brand.delete() ;
                            for ( ProductBrand item: obj.getProductBrands()) {
                                db_Brand.create(item);
                            }
                            //--------
                            ProductGroupDbHelper db_Group = new ProductGroupDbHelper(context.getApplicationContext() );
                            db_Group.delete() ;
                            for ( ProductGroup item: obj.getProductGroups()) {
                                db_Group.create(item);
                            }
                            //--------
                            final ProductDbHelper db_Product = new ProductDbHelper(context.getApplicationContext() );
                            db_Product.delete() ;
                            for ( Product item: obj.getProducts()) {
                                db_Product.create(item);
                            }
                            P.dismiss();
                            Toast.makeText( context.getApplicationContext() , R.string.update_Product_successful, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ApiResult<ProductViewModel>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private  void updateWarehouse(){
        try {
            final ProgressDialog P = ProgressDialog.show(context, getString(R.string.update), getString(R.string.update_Warehouse), true);
            IWarehouseAPI api  = APIClient.getClient().create(IWarehouseAPI.class);
            api.get(Global.IMEI).enqueue(new Callback<ApiResult<List<Warehouse>>>() {
                @Override
                public void onResponse(Call<ApiResult<List<Warehouse>>> call, Response<ApiResult<List<Warehouse>>> response) {
                    try {
                        if (response.isSuccessful()) {
                            final List<Warehouse> obj= response.body().getData();
                            WarehouseDbHelper db = new WarehouseDbHelper(context.getApplicationContext() );
                            db.delete() ;
                            for ( Warehouse item : obj) {
                                db.create(item);
                            }
                            P.dismiss();
                            Toast.makeText( context.getApplicationContext() , R.string.update_Warehouse_successful, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ApiResult<List<Warehouse>>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private  void updateInventory(){
        try {
            final ProgressDialog P = ProgressDialog.show(context, getString(R.string.update), getString(R.string.update_Inventory), true);
            IInventoryAPI api  = APIClient.getClient().create(IInventoryAPI.class);
            api.get(Global.IMEI).enqueue(new Callback<ApiResult<List<Inventory>>>() {
                @Override
                public void onResponse(Call<ApiResult<List<Inventory>>> call, Response<ApiResult<List<Inventory>>> response) {
                    try {
                        if (response.isSuccessful()) {
                            final List<Inventory> obj= response.body().getData();
                            InventoryDbHelper db = new InventoryDbHelper(context.getApplicationContext() );
                            db.delete() ;
                            for ( Inventory item : obj) {
                                db.create(item);
                            }
                            P.dismiss();
                            Toast.makeText( context.getApplicationContext() , R.string.update_Inventory_successful, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ApiResult<List<Inventory>>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private  void updatePriceList(){
        try {
            final ProgressDialog P = ProgressDialog.show(context, getString(R.string.update), getString(R.string.update_Price), true);
            IPriceListApi api  = APIClient.getClient().create(IPriceListApi.class);
            api.GetWithDependency(Global.IMEI).enqueue(new Callback<ApiResult<PriceListViewModel>>() {
                @Override
                public void onResponse(Call<ApiResult<PriceListViewModel>> call, Response<ApiResult<PriceListViewModel>> response) {
                    try {
                        if (response.isSuccessful()) {
                            final PriceListViewModel obj= response.body().getData();
                            PriceListDbHelpr db_Header = new PriceListDbHelpr(context );
                            PriceListDetailIDbHelper db_Detail = new PriceListDetailIDbHelper(context );
                            LinkPriceListCustomerDbhelper db_link = new  LinkPriceListCustomerDbhelper(context);
                            db_Header.delete();
                            db_Detail.delete();
                            db_link.delete();
                            for ( PriceList Header : obj.PriceLists) {
                                db_Header.create(Header);
                                for ( PriceListDetail Dt: Header.priceListDetails) {
                                    db_Detail.create(Dt);
                                }
                            }
                            for ( LinkPriceListCustomer Lnk : obj.Links) {
                                db_link.create(Lnk);
                            }
                            P.dismiss();
                            Toast.makeText( context.getApplicationContext() , R.string.update_PriceList_successful, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ApiResult<PriceListViewModel>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private  void updatePlans(){
        try {
            final ProgressDialog P = ProgressDialog.show(context, getString(R.string.update), getString(R.string.update_Plans), true);
            IDailyVisitPlanAPI api  = APIClient.getClient().create(IDailyVisitPlanAPI.class);
            api.get(Global.IMEI).enqueue(new Callback<ApiResult<List<DailyVisitPlan>>>() {
                @Override
                public void onResponse(Call<ApiResult<List<DailyVisitPlan>>> call, Response<ApiResult<List<DailyVisitPlan>>> response) {
                    try {
                        if (response.isSuccessful()) {
                            final List<DailyVisitPlan> obj= response.body().getData();
                            DailyVisitPlanDbHelper db_Header            = new DailyVisitPlanDbHelper(context.getApplicationContext() );
                            DailyVisitPlanDetailDbHelper db_Detail      = new DailyVisitPlanDetailDbHelper(context.getApplicationContext() );
                            db_Header.delete() ;
                            db_Detail.delete();
                            for ( DailyVisitPlan Header : obj) {
                                db_Header.create(Header);
                                for ( DailyVisitPlanDetail Dt: Header.DailyVisitPlanDetails) {
                                    db_Detail.create(Dt);
                                }
                            }
                            P.dismiss();
                            Toast.makeText( context.getApplicationContext() , R.string.update_Plans_successful, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ApiResult<List<DailyVisitPlan>>> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


//    class  Taskinto extends AsyncTask<Integer,String,Boolean>{
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Boolean doInBackground(Integer... integers) {
//            try {
//                ICalendarAPI api_1  = APIClient.getClient().create(ICalendarAPI.class);
//                api_1.get().enqueue(new Callback<ApiResult<List<MyCalendar>>>() {
//                    @Override
//                    public void onResponse(Call<ApiResult<List<MyCalendar>>> call, Response<ApiResult<List<MyCalendar>>> response) {
//                        try {
//                            if (response.isSuccessful()) {
//                                final List<MyCalendar>  obj = response.body().getData();
//                                final MyCalendarIDbHelper db = new MyCalendarIDbHelper(context.getApplicationContext() );
//                                db.delete() ;
//                                for ( MyCalendar item: obj) {
//                                    db.create(item);
//                                }
//                                publishProgress(" Calendar inserted");
//
//                                Toast.makeText( context.getApplicationContext() , "ok ", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//                            Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<ApiResult<List<MyCalendar>>> call, Throwable t) {
//                        Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            } catch (Exception e) {
//                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//            //---------------------------------------------------------------------------------------------------------
//            try {
//                IProductAPI api2  = APIClient.getClient().create(IProductAPI.class);
//                api2.getWithDependency(Global.IMEI).enqueue(new Callback<ApiResult<ProductViewModel>>() {
//                    @Override
//                    public void onResponse(Call<ApiResult<ProductViewModel>> call, Response<ApiResult<ProductViewModel>> response) {
//                        try {
//                            if (response.isSuccessful()) {
//                                ProductViewModel  obj= response.body().getData();
//                                ProductDbHelper db = new ProductDbHelper(context.getApplicationContext() );
//                                //--------
//                                ProductBrandIDbHelper db_Brand = new ProductBrandIDbHelper(context.getApplicationContext() );
//                                db_Brand.delete() ;
//                                for ( ProductBrand item: obj.getProductBrands()) {
//                                    db_Brand.create(item);
//                                }
//                                //--------
//                                ProductGroupDbHelper db_Group = new ProductGroupDbHelper(context.getApplicationContext() );
//                                db_Group.delete() ;
//                                for ( ProductGroup item: obj.getProductGroups()) {
//                                    db_Group.create(item);
//                                }
//                                //--------
//                                ProductDbHelper db_Product = new ProductDbHelper(context.getApplicationContext() );
//                                db_Product.delete() ;
//                                for ( Product item: obj.getProducts()) {
//                                    db_Product.create(item);
//                                }
//                                publishProgress("Product Customer ");
//                                Toast.makeText( context.getApplicationContext() , R.string.update_Product_successful, Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//                            Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<ApiResult<ProductViewModel>> call, Throwable t) {
//                        Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            } catch (Exception e) {
//                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//            //------------------------------------------------------------------------------------------------------
//            try {
//                ICustomerAPI api  = APIClient.getClient().create(ICustomerAPI.class);
//                api.getWithDependency(Global.IMEI).enqueue(new Callback<ApiResult<CustomerViewModel>>() {
//                    @Override
//                    public void onResponse(Call<ApiResult<CustomerViewModel>> call, Response<ApiResult<CustomerViewModel>> response) {
//                        try {
//                            if (response.isSuccessful()) {
//                                final CustomerViewModel  obj = response.body().getData();
//                                //--------
//                                CustomerTypeIDbHelper db_t = new CustomerTypeIDbHelper(context.getApplicationContext() );
//                                db_t.delete() ;
//                                for ( CustomerType item: obj.getCustomerTypes()) {
//                                    db_t.create(item);
//                                }
//                                //--------
//                                CustomerGroupDbHelper db_g = new CustomerGroupDbHelper(context.getApplicationContext() );
//                                db_g.delete() ;
//                                for ( CustomerGroup item: obj.getCustomerGroups()) {
//                                    db_g.create(item);
//                                }
//                                //--------
//                                final CustomerDbHelper db_c = new CustomerDbHelper(context.getApplicationContext() );
//                                db_c.delete() ;
//                                for ( Customer item: obj.getCustomers()) {
//                                    db_c.create(item);
//                                }
//                                publishProgress("complate Customer ...");
//                                Toast.makeText( context.getApplicationContext() , R.string.update_Customer, Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText( context.getApplicationContext() , response.errorBody().string(), Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (Exception e) {
//                            Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<ApiResult<CustomerViewModel>> call, Throwable t) {
//                        Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            } catch (Exception e) {
//                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            return  true;
//
//        }
//
//        @Override
//        protected void onProgressUpdate(final String... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(Boolean b) {
//            super.onPostExecute(b);
//        }
//
//    }
}

