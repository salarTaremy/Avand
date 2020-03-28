package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.Inventory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class InventoryDbHelper extends DbHelper_Base implements IDbHelper<Inventory> {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "Inventory";
    //=============================================================
    private static String col_ID = "ID";
    private static String col_ID_Warehouse = "ID_Warehouse";
    private static String col_ID_Product = "ID_Product";
    private static String col_Quantity = "Quantity";
    public InventoryDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Qry = MessageFormat.format("create table IF NOT EXISTS {0}( {1} INTEGER primary key , {2} INTEGER, {3} INTEGER, {4} INTEGER )", TableName,col_ID,col_ID_Warehouse,col_ID_Product,col_Quantity);
        db.execSQL(Qry);
    }

    @Override
    public boolean create(Inventory obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col_ID, obj.ID);
            cv.put(col_ID_Warehouse, obj.ID_Warehouse);
            cv.put(col_ID_Product, obj.ID_Product);
            cv.put(col_Quantity, obj.Quantity);
            result = db.insert(TableName, null, cv) > 0;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Inventory> search(String keyword) {
        return null;
    }

    @Override
    public List<Inventory> findAll() {
        List<Inventory> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = MessageFormat.format("select {0},{1},{2},{3} from {4}", col_ID,col_ID_Warehouse,col_ID_Product,col_Quantity, TableName);
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<Inventory>();
                do {
                    Inventory Obj = new Inventory();
                    Obj.ID=cr.getInt(0);
                    Obj.ID_Warehouse=cr.getInt(1);
                    Obj.ID_Product=cr.getInt(2);
                    Obj.Quantity=cr.getInt(3);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }

    @Override
    public Inventory find(int id) {
        return null;
    }

    @Override
    public boolean update(Inventory inventory) {
        return false;
    }

    @Override
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
