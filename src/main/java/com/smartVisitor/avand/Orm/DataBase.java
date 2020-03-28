package com.smartVisitor.avand.Orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smartVisitor.avand.entities.Warehouse;

public class DataBase  extends SQLiteOpenHelper {
    public Table Table;
    Context context ;


    public DataBase(Context context, Table table) {
        super(context, table.dbName ,null, table.dbVersion);
        this.context = context;
        this.Table = table;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String Qry = "create table IF NOT EXISTS " +
            this.Table.Name +"("+
            this.Table.Key.Name + " "+
            this.Table.Key.Type.toString() ;
        if (this.Table.Key.AUTOINCREMENT){
            Qry += " AUTOINCREMENT,";
        }
        else {
            Qry += ",";
        }
        for (Field f: this.Table.Fields) {
            if (f.Name != this.Table.Key.Name){
                Qry += f.Name + " " + f.Type.toString() + ",";
            }
        }
        Qry = Qry.substring(0,Qry.length() - 1 ) +")";
        db.execSQL(Qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + this.Table.Name);
        onCreate(db);
    }



    public boolean create() {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();


            for (Field f: this.Table.Fields) {
                cv.put(f.Name, "1");
            }


            result = db.insert(this.Table.Name, null, cv) > 0;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }


}
