package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.LinkPriceListCustomer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class LinkPriceListCustomerDbhelper extends DbHelper_Base implements IDbHelper<LinkPriceListCustomer> {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "LinkPriceListCustomer";
    //=============================================================
    private static String col_ID = "ID";
    private static String col_ID_PriceList = "ID_PriceList";
    private static String col_ID_CustomerType = "ID_CustomerType";
    private static String col_ID_CustomerGroup = "ID_CustomerGroup";
    public LinkPriceListCustomerDbhelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }

    public LinkPriceListCustomerDbhelper(Context context, String dbName, SQLiteDatabase.CursorFactory Factory, int dbVersion, String TableName) {
        super(context, dbName, Factory, dbVersion, TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Qry =MessageFormat.format("create table IF NOT EXISTS {0}( {1} INTEGER primary key , {2} INTEGER, {3} INTEGER, {4} INTEGER )", TableName,col_ID,col_ID_PriceList,col_ID_CustomerType,col_ID_CustomerGroup);
        db.execSQL(Qry);
    }

    @Override
    public boolean create(LinkPriceListCustomer obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col_ID, obj.ID);
            cv.put(col_ID_PriceList, obj.ID_PriceList);
            cv.put(col_ID_CustomerType, obj.ID_CustomerType);
            cv.put(col_ID_CustomerGroup, obj.ID_CustomerGroup);
            result = db.insert(TableName, null, cv) > 0;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<LinkPriceListCustomer> search(String keyword) {
        return null;
    }

    @Override
    public List<LinkPriceListCustomer> findAll() {
        List<LinkPriceListCustomer> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = MessageFormat.format("select {0},{1},{2},{3},{4} from {5}", col_ID, col_ID_PriceList,col_ID_CustomerType,col_ID_CustomerGroup, TableName);
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<LinkPriceListCustomer>();
                do {
                    LinkPriceListCustomer Obj = new LinkPriceListCustomer();
                    Obj.ID=cr.getInt(0);
                    Obj.ID_PriceList=cr.getInt(1);
                    Obj.ID_CustomerType=cr.getInt(2);
                    Obj.ID_CustomerGroup=cr.getInt(3);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }

    @Override
    public LinkPriceListCustomer find(int id) {
        return null;
    }

    @Override
    public boolean update(LinkPriceListCustomer warehouse) {
        return false;
    }

    public boolean delete(int id) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            result = db.delete(TableName, col_ID + " = ?", new String[]{ String.valueOf(id) }) > 0;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
