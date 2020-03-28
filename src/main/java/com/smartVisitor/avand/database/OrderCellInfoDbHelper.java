package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.ViewModels.OrderCellInfoViewModel;

import java.text.MessageFormat;
import java.util.List;

public class OrderCellInfoDbHelper extends DbHelper_Base implements IDbHelper<OrderCellInfoViewModel>  {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "OrderCellInfo";
    //=============================================================
    private static String col_id = "id";
    private static String col_ID_order = "ID_order";
    private static String col_mcc = "mcc";
    private static String col_mnc = "mnc";
    private static String col_cellid = "cellid";
    private static String col_lac = "lac";
    private static String col_OperatorName = "OperatorName";
    private static String col_IsRegistered = "IsRegistered";
    public OrderCellInfoDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }

    public OrderCellInfoDbHelper(Context context, String dbName, SQLiteDatabase.CursorFactory Factory, int dbVersion, String TableName) {
        super(context, dbName, Factory, dbVersion, TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Qry = MessageFormat.format("create table IF NOT EXISTS {0}( {1} INTEGER primary key  AUTOINCREMENT , {2} integer, {3} TEXT, {4} TEXT, {5} TEXT, {6} TEXT, {7} TEXT)"
            , TableName,col_id,col_ID_order,col_mcc,col_mnc,col_cellid,col_lac,col_OperatorName);
        db.execSQL(Qry);
    }



    @Override
    public boolean create(OrderCellInfoViewModel obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            //cv.put(col_id, obj.id);
            cv.put(col_ID_order, obj.ID_order);
            cv.put(col_mcc, obj.mcc);
            cv.put(col_mnc, obj.mnc);
            cv.put(col_cellid, obj.cellid);
            cv.put(col_lac, obj.lac);
            cv.put(col_OperatorName, obj.OperatorName);
            result = db.insert(TableName, null, cv) > 0;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<OrderCellInfoViewModel> search(String keyword) {
        return null;
    }

    @Override
    public List<OrderCellInfoViewModel> findAll() {
        return null;
    }

    @Override
    public OrderCellInfoViewModel find(int id) {
        return null;
    }

    @Override
    public boolean update(OrderCellInfoViewModel obj) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
