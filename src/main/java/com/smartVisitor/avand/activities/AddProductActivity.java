package com.smartVisitor.avand.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.smartVisitor.avand.R;
import com.smartVisitor.avand.classes.Convert;
import com.smartVisitor.avand.classes.Global;
import com.smartVisitor.avand.dataTransferObjects.ProductDTO;
import com.smartVisitor.avand.database.CustomerDbHelper;
import com.smartVisitor.avand.database.GeneralDbHelper;
import com.smartVisitor.avand.database.PriceListDbHelpr;
import com.smartVisitor.avand.entities.Customer;
import com.smartVisitor.avand.entities.Order;
import com.smartVisitor.avand.entities.PriceList;
import com.smartVisitor.avand.entities.PriceListDetail;

import life.sabujak.roundedbutton.RoundedButton;
import pl.polak.clicknumberpicker.ClickNumberPickerListener;
import pl.polak.clicknumberpicker.ClickNumberPickerView;
import pl.polak.clicknumberpicker.PickerClickType;

public class AddProductActivity extends AppCompatActivity  {
    private Context context;
    private ProductDTO product;
    private Order order;
    private  PriceListDetail priceListDetail;
    private int position;
    private int Qty,QtyK,Offer,TotalCount,Tax;
    private TextView Tv_AddProduct_NameKala,Tv_AddKala_tdk_val,Tv_AddKala_tdks_val,Tv_AddKala_Fee_val,Tv_AddKala_Arz_val,Tv_AddKala_Discount_val,Tv_AddKala_TotalRls_val,Tv_AddKala_Custo,Tv_AddKala_Mv_val,Tv_AddKala_Count_val,Tv_AddKala_CreditCount_val,Tv_AddKala_Detail_val,Tv_AddKala_CustomerRls_val;
    private ClickNumberPickerView NumPickerQty,NumPickerQtyK,NumPickerOffer;
    private ImageView ImgBtnDelete,ImgBtnConfirm,ImgBtnRefresh;
    private  FloatingActionButton fab_Product;
    private  FABToolbarLayout fab_Menu_Product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        this.setFinishOnTouchOutside(false);
        this.context = this;
        this.product = (ProductDTO)getIntent().getSerializableExtra("Product");
        this.order = (Order) getIntent().getSerializableExtra("Order");
        this.position = getIntent().getIntExtra("position",-1);
        InitView();
        SetOnClick();
        LoadData();
        //LoadDataDB();
    }

//    private void LoadDataDB() {
////        CustomerDbHelper dbC = new CustomerDbHelper(context);
////        this.customer = dbC.find(this.order.customer);
//        //GeneralDbHelper Gdb = new GeneralDbHelper(context);
//        //this.priceList = Gdb.PriceListGetByCustomer (this.order.priceList);
//        //55555
//    }

    private void SetOnClick() {
        this.NumPickerQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumPickerClick(NumPickerQty);
            }
        });
        this.NumPickerQtyK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumPickerClick(NumPickerQtyK);
            }
        });
        this.NumPickerOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumPickerClick(NumPickerOffer);
            }
        });
        this.NumPickerQty.setClickNumberPickerListener(new ClickNumberPickerListener() {
            @Override
            public void onValueChange(float previousValue, float currentValue, PickerClickType pickerClickType) {
                Qty = (int) currentValue;
                compute();
            }
        });
        this.NumPickerQtyK.setClickNumberPickerListener(new ClickNumberPickerListener() {
            @Override
            public void onValueChange(float previousValue, float currentValue, PickerClickType pickerClickType) {
                QtyK = (int)currentValue;
                compute();
            }
        });
        this.NumPickerOffer.setClickNumberPickerListener(new ClickNumberPickerListener() {
            @Override
            public void onValueChange(float previousValue, float currentValue, PickerClickType pickerClickType) {
                Offer = (int)currentValue;
                compute();
            }
        });
        this.ImgBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !Validate()){
                    final View dialogView = LayoutInflater.from(context ).inflate(R.layout.dialog_info_layout , null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context).setCancelable(false).setView(dialogView)  ;
                    final AlertDialog alertDialog = builder.show();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    ((TextView)dialogView.findViewById(R.id.Tv_Msg_Inf_Title)).setText("خطا");
                    ((TextView)dialogView.findViewById(R.id.Tv_Msg_Inf_Message)).setText("مقادیر نا معتبر است");
                    dialogView.findViewById(R.id.BtnConfirmInf).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    return;
                }
                Confirm();
            }
        });
