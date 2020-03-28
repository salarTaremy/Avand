package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.DailyVisitPlanDetail;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class DailyVisitPlanDetailDbHelper extends DbHelper_Base implements IDbHelper<DailyVisitPlanDetail> {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "DailyVisitPlanDetail";
    //=============================================================
    private static String col_ID = "ID";
    private static String col_ID_Plan = "ID_Plan";
    private static String col_ID_Customer = "ID_Customer";
    public DailyVisitPlanDetailDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String Qry =MessageFormat.format("create table IF NOT EXISTS {0}( {1} INTEGER , {2} INTEGER, {3} INTEGER )", TableName,col_ID,col_ID_Plan,col_ID_Customer);
        db.execSQL(Qry);
    }
    @Override
    public boolean create(DailyVisitPlanDetail obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col_ID, obj.ID);
            cv.put(col_ID_Plan, obj.ID_Plan);
            cv.put(col_ID_Customer, obj.ID_Customer);
            result = db.insert(TableName, null, cv) > 0;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<DailyVisitPlanDetail> search(String keyword) {
        return null;
    }

    @Override
    public List<DailyVisitPlanDetail> findAll() {
        List<DailyVisitPlanDetail> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = MessageFormat.format("select {0},{1},{2} from {3}", col_ID,col_ID_Plan,col_ID_Customer, TableName);
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<DailyVisitPlanDetail>();
                do {
                    DailyVisitPlanDetail Obj = new DailyVisitPlanDetail();
                    Obj.ID=cr.getInt(0);
                    Obj.ID_Plan=cr.getInt(1);
                    Obj.ID_Customer=cr.getInt(2);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }

    @Override
    public DailyVisitPlanDetail find(int id) {
        return null;
    }

    @Override
    public boolean update(DailyVisitPlanDetail dailyVisitPlanDetail) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
