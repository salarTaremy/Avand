package com.smartVisitor.avand.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.MessageFormat;

public class DbHelper_Base extends SQLiteOpenHelper {
    private  String dbName ;
    private  int dbVersion ;
    private  String TableName ;
    private static String col_id = "id";
    public DbHelper_Base(Context context , String dbName, SQLiteDatabase.CursorFactory Factory, int dbVersion, String TableName) {
        super(context, dbName,Factory, dbVersion);
        this.dbName = dbName;
        this.dbVersion = dbVersion;
        this.TableName = TableName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        onCreate(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MessageFormat.format("drop table if exists {0}", TableName));
        onCreate(db);
    }



    public int Count() {
        try {
            int Cnt = 0;
            SQLiteDatabase db = getReadableDatabase();
            String query = MessageFormat.format("select Count(*) As MaxID from {0}", TableName);
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    Cnt = cursor.getInt(0);
                }
                cursor.close();
            }
            return Cnt;
        } catch (Exception e) {
            return 0;
        }
    }

    public int Max( String ColumnName) {
        try {
            int max = 0 ;
            SQLiteDatabase db = getReadableDatabase();
            String query = MessageFormat.format("select max({0}) As Max from {1}",ColumnName,  TableName);
            Cursor cursor = db.rawQuery(query, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    max =  cursor.getInt(0);
                }
                cursor.close();
            }
            return max;
        } catch (Exception e) {
            return 0 ;
    }

    }


    public boolean delete() {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            result = db.delete(TableName,null,null) > 0 ;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

}