//        this.BtnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setResult(Activity.RESULT_CANCELED,null);
//                finish();
//            }
//        });
        this.ImgBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        this.ImgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearAll();
                Confirm();
            }
        });
        this.fab_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_Menu_Product.show();
            }
        });
    }
    private void LoadData() {
        GeneralDbHelper db = new GeneralDbHelper(context);
        this.priceListDetail= db.PriceListDetailGetByProductIDAndPriceID( this.order.priceList.ID ,this.product.id );
        if (   this.priceListDetail == null ){
            final View dialogView = LayoutInflater.from(context ).inflate(R.layout.dialog_info_layout , null);
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setCancelable(false).setView(dialogView)  ;
            final AlertDialog alertDialog = builder.show();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ((TextView)dialogView.findViewById(R.id.Tv_Msg_Inf_Title)).setText("خطا");
            ((TextView)dialogView.findViewById(R.id.Tv_Msg_Inf_Message)).setText("متاسفانه مبلغ کالای فوق را برای مشتری جاری تعریف نشده است ");
            dialogView.findViewById(R.id.BtnConfirmInf).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
            this.Tv_AddKala_Fee_val.setText(Convert.toSeprate( 0 ));
            this.Tv_AddKala_CustomerRls_val.setText(Convert.toSeprate( 0));
            this.Tv_AddKala_Mv_val.setText(Convert.toSeprate( 0 ));
        }else {
            this.Tv_AddKala_Fee_val.setText(Convert.toSeprate( (int)Global.isNull(this.priceListDetail.Price,0) ));
            this.Tv_AddKala_CustomerRls_val.setText(Convert.toSeprate( (int)Global.isNull(this.priceListDetail.ConsumerPrice,0)));
            this.Tv_AddKala_Mv_val.setText(Convert.toSeprate( (int)Global.isNull(this.priceListDetail.Duration,0) ));
        }
        this.Tv_AddProduct_NameKala.setText(this.product.getName());
        this.Tv_AddKala_tdk_val.setText(Convert.toSeprate( this.product.getCountInBox()));
        this.Tv_AddKala_Count_val.setText(Convert.toSeprate( this.product.Inventory));
        this.Qty =(int)Global.isNull(product.SelectedCount,0) % product.countInBox;
        this.QtyK =(int)Global.isNull(product.SelectedCount,0) / product.countInBox;
        this.Offer =(int)Global.isNull(product.SelectedOffer,0);
        //this.Tax =(int)Global.isNull(product.SelectedTax,0);
        this.NumPickerQty.setPickerValue( Qty  );
        this.NumPickerQtyK.setPickerValue( QtyK  );
        this.NumPickerOffer.setPickerValue( Offer  );
        if ( TotalCount <= 0 ){
            View v = this.ImgBtnDelete;
            v.setVisibility(View.GONE);
        }
    }

    private void InitView() {
       this.Tv_AddProduct_NameKala = findViewById(R.id.Tv_AddProduct_NameKala );
        this.Tv_AddKala_tdk_val				= findViewById(R.id.Tv_AddKala_tdk_val);
        this.Tv_AddKala_tdks_val			= findViewById(R.id.Tv_AddKala_tdks_val);
        this.Tv_AddKala_Fee_val				= findViewById(R.id.Tv_AddKala_Fee_val);
        this.Tv_AddKala_Arz_val				= findViewById(R.id.Tv_AddKala_Arz_val);
        this.Tv_AddKala_Discount_val		= findViewById(R.id.Tv_AddKala_Discount_val);
        this.Tv_AddKala_TotalRls_val		= findViewById(R.id.Tv_AddKala_TotalRls_val);
        this.Tv_AddKala_Mv_val				= findViewById(R.id.Tv_AddKala_Mv_val);
        this.Tv_AddKala_Count_val			= findViewById(R.id.Tv_AddKala_Count_val);
        this.Tv_AddKala_CreditCount_val		= findViewById(R.id.Tv_AddKala_CreditCount_val);
        this.Tv_AddKala_Detail_val			= findViewById(R.id.Tv_AddKala_Detail_val);
        this.Tv_AddKala_CustomerRls_val		= findViewById(R.id.Tv_AddKala_CustomerRls_val);
        this.NumPickerQty                   = findViewById(R.id.NumPickerQty );
        this.NumPickerQtyK                  = findViewById(R.id.NumPickerQtyK );
        this.NumPickerOffer                 = findViewById(R.id.NumPickerOffer );
        this.ImgBtnConfirm                  = findViewById(R.id.ImgBtnConfirm);
        this.ImgBtnDelete                   = findViewById(R.id.ImgBtnDelete);
        this.ImgBtnRefresh                  = findViewById(R.id.ImgBtnRefresh);
        this.fab_Product                    = findViewById(R.id.fab_Product);
        this.fab_Menu_Product               = findViewById(R.id.fab_Menu_Product);
    }

    private  void NumPickerClick( final ClickNumberPickerView Nmp){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_enter_number, null);
        builder.setView(dialogView);
        final TextView Tv  = dialogView.findViewById(R.id.Tv_DialogTitle);
        final EditText myTextView = dialogView.findViewById(R.id.tv_number);
        final RoundedButton btnCancel = dialogView.findViewById(R.id.BtnCancel);
        final RoundedButton btnOk = dialogView.findViewById(R.id.BtnOk);
        Tv.setText("تعداد را وارد کنید");
        final AlertDialog alertDialog = builder.show();
        myTextView.setFocusable(true);
        myTextView.requestFocus();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nmp.setPickerValue(   Float.parseFloat( myTextView.getText().toString()) );
                alertDialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    private void Confirm() {
        int SelectedCount = Qty + (QtyK * product.countInBox);
        product.SelectedCount = SelectedCount;
        product.SelectedOffer = Offer;
        product.SelectedTax = this.priceListDetail.Tax;
        product.SelectedPrice = this.priceListDetail.Price;
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Product",product);
        returnIntent.putExtra("position",position);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    private boolean Validate() {
        if (Qty <= 0 && QtyK <= 0 && Offer <= 0   ){
            return  false;
        }
        return  true;
    }

    private void compute() {
        int CountInBox = this.product.getCountInBox();
        TotalCount = Offer + Qty +(QtyK * CountInBox);
        int TotalPrice = (TotalCount -Offer) * this.priceListDetail.Price;
        this.Tax = TotalPrice * this.priceListDetail.Tax / 100 ;
        this.Tv_AddKala_Arz_val.setText(Convert.toSeprate(this.Tax));
        this.Tv_AddKala_tdks_val.setText(Convert.toSeprate(TotalCount));
        this.Tv_AddKala_TotalRls_val.setText(Convert.toSeprate(TotalPrice + Tax));
    }
    private void ClearAll() {
        this.NumPickerQty.setPickerValue(0);
        this.NumPickerQtyK.setPickerValue(0);
        this.NumPickerOffer.setPickerValue(0);
        this.Qty= 0;
        this.QtyK = 0 ;
        this.Offer = 0;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (fab_Menu_Product.isToolbar()){
                fab_Menu_Product.hide();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
