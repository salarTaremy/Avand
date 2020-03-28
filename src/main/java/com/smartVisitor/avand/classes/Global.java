package com.smartVisitor.avand.classes;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.smartVisitor.avand.database.MyCalendarIDbHelper;
import com.smartVisitor.avand.entities.MyCalendar;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Global  extends Application {
   public static String IMEI;
    public Global() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "You do not have   permission to IMEI", Toast.LENGTH_SHORT).show();
            IMEI = "0";
            return;
        }else{
            IMEI=  (String)telephonyManager.getDeviceId();
            Toast.makeText(this, IMEI, Toast.LENGTH_SHORT).show();
        }
    }

    public static   int getToday(Context context){
        Date currentTime = Calendar.getInstance().getTime();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String strDate = dateFormat.format(currentTime);
        MyCalendarIDbHelper db = new MyCalendarIDbHelper(context);
        MyCalendar my =  db.find( Integer.valueOf(strDate ));
        return  my.getPrDate();
    }

    public static   String getTodayString(Context context){
        Date currentTime = Calendar.getInstance().getTime();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String strDate = dateFormat.format(currentTime);
        MyCalendarIDbHelper db = new MyCalendarIDbHelper(context);
        MyCalendar my =  db.find( Integer.valueOf(strDate ));
        Integer value = my.getPrDate();
        return  String.valueOf(value).substring(0,4) + "/" + String.valueOf(value).substring(4,6) + "/" + String.valueOf(value).substring(6,8);

    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }
    public static Object isNull(Object obj,Object value) {
        if( obj == null){
            return value;
        }
        else {
            return obj;
        }
    }
    public  static   boolean internetConnect(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    public static String ThisTime(){
        Date currentTime = Calendar.getInstance().getTime();
        Timestamp ts=new Timestamp(currentTime.getTime());
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        String Ret = formatter.format(ts);
        return Ret;
    }

}
