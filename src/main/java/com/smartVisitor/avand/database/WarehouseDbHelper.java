package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.Warehouse;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDbHelper extends DbHelper_Base implements IDbHelper<Warehouse> {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "Warehouse";
    //=============================================================
    private static String col_id = "id";
    private static String col_Name = "Name";
    public WarehouseDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }

    public WarehouseDbHelper(Context context, String dbName, SQLiteDatabase.CursorFactory Factory, int dbVersion, String TableName) {
        super(context, dbName, Factory, dbVersion, TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Qry =MessageFormat.format("create table IF NOT EXISTS {0}( {1} INTEGER primary key , {2} TEXT )", TableName,col_id,col_Name);
        db.execSQL(Qry);
    }
    @Override
    public boolean create(Warehouse obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col_id, obj.ID);
            cv.put(col_Name, obj.Name);
            result = db.insert(TableName, null, cv) > 0;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Warehouse> search(String keyword) {
        return null;
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = MessageFormat.format("select {0},{1} from {2}", col_id, col_Name, TableName);
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<Warehouse>();
                do {
                    Warehouse Obj = new Warehouse();
                    Obj.ID=cr.getInt(0);
                    Obj.Name=cr.getString(1);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }

    @Override
    public Warehouse find(int id) {
        return null;
    }

    @Override
    public boolean update(Warehouse warehouse) {
        return false;
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
}
