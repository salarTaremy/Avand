package com.smartVisitor.avand.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartVisitor.avand.R;
import com.smartVisitor.avand.adapters.ProductListAdapter;
import com.smartVisitor.avand.dataTransferObjects.ProductDTO;
import com.smartVisitor.avand.database.GeneralDbHelper;
import com.smartVisitor.avand.entities.Order;
import com.smartVisitor.avand.entities.OrderItem;
import com.smartVisitor.avand.entities.Product;

import java.util.Iterator;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView rv_Product;
    private SearchView sv;
    private  boolean CallFromOrder;
    private  Order order;
    private Context context;
    ProductListAdapter productListAdapter;
    private FloatingActionButton fab;

    public ProductListActivity() {
        this.context = this;
    }

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        this.CallFromOrder =  getIntent().getBooleanExtra("CallFromOrder" , false);
        if ( CallFromOrder){
            this.order = (Order) getIntent().getSerializableExtra("Order");
        }else {
            this.order = new Order();
        }
        initView();
        loadData(null);
        SetOnclick();
        //=================================================================
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        sv.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        sv.setSubmitButtonEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadData(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                loadData(newText);
                return false;
            }
        });
        rv_Product.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                return false;
            }
        });
    }
    private void initView() {
        this.rv_Product = findViewById(R.id.rv_Product);
        this.sv = findViewById(R.id.searchView);
        this.fab = findViewById(R.id.fab_Product_Confirm);
    }
    private void loadData( String keyword) {
        GeneralDbHelper db = new GeneralDbHelper(getApplicationContext());
        List<ProductDTO> lst ;
        if(keyword == null){
             if (CallFromOrder == true){
                 lst= db.ProductGetAll(this.order.priceList);
             }else {
                 lst= db.ProductGetAll();
             }
        }
        else {
            if (CallFromOrder == true){
                lst= db.ProductGetByLike(keyword,this.order.priceList);
            }else {
                lst= db.ProductGetByLike(keyword);
            }
        }
        productListAdapter = new ProductListAdapter(this, lst,this.order,this.CallFromOrder);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv_Product.setAdapter(null);
        if (lst != null) {
            rv_Product.setLayoutManager(layoutManager);
            rv_Product.setItemAnimator(new DefaultItemAnimator());
            //rv_Product.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
            rv_Product.setAdapter(productListAdapter);
        }
        else {
            rv_Product.setAdapter(null);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Product product= (Product)data.getSerializableExtra("Product");
                int position =  data.getIntExtra("position",-1);
                productListAdapter.products.get(position).SelectedCount = product.SelectedCount;
                productListAdapter.products.get(position).SelectedOffer = product.SelectedOffer;
                productListAdapter.products.get(position).SelectedTax = product.SelectedTax;
                AddProduct(product);
                productListAdapter.notifyItemChanged(position);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "RESULT_CANCELED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void AddProduct(Product product) {
        for (Iterator<OrderItem> iter = this.order.Items.listIterator(); iter.hasNext(); ) {
            OrderItem it = iter.next();
            if ((float)it.ID_Product == (float)product.id){
                iter.remove();
            }
        }
        if (product.SelectedCount > 0 || product.SelectedOffer > 0  ){
            this.order.Items.add(new OrderItem(0,0,product));
        }
    }

    private void SetOnclick() {
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Order",order);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (  this.order.Items.size() > 0  ){
                final View dialogView = LayoutInflater.from(context ).inflate(R.layout.dialog_confirm_layout, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context).setCancelable(false).setView(dialogView)  ;
                final AlertDialog alertDialog = builder.show();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ((TextView)dialogView.findViewById(R.id.Tv_Msg_Qestion_Title)).setText("تایید سفارش");
                ((TextView)dialogView.findViewById(R.id.Tv_Msg_Qestion_Message)).setText("تغییرات اعمال نشده اند.آیا برای بازگشت مطمئن هستید ؟");
                dialogView.findViewById(R.id.BtnCancellQestion).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                dialogView.findViewById(R.id.BtnConfirmQestion).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        finish();
                    }
                });
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
