package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.OrderItem;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDbHelper extends DbHelper_Base implements IDbHelper<OrderItem> {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "OrderItem";
    //=============================================================
    private static String col_id = "id";
    private static String col_ID_order = "ID_order";
    private static String col_ID_Product = "ID_Product";
    private static String col_Qty = "Qty";
    private static String col_Offer = "Offer";
    private static String col_Price = "Price";
    private static String col_Tax = "Tax";
    public OrderItemDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sfsdf = "dsa "  + TableName  + " sdfsdf " + col_id ;
        //integer primary key AUTOINCREMENT
        String Qry = MessageFormat.format("create table IF NOT EXISTS {0}({1} integer primary key AUTOINCREMENT , {2} integer ,{3} integer ,{4} integer ,{5} integer ,{6} integer ,{7} integer )"
            , TableName
            , col_id
            , col_ID_order
            , col_ID_Product
            , col_Qty
            , col_Offer
            , col_Price
            ,col_Tax) ;
        db.execSQL(Qry);
    }
    public boolean create(OrderItem obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            //cv.put(col_id, obj.ID);
            cv.put(col_ID_order, obj.ID_order);
            cv.put(col_ID_Product, obj.ID_Product);
            cv.put(col_Qty, obj.Qty);
            cv.put(col_Offer, obj.Offer);
            cv.put(col_Price, obj.Price);
            //cv.put(col_Tax, obj.Tax);
            result = db.insert(TableName, null, cv) > 0;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    @Override
    public List<OrderItem> search(String keyword) {
        return null;
    }
    public List<OrderItem> findAll() {
        List<OrderItem> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = MessageFormat.format("select {0},{1},{2},{3},{4},{5},{6}from {7}"
                , col_id
                , col_ID_order
                , col_ID_Product
                , col_Qty
                , col_Offer
                , col_Price
                , col_Tax
                , TableName);
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<OrderItem>();
                do {
                    OrderItem Obj = new OrderItem();
                    Obj.ID =cr.getInt(0);
                    Obj.ID_order = cr.getInt(1);
                    Obj.ID_Product = cr.getInt(2);
                    Obj.Qty = cr.getInt(3);
                    Obj.Offer = cr.getInt(4);
                    Obj.Price = cr.getInt(5);
                    Obj.Tax = cr.getInt(6);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }
    public boolean delete(int id) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            result = db.delete(TableName, col_id + " = ?", new String[]{ String.valueOf(id) }) > 0;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    public OrderItem find(int id) {
        OrderItem Obj = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = MessageFormat.format("select {0},{1},{2},{3},{4},{5},{6}from {7}"
                , col_id
                , col_ID_order
                , col_ID_Product
                , col_Qty
                , col_Offer
                , col_Price
                , col_Tax
                , TableName);
            Cursor cr = sqLiteDatabase.rawQuery(MessageFormat.format("{0} where {1} = ?  ", query, col_id), new String[] { Integer.toString(id)  });
            if (cr.moveToFirst()) {
                do {
                    Obj  = new OrderItem();
                    Obj.ID =cr.getInt(0);
                    Obj.ID_order = cr.getInt(1);
                    Obj.ID_Product = cr.getInt(2);
                    Obj.Qty = cr.getInt(3);
                    Obj.Offer = cr.getInt(4);
                    Obj.Price = cr.getInt(5);
                    Obj.Tax = cr.getInt(6);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            Obj = null;
        }
        return Obj;
    }
    @Override
    public boolean update(OrderItem orderItem) {
        return false;
    }
}
