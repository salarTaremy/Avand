package com.smartVisitor.avand.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.adapters.UpdateNewListAdapter;
import com.smartVisitor.avand.api.APIClient;
import com.smartVisitor.avand.api.IFullDataAPI;
import com.smartVisitor.avand.classes.Global;
import com.smartVisitor.avand.classes.MessageBox;
import com.smartVisitor.avand.classes.Update.UpdateGroup;
import com.smartVisitor.avand.dataTransferObjects.FullDataDTO;
import com.smartVisitor.avand.database.CustomerDbHelper;
import com.smartVisitor.avand.database.CustomerGroupDbHelper;
import com.smartVisitor.avand.database.CustomerTypeIDbHelper;
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
import com.smartVisitor.avand.entities.Inventory;
import com.smartVisitor.avand.entities.LinkPriceListCustomer;
import com.smartVisitor.avand.entities.MyCalendar;
import com.smartVisitor.avand.entities.PriceList;
import com.smartVisitor.avand.entities.PriceListDetail;
import com.smartVisitor.avand.entities.Product;
import com.smartVisitor.avand.entities.ProductBrand;
import com.smartVisitor.avand.entities.ProductGroup;
import com.smartVisitor.avand.entities.Warehouse;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.luongvo.widget.iosswitchview.SwitchView;

public class NewUpdateActivity extends AppCompatActivity {
    Context context;
    List<UpdateGroup> Lst = new ArrayList<UpdateGroup>();
    RecyclerView Rv;
    UpdateNewListAdapter Adpt;
    FloatingActionButton fab;
    SwitchView Sw_UpdateAll;
    TextView tv_Update_Header2;


