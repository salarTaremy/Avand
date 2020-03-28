package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.DailyVisitPlan;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class DailyVisitPlanDbHelper extends DbHelper_Base implements IDbHelper<DailyVisitPlan> {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "DailyVisitPlan";
    //=============================================================
    private static String col_ID = "ID";
    private static String col_ID_Visitor = "ID_Visitor";
    private static String col_PrDay = "PrDay";

    public DailyVisitPlanDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Qry = MessageFormat.format("create table IF NOT EXISTS {0}( {1} INTEGER primary key , {2} INTEGER, {3} INTEGER )", TableName,col_ID,col_ID_Visitor,col_PrDay);
        db.execSQL(Qry);
    }
    @Override
    public boolean create(DailyVisitPlan obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col_ID, obj.ID);
            cv.put(col_ID_Visitor, obj.ID_Visitor);
            cv.put(col_PrDay, obj.PrDay);
            result = db.insert(TableName, null, cv) > 0;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<DailyVisitPlan> search(String keyword) {
        return null;
    }

    @Override
    public List<DailyVisitPlan> findAll() {
        List<DailyVisitPlan> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = MessageFormat.format("select {0},{1},{2} from {3}", col_ID,col_ID_Visitor,col_PrDay, TableName);
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<DailyVisitPlan>();
                do {
                    DailyVisitPlan Obj = new DailyVisitPlan();
                    Obj.ID=cr.getInt(0);
                    Obj.ID_Visitor=cr.getInt(1);
                    Obj.PrDay=cr.getInt(2);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }

    @Override
    public DailyVisitPlan find(int id) {
        return null;
    }

    @Override
    public boolean update(DailyVisitPlan dailyVisitPlan) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
