package com.smartVisitor.avand.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.fafaldo.fabtoolbar.widget.FABToolbarLayout;
import com.smartVisitor.avand.Orm.DataBase;
import com.smartVisitor.avand.Orm.Field;
import com.smartVisitor.avand.Orm.Table;
import com.smartVisitor.avand.Orm.Types;
import com.smartVisitor.avand.R;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.smartVisitor.avand.classes.ConnectivityReceiver;
import com.smartVisitor.avand.classes.Global;
import com.smartVisitor.avand.classes.MessageBox;

import java.util.ArrayList;
import java.util.List;

import info.hoang8f.android.segmented.SegmentedGroup;
import life.sabujak.roundedbutton.RoundedButton;
import pl.polak.clicknumberpicker.ClickNumberPickerListener;
import pl.polak.clicknumberpicker.ClickNumberPickerView;
import pl.polak.clicknumberpicker.PickerClickType;


public class TestActivity extends AppCompatActivity {

    @NotEmpty
    private EditText edt_code;
    @NotEmpty
    private EditText edt_name;
    private EditText edt_id_grp;
    private ClickNumberPickerView num_picker;
    private Context context;
    private  RoundedButton btn1,btn2,btn3;
    private FABToolbarLayout NewFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
        OrmTest();
    }

    private void OrmTest() {
        //        //orm
        //        List<Field> f = new ArrayList<Field>();
        //        f.add(new Field( "id", Types.DbType.INTEGER,true ));
        //        f.add(new Field( "Name", Types.DbType.TEXT));
        //        Table t = new Table("SmartVisitorDB",1,"test",f);
        //        DataBase db = new DataBase( this ,t);
        //        db.create();
    }

    private void initView() {
        this.context = this;
        this.edt_code = findViewById(R.id.edt_code);
        this.edt_name = findViewById(R.id.edt_name);
        this.edt_id_grp = findViewById(R.id.edt_id_grp);
        this.num_picker = findViewById(R.id.Test_number_picker4);
        this.btn1 = findViewById(R.id.BtnTest1);
        this.btn2 = findViewById(R.id.BtnTest2);
        this.btn3 = findViewById(R.id.BtnTest3);

        this.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             new MessageBox(context).ShowQuestion("sol", "Aya", new MessageBox.OnYesClickListener() {
                     @Override
                     public void onClick(View v) {
                         Toast.makeText(context, "yes", Toast.LENGTH_LONG).show();
                     }
                 }
                 , new MessageBox.OnNoClickListener() {
                     @Override
                     public void onClick(View v) {
                         Toast.makeText(context, "No", Toast.LENGTH_LONG).show();
                     }
                 }

             );




            }
        });
        this.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    TestNetworkConnection();
            }
        });
        this.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    TestInternet();
            }
        });

        this.num_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(false);
                final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_enter_number, null);
                builder.setView(dialogView);
                final TextView Tv  = dialogView.findViewById(R.id.Tv_DialogTitle);
                Tv.setText("تعداد کالا را وارد کنید");
                final EditText myTextView = dialogView.findViewById(R.id.tv_number);
                final RoundedButton btnCancel = dialogView.findViewById(R.id.BtnCancel);
                final RoundedButton btnOk = dialogView.findViewById(R.id.BtnOk);
                //myTextView.setText( Float.toString(num_picker.getValue()));
                final AlertDialog alertDialog = builder.show();
                myTextView.setFocusable(true);
                myTextView.requestFocus();
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        num_picker.setPickerValue(   Float.parseFloat( myTextView.getText().toString()) );
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
        });


        this.NewFab = findViewById(R.id.fabtoolbar);
        this.NewFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewFab.show();
                //NewFab.hide();
            }
        });

        this.num_picker.setClickNumberPickerListener(new ClickNumberPickerListener() {
            @Override
            public void onValueChange(float previousValue, float currentValue, PickerClickType pickerClickType) {
                Toast.makeText(context, Float.toString(currentValue),  Toast.LENGTH_SHORT).show();
            }
        });


        SegmentedGroup segmented2 = (SegmentedGroup) findViewById(R.id.segmented2);
        segmented2.setTintColor(getResources().getColor(R.color.colorAccent));


    }

    private void TestInternet() {
        //Global g = new Global();
        if (Global.internetConnect()  == false ){
            Toast.makeText(context, "Not Connect", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Connect", Toast.LENGTH_SHORT).show();
        }

    }

    private void TestNetworkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected( this.getApplicationContext());
        if (isConnected){
            Toast.makeText(this,"ON" ,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Disconnect" ,Toast.LENGTH_SHORT).show();
        }
    }

    public void ImgBtn1_Click(View view) {
        Toast.makeText(this,"ImgBtn1_Click" ,Toast.LENGTH_SHORT).show();
        NewFab.hide();
    }

    public void ImgBtn2_Click(View view) {
        Toast.makeText(this,"ImgBtn2_Click" ,Toast.LENGTH_SHORT).show();
        NewFab.hide();
    }
}
