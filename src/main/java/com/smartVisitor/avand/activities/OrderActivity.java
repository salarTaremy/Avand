package com.smartVisitor.avand.activities;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.smartVisitor.avand.R;
import com.smartVisitor.avand.ViewModels.OrderCellInfoViewModel;
import com.smartVisitor.avand.adapters.OrderItemAdapter;
import com.smartVisitor.avand.classes.Convert;
import com.smartVisitor.avand.classes.Global;
import com.smartVisitor.avand.classes.MessageBox;
import com.smartVisitor.avand.classes.MyCellInfo;
import com.smartVisitor.avand.database.GeneralDbHelper;
import com.smartVisitor.avand.database.OrderCellInfoDbHelper;
import com.smartVisitor.avand.database.OrderIDbHelper;
import com.smartVisitor.avand.database.OrderItemDbHelper;
import com.smartVisitor.avand.entities.Customer;
import com.smartVisitor.avand.entities.Order;
import com.smartVisitor.avand.entities.OrderItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class OrderActivity extends AppCompatActivity {
    /* GPS Constant Permission */
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 12;

    /* Position */
    private static final long MINIMUM_TIME = 10000;  // 10s
    private static final float MINIMUM_DISTANCE = 1; // 50m

    /* GPS */
    private LocationManager locationManager;
    /* Context */
    private Context context;
    private Order order;
    //private Customer customer;
    private RecyclerView Rv_Order_Items;
    private FloatingActionButton fab_Order;
    private FABToolbarLayout fab_Menu_Order;
    private RadioButton Rad_Order_1, Rad_Order_2, Rad_Order_3;
    private RadioButton Rad_OrderType_Sale, Rad_OrderType_Return;
    private CheckBox Chk_OrderCheckAll;
    private ImageView ImgBtnDelete, ImgBtnConfirm;
    private EditText Txt_Order_Description;
    private TextView Tv_Order_Date_value, Tv_Order_Name, Tv_Order_Price_val, Tv_Order_Tax_val, Tv_Order_Net_val, Tv_Order_TimeOut_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        this.context = this;
        InitView();
        SetObjects();
        LoadData();
        FillList(null);
        SetOrderLocation();
        SetListener();
    }

    private void SetObjects() {
        this.order.customer = (Customer) getIntent().getSerializableExtra("Customer");
        GeneralDbHelper db = new GeneralDbHelper(context);
        this.order.priceList = db.PriceListGetByCustomer(this.order.customer);
    }

    private void SetListener() {
        this.fab_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order.Items.size() <= 0) {
                    ImgBtnDelete.setVisibility(View.GONE);
                    ImgBtnConfirm.setVisibility(View.GONE);
                } else {
                    ImgBtnDelete.setVisibility(View.VISIBLE);
                    ImgBtnConfirm.setVisibility(View.VISIBLE);
                }
                fab_Menu_Order.show();
            }
        });
        this.Chk_OrderCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (OrderItem it : order.Items) {
                    it.setChecked(isChecked);
                }
                FillList(order.Items);
            }
        });
    }

    private void LoadData() {
        String prDate = Global.getTodayString(this);
        this.Tv_Order_Date_value.setText(prDate);
        this.Tv_Order_Name.setText(this.order.customer.getName());
        this.fab_Menu_Order = findViewById(R.id.fab_Menu_Order);
        this.fab_Order = findViewById(R.id.fab_Order);
        this.ImgBtnDelete = findViewById(R.id.ImgBtnDeleteProductFromOrder);
        this.ImgBtnConfirm = findViewById(R.id.ImgBtnOrderConfirm);
    }

    private void InitView() {
        this.order = new Order();
        this.Tv_Order_Date_value = findViewById(R.id.Tv_Order_Date_value);
        this.Tv_Order_Name = findViewById(R.id.Tv_Order_Name);
        this.Rv_Order_Items = findViewById(R.id.Rv_Order_Items);
        this.Chk_OrderCheckAll = findViewById(R.id.Chk_OrderCheckAll);
        this.Txt_Order_Description = findViewById(R.id.Txt_Order_Description);
        this.Tv_Order_Price_val = findViewById(R.id.Tv_Order_Price_val);
        this.Tv_Order_Tax_val = findViewById(R.id.Tv_Order_Tax_val);
        this.Tv_Order_Net_val = findViewById(R.id.Tv_Order_Net_val);
        this.Tv_Order_TimeOut_val = findViewById(R.id.Tv_Order_TimeOut_val);
        this.Rad_Order_1 = findViewById(R.id.Rad_Order_1);
        this.Rad_Order_2 = findViewById(R.id.Rad_Order_2);
        this.Rad_Order_3 = findViewById(R.id.Rad_Order_3);
        this.Rad_OrderType_Sale = findViewById(R.id.Rad_OrderType_Sale);
        this.Rad_OrderType_Return = findViewById(R.id.Rad_OrderType_Return);
    }

    private void FillList(List<OrderItem> lst) {
        if (lst == null) {
            return;
        }
        OrderItemAdapter Adp = new OrderItemAdapter(this, lst);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        Rv_Order_Items.setAdapter(null);
        if (lst != null) {
            Rv_Order_Items.setLayoutManager(layoutManager);
            Rv_Order_Items.setItemAnimator(new DefaultItemAnimator());
            //rv_Product.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
            Rv_Order_Items.setAdapter(Adp);
        } else {
            Rv_Order_Items.setAdapter(null);
        }
        Campute();
    }

    private void Campute() {
        long TotalPrice = 0;
        long TotalTax = 0;
        long TotalNetPrice = 0;
        long TotalOverTime = 0;
        for (OrderItem it : order.Items) {
            TotalPrice += (it.Price * it.Qty);
            TotalTax += (it.Price * it.Qty * it.Tax / 100);
        }
        this.Tv_Order_Price_val.setText(Convert.toSeprate(TotalPrice));
        this.Tv_Order_Tax_val.setText(Convert.toSeprate(TotalTax));
        this.Tv_Order_Net_val.setText(Convert.toSeprate(TotalPrice + TotalTax));
        this.Tv_Order_TimeOut_val.setText("0");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                this.order = (Order) data.getSerializableExtra("Order");
                FillList(order.Items);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "RESULT_CANCELED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Confirm(View view) throws InterruptedException {
        if (Save()) {

            new MessageBox(context).ShowOk("ثبت سفارش", "سفارش شما با موفقیت ثیت شد", new MessageBox.OnOkClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            new MessageBox(context).ShowError("ثبت سفارش" , "ثبت سفارش با خطا مواجه شد");
        }
        this.fab_Menu_Order.hide();
    }

    private void SetOrderLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MINIMUM_TIME, MINIMUM_DISTANCE, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_ACCESS_FINE_LOCATION);
            }
        }
    }

    private boolean Save() {
        try {


            OrderIDbHelper odb = new OrderIDbHelper(context);
            order.id = odb.Max("id") + 1;
            //order.customer = this.customer;
            order.Description = this.Txt_Order_Description.getText().toString();
            order.ClientTime = Integer.valueOf(Global.ThisTime());
            order.ClientDate = Global.getToday(context);
            order.PaymentType = GetPaymentType();
            order.Type = GetOrderType();
            order.MyCells =  GetMyCells();
            if (odb.create(order)) {
                OrderItemDbHelper idb = new OrderItemDbHelper(context);
                for (OrderItem it : order.Items) {
                    it.ID_order = order.id;
                    if (idb.create(it)) {
                        //code
                    } else {
                        return false;
                    }
                }
                OrderCellInfoDbHelper cdb = new OrderCellInfoDbHelper(context);
                for (MyCellInfo Cell : order.MyCells) {
                    if (cdb.create( new OrderCellInfoViewModel( order.id , Cell  ))) {
                        //code
                    } else {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    private Integer GetPaymentType() {
        if (Rad_Order_1.isChecked()) {
            return 1;
        }
        if (Rad_Order_2.isChecked()) {
            return 2;
        }
        if (Rad_Order_3.isChecked()) {
            return 3;
        }
        return 0;
    }

    private Integer GetOrderType() {
        if (Rad_OrderType_Sale.isChecked()) {
            return 1;
        }
        if (Rad_OrderType_Return.isChecked()) {
            return 2;
        }
        return 0;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (fab_Menu_Order.isToolbar()) {
                fab_Menu_Order.hide();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void AddProduct(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra("CallFromOrder", true);
        intent.putExtra("Order", this.order);
        //startActivity(intent);
        startActivityForResult(intent, 1);
        this.fab_Menu_Order.hide();
    }

    public void DeleteProduct(View view) {
        int cnt = GetCheckedCount();
        if (cnt == 0) {
            new MessageBox(context).ShowInfo("حذف اقلام","شما هیچ  موردی برای حذف انتخاب نکرده اید!");
            this.fab_Menu_Order.hide();
            return;
        }
        //════════════════════════════════════════Dialog Question════════════════════════════════════════
        new MessageBox(context).ShowQuestion("", "", new MessageBox.OnYesClickListener() {
            @Override
            public void onClick(View v) {
                for (Iterator<OrderItem> iter = order.Items.listIterator(); iter.hasNext(); ) {
                    OrderItem it = iter.next();
                    if (it.isChecked()) {
                        iter.remove();
                    }
                }
                FillList(order.Items);
                Chk_OrderCheckAll.setChecked(false);
            }
        });
        this.fab_Menu_Order.hide();
    }

    private final LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(final Location location) {
            if (location != null) {
                order.Latitude = location.getLatitude();
                order.Longitude = location.getLongitude();
            } else {
                Toast.makeText(getApplicationContext(), "مختصات مکانی  ثبت نشد", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            //Toast.makeText(TrackingActivity.this, "onStatusChanged" + s, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String s) {
            Toast.makeText(context, "Provider enabled: " + s, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String s) {
            Toast.makeText(context, "Provider disabled: " + s, Toast.LENGTH_SHORT).show();
        }
    };


    private int GetCheckedCount() {
        int cnt = 0;
        for (OrderItem it : this.order.Items) {
            if (it.isChecked()) {
                cnt += 1;
            }
        }
        return cnt;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public List<MyCellInfo> GetMyCells()

    {
        final TelephonyManager telMgr = (TelephonyManager)
            getSystemService(this.TELEPHONY_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        List<CellInfo> cellLocation = telMgr.getAllCellInfo();

        Iterator<CellInfo> i = cellLocation.iterator();
        while (i.hasNext()) {
            CellInfo c = i.next();
            if (c.isRegistered() == false) {
                i.remove();
            }
        }


        List<MyCellInfo> MyCells = new ArrayList<MyCellInfo>();
        for (CellInfo item : cellLocation) {

            if (item.getClass() == CellInfoLte.class) {
                CellIdentityLte Idn =  ((CellInfoLte) item).getCellIdentity();
                MyCells.add(new MyCellInfo(Idn.toString(), item.isRegistered() , Idn.getMcc(),Idn.getMnc(),Idn.getCi(), Idn.getTac()  ));
            }
            else if (item.getClass() == CellInfoWcdma.class) {
                CellIdentityWcdma Idn =  ((CellInfoWcdma) item).getCellIdentity();
                MyCells.add(new MyCellInfo(Idn.toString(), item.isRegistered() , Idn.getMcc(),Idn.getMnc(),Idn.getCid(), Idn.getLac() ));
            }
            else if (item.getClass() == CellInfoGsm.class) {
                CellIdentityGsm Idn =  ((CellInfoGsm) item).getCellIdentity();
                MyCells.add(new MyCellInfo(Idn.toString(), item.isRegistered() , Idn.getMcc(),Idn.getMnc(),Idn.getCid(), Idn.getLac() ));
            }
            else{
                MyCells.add(new MyCellInfo("unknown", item.isRegistered() , 0,0,0,0));
            }
        }
        return  MyCells;
    }



}