    FullDataDTO data;
    private int progressStatus = 0;
    private Handler handler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_new_update);
        InitView();
        SetListener();
        loadData();
    }



    private void InitView() {
        this.Rv = findViewById(R.id.Rv_New_Update);
        this.fab = findViewById(R.id.fab_Update_Confirm);
        this.Sw_UpdateAll = findViewById(R.id.Sw_UpdateAll);
        this.tv_Update_Header2 = findViewById(R.id.tv_Update_Header2);

    }

    private void SetListener() {
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateGud();
            }
        });
        this.Sw_UpdateAll.setOnCheckedChangeListener(new SwitchView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchView switchView, boolean isChecked) {
                for (UpdateGroup item : Lst) {
                    item.IsChecked = isChecked;
                }
                loadData();
            }
        });
    }

    private void loadData() {
        this.Adpt = new UpdateNewListAdapter(this, Lst);
        Rv.setLayoutManager(new GridLayoutManager(context,1));
        Rv.setItemAnimator(new DefaultItemAnimator());
        Rv.setAdapter(Adpt);
    }





    private void UpdateGud() {

        try {
            SendMessage("ارتباط با سرور" ,  "سیستم در حال برقراری ارتباط با سرور ...",0);
            IFullDataAPI Api  = APIClient.getClient().create(IFullDataAPI.class);
            Api.get(Global.IMEI).enqueue(new Callback<FullDataDTO>() {
                @Override
                public void onResponse(Call<FullDataDTO> call, Response<FullDataDTO> response) {
                    try{
                        data = response.body();
                        Save();
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<FullDataDTO> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

        private void Save() {
        final FullDataDTO obj = this.data;
        int Total = 0 ;
        Total += obj.MyCalendars.size();
        Total += obj.Customers.size();
        Total += obj.CustomerTypes.size();
        Total += obj.CustomerGroups.size();
        Total += obj.ProductBrands.size();
        Total += obj.ProductGroups.size();
        Total += obj.Products.size();
        Total += obj.Warehouses.size();
        Total += obj.inventories.size();
        Total += obj.PriceLists.size();
            for ( PriceList item: obj.PriceLists) {
                Total += item.priceListDetails.size();
            }
        Total += obj.LinkPriceListCustomer.size();
        //--------
        final MyCalendarIDbHelper db = new MyCalendarIDbHelper(context);
        db.delete() ;
        //--------
        final CustomerTypeIDbHelper db_t = new CustomerTypeIDbHelper(context);
        db_t.delete() ;
        //--------
        final CustomerGroupDbHelper db_g = new CustomerGroupDbHelper(context );
        db_g.delete() ;
        //--------
        final CustomerDbHelper db_c = new CustomerDbHelper(context );
        db_c.delete() ;
        //--------
        final  ProductBrandIDbHelper db_Brand = new ProductBrandIDbHelper(context );
        db_Brand.delete() ;
        //--------
        final ProductGroupDbHelper db_Group = new ProductGroupDbHelper(context );
        db_Group.delete() ;
        //--------
        final ProductDbHelper db_Product = new ProductDbHelper(context );
        db_Product.delete() ;
        //--------
        final WarehouseDbHelper db_w = new WarehouseDbHelper(context );
        db_w.delete() ;
        //--------
        final InventoryDbHelper db_i = new InventoryDbHelper(context );
        db_i.delete() ;
        //--------
        final PriceListDbHelpr db_Pl = new PriceListDbHelpr(context );
        db_Pl.delete();
        //--------
        final PriceListDetailIDbHelper db_Pld = new PriceListDetailIDbHelper(context );
        db_Pld.delete() ;
        //--------
        final LinkPriceListCustomerDbhelper db_LPc = new LinkPriceListCustomerDbhelper(context );
        db_LPc.delete();
        //--------
            final ProgressBar pb = findViewById(R.id.PrcSalar);
            pb.setVisibility(View.VISIBLE);
            pb.setMax(Total);
            progressStatus = 0;
            //--------
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SendMessage("اطلاعات پایه","دریافت و ذخیره اطلاعات پایه",obj.MyCalendars.size());
                    for ( MyCalendar item: obj.MyCalendars) {
                        db.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("نوع مشتری","به روز رسانی  نوع های مشتری",obj.CustomerTypes.size());
                    for ( CustomerType item: obj.CustomerTypes) {
                        db_t.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("گروهبندی مشتریان","بروز رسانی گروه های مشتری",obj.CustomerGroups.size());
                    for ( CustomerGroup item: obj.CustomerGroups) {
                        db_g.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("مشتریان","ذخیره اطلاعات مشتریان",obj.Customers.size());
                    for ( Customer item: obj.Customers) {
                        db_c.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("برند ها","به روز رسانی برند های کالا",obj.ProductBrands.size());
                    for ( ProductBrand item: obj.ProductBrands) {
                        db_Brand.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("گروه های کالایی","دریافت و ذخیره گروه بندی کالها",obj.ProductGroups.size());
                    for ( ProductGroup item: obj.ProductGroups) {
                        db_Group.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("کالا ها","دریافت فهرست محصولات",obj.Products.size());
                    for ( Product item: obj.Products) {
                        db_Product.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("انبار ها","دریافت اطلاعات پایه انبارها",obj.Warehouses.size());
                    for ( Warehouse item: obj.Warehouses) {
                        db_w.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("موجودی کالا"," به روز رسانی موجودی کالاها در انبارها",obj.inventories.size());
                    for ( Inventory item: obj.inventories) {
                        db_i.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("لیست های قیمت","دریافت مبالغ کالا به ازای مششتریان مختلف",obj.PriceLists.size());
                    for ( PriceList item: obj.PriceLists) {
                        db_Pl.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    SendMessage("لیست های قیمت","دریافت جزئیات فی واحد کالاها",obj.PriceLists.size());
                    for ( PriceList item: obj.PriceLists) {
                        for ( PriceListDetail Dt: item.priceListDetails) {
                            db_Pld.create(Dt);
                            try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                            ProgressPlus(pb);
                        }
                    }
                    SendMessage("لیست های قیمت","تخصیص لیست های قیمت به مشتریان",obj.LinkPriceListCustomer.size());
                    for ( LinkPriceListCustomer item: obj.LinkPriceListCustomer) {
                        db_LPc.create(item);
                        try{ Thread.sleep(0); }catch(InterruptedException e){ e.printStackTrace(); }
                        ProgressPlus(pb);
                    }
                    //--------
                     ShowOk();
                    //--------
                }}).start();
        }

    private void ShowOk() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                new MessageBox(context).ShowOk("به روز رسانی", "به روز رسانی با موفقیت انجام شد", new MessageBox.OnOkClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }});
    }

    public void ProgressPlus(final ProgressBar pb){
            progressStatus +=1;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    pb.setProgress(progressStatus);
                }});
        }

    private void SendMessage( final String Title,  final String Msg,final int id) {

        progressStatus +=1;
        handler.post(new Runnable() {
            @Override
            public void run() {
                Lst.add(new UpdateGroup(id, Title, Msg));
                Adpt.notifyItemInserted(Lst.size() - 1);
            }});

    }


}

