package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.PriceListDetail;

import java.text.MessageFormat;
import java.util.List;

public class PriceListDetailIDbHelper extends DbHelper_Base implements IDbHelper<PriceListDetail> {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "PriceListDetail";
    //=============================================================
    private static String col_ID = "ID";
    private static String col_ID_PriceList = "ID_PriceList";
    private static String col_ID_Product = "ID_Product";
    private static String col_Price = "Price";
    private static String col_ConsumerPrice = "ConsumerPrice";
    private static String col_Tax = "Tax";
    private static String col_Duration = "Duration";
    //=============================================================
    public PriceListDetailIDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }
    public PriceListDetailIDbHelper(Context context, String dbName, SQLiteDatabase.CursorFactory Factory, int dbVersion, String TableName) {
        super(context, dbName, Factory, dbVersion, TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //integer primary key AUTOINCREMENT
        String Qry =MessageFormat.format("create table IF NOT EXISTS {0}( {1} INTEGER primary key , {2} INTEGER, {3} INTEGER, {4} INTEGER, {5} INTEGER, {6} INTEGER, {7} INTEGER )", TableName,col_ID,col_ID_PriceList,col_ID_Product,col_Price,col_ConsumerPrice,col_Tax,col_Duration);
        db.execSQL(Qry);
    }
    @Override
    public boolean create(PriceListDetail obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col_ID, obj.ID);
            cv.put(col_ID_PriceList, obj.ID_PriceList);
            cv.put(col_ID_Product, obj.ID_Product);
            cv.put(col_Price, obj.Price);
            cv.put(col_ConsumerPrice, obj.ConsumerPrice);
            cv.put(col_Tax, obj.Tax);
            cv.put(col_Duration, obj.Duration);
            result = db.insert(TableName, null, cv) > 0;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<PriceListDetail> search(String keyword) {
        return null;
    }

    @Override
    public List<PriceListDetail> findAll() {
        return null;
    }

    @Override
    public PriceListDetail find(int id) {
        return null;
    }

    public PriceListDetail find(int ID_PriceList ,int ID_Product) {
        PriceListDetail Obj = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = MessageFormat.format("select {0},{1},{2},{3},{4},{5},{6} from {7}", col_ID,col_ID_PriceList,col_ID_Product,col_Price,col_ConsumerPrice,col_Tax,col_Duration, TableName);
            Cursor cr = sqLiteDatabase.rawQuery(MessageFormat.format("{0} where {1} = ?  ", query, col_ID_PriceList,col_ID_Product), new String[] { Integer.toString(ID_PriceList) ,Integer.toString(ID_Product)  });
            if (cr.moveToFirst()) {
                do {
                    Obj  = new PriceListDetail();
                    Obj.ID=cr.getInt(0);
                    Obj.ID_PriceList=cr.getInt(1);
                    Obj.ID_Product=cr.getInt(2);
                    Obj.Price=cr.getInt(3);
                    Obj.ConsumerPrice=cr.getInt(4);
                    Obj.Tax=cr.getInt(5);
                    Obj.Duration=cr.getInt(6);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            Obj = null;
        }
        return Obj;
    }

    @Override
    public boolean update(PriceListDetail priceListDetail) {
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
