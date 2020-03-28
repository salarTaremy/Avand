package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.MyCalendar;

import java.util.ArrayList;
import java.util.List;

public class MyCalendarIDbHelper extends DbHelper_Base implements IDbHelper<MyCalendar> {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "MyCalendar";
    //=============================================================
    private static String col_prDate = "prDate";
    private static String col_grDate= "grDate";
    private static String col_dayOfWeek = "dayOfWeek";
    private static String col_dayCounter = "dayCounter";
    public MyCalendarIDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS " + TableName + "(" +
            col_prDate  + " integer primary key , " +
            col_grDate + " integer ," +
            col_dayOfWeek + " integer ," +
            col_dayCounter + " integer" +
            ")"
        );
    }
    @Override
    public boolean create(final MyCalendar obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col_prDate , obj.getPrDate());
            cv.put(col_grDate, obj.getGrDate());
            cv.put(col_dayOfWeek , obj.getDayOfWeek());
            cv.put(col_dayCounter , obj.getDayCounter());
            db.insert(TableName, null, cv) ;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<MyCalendar> search(String keyword) {
        return null;
    }

    @Override
    public List<MyCalendar> findAll() {
        List<MyCalendar> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = "select " +
                col_prDate			+ "," +
                col_grDate		    + "," +
                col_dayOfWeek		+ "," +
                col_dayCounter		+ " from " +  TableName ;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                lst = new ArrayList<MyCalendar>();
                do {
                    MyCalendar Obj = new MyCalendar();
                    Obj.setPrDate (cursor.getInt(0));
                    Obj.setGrDate (cursor.getInt(1));
                    Obj.setDayOfWeek(cursor.getInt(2));
                    Obj.setDayCounter(cursor.getInt(3));
                    lst.add(Obj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }

    @Override
    public MyCalendar find(int id) {
        MyCalendar obj = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select " +
                col_prDate			+ "," +
                col_grDate		    + "," +
                col_dayOfWeek		+ "," +
                col_dayCounter		+ " from " +  TableName ;
            Cursor cursor = sqLiteDatabase.rawQuery(query + " where " + col_grDate + " = ?  " , new String[] { Integer.toString(id)  });
            if (cursor.moveToFirst()) {
                do {
                    obj  = new MyCalendar();
                    obj.setPrDate (cursor.getInt(0));
                    obj.setGrDate (cursor.getInt(1));
                    obj.setDayOfWeek(cursor.getInt(2));
                    obj.setDayCounter(cursor.getInt(3));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    @Override
    public boolean update(MyCalendar myCalendar) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }



}
