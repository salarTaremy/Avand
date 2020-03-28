package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.Customer;

import java.util.ArrayList;
import java.util.List;
public class CustomerDbHelper extends DbHelper_Base implements IDbHelper<Customer> {
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "Customer";
    //=============================================================
    private static String col_id			 ="id";
    private static String col_Code			 ="Code";
    private static String col_Name			 ="Name";
    private static String col_F_Name		 ="F_Name";
    private static String col_L_name		 ="L_name";
    private static String col_IdType		 ="IdType";
    private static String col_IdGroup		 ="IdGroup";
    private static String col_tel			 ="tel";
    private static String col_mob			 ="mobile";
    private static String col_mail			 ="mail";
    private static String col_OpenInvoice	 ="OpenInvoice";
    private static String col_Debt			 ="Debt";
    private static String col_AvgDate		 ="AvgDate";
    private static String col_rate			 ="rate";
    private static String col_Description	 ="Description";
    private static String col_Address		 ="Address";
    private static String col_x				 ="x";
    private static String col_y				 ="y";
    public CustomerDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS " + TableName + "(" +
            col_id               + " integer primary key , " +
            col_Code			 + " text," +
            col_Name			 + " text," +
            col_F_Name			 + " text," +
            col_L_name			 + " text," +
            col_IdType			 + " integer," +
            col_IdGroup			 + " integer," +
            col_tel				 + " text," +
            col_mob				 + " text," +
            col_mail			 + " text," +
            col_OpenInvoice		 + " integer," +
            col_Debt			 + " integer," +
            col_AvgDate			 + " integer," +
            col_rate			 + " integer," +
            col_Description		 + " text," +
            col_Address			 + " text," +
            col_x				 + " text," +
            col_y 				 + " text" +
            ")"
        );
    }

    @Override
    public boolean create(Customer obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col_id, obj.getId());
            cv.put(col_Code, obj.getCode());
            cv.put(col_Name, obj.getName());
            cv.put(col_F_Name, obj.getFName());
            cv.put(col_L_name, obj.getLName());
            cv.put(col_IdType, obj.getIdType());
            cv.put(col_IdGroup, obj.getIdGroup());
            cv.put(col_tel, obj.getTel());
            cv.put(col_mob, obj.getMobile());
            cv.put(col_mail , obj.getMail());
            cv.put(col_OpenInvoice, obj.getOpenInvoice());
            cv.put(col_Debt, obj.getDebt());
            cv.put(col_AvgDate, obj.getAvgDate());
            cv.put(col_rate, obj.getRate());
            cv.put(col_Description, obj.getDescription());
            cv.put(col_Address, obj.getAddress());
            cv.put(col_x, obj.getX());
            cv.put(col_y, obj.getY());
            result = db.insert(TableName, null, cv) > 0;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    public List<Customer> search(String keyword) {
        List<Customer> lst = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select " +
                col_id			+ "," +
                col_Code		+ "," +
                col_Name		+ "," +
                col_F_Name		+ "," +
                col_L_name		+ "," +
                col_IdType		+ "," +
                col_IdGroup		+ "," +
                col_tel			+ "," +
                col_mob			+ "," +
                col_mail		+ "," +
                col_OpenInvoice	+ "," +
                col_Debt		+ "," +
                col_AvgDate		+ "," +
                col_rate		+ "," +
                col_Description	+ "," +
                col_Address		+ "," +
                col_x			+ "," +
                col_y 			+ "," +
                col_rate + " from " +  TableName ;
            Cursor cursor = sqLiteDatabase.rawQuery(query + " where " + col_Name + " like ? or " + col_Code + " like ?", new String[] { "%" + keyword + "%" , "%" + keyword + "%"  });
            if (cursor.moveToFirst()) {
                lst = new ArrayList<Customer>();
                do {
                    Customer Obj = new Customer();
                    Obj.setId (cursor.getInt(0));
                    Obj.setCode(cursor.getString(1));
                    Obj.setName(cursor.getString(2));
                    Obj.setFName(cursor.getString(3));
                    Obj.setLName(cursor.getString(4));
                    Obj.setIdType(cursor.getInt(5));
                    Obj.setIdGroup(cursor.getInt(6));
                    Obj.setTel(cursor.getString(7));
                    Obj.setMobile(cursor.getString(8));
                    Obj.setMail(cursor.getString(9));
                    Obj.setOpenInvoice(cursor.getInt(10));
                    Obj.setDebt(cursor.getLong(11));
                    Obj.setAvgDate(cursor.getInt(12));
                    Obj.setRate(cursor.getInt(13));
                    Obj.setDescription(cursor.getString(14));
                    Obj.setAddress(cursor.getString(15));
                    Obj.setX(cursor.getString(16));
                    Obj.setY(cursor.getString(17));
                    lst.add(Obj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = "select " +
                col_id			+ "," +
                col_Code		+ "," +
                col_Name		+ "," +
                col_F_Name		+ "," +
                col_L_name		+ "," +
                col_IdType		+ "," +
                col_IdGroup		+ "," +
                col_tel			+ "," +
                col_mob			+ "," +
                col_mail			+ "," +
                col_OpenInvoice	+ "," +
                col_Debt		+ "," +
                col_AvgDate		+ "," +
                col_rate		+ "," +
                col_Description	+ "," +
                col_Address		+ "," +
                col_x			+ "," +
                col_y 			+ "," +
                col_rate + " from " +  TableName ;
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                lst = new ArrayList<Customer>();
                do {
                    Customer Obj = new Customer();
                    Obj.setId (cursor.getInt(0));
                    Obj.setCode(cursor.getString(1));
                    Obj.setName(cursor.getString(2));
                    Obj.setFName(cursor.getString(3));
                    Obj.setLName(cursor.getString(4));
                    Obj.setIdType(cursor.getInt(5));
                    Obj.setIdGroup(cursor.getInt(6));
                    Obj.setTel(cursor.getString(7));
                    Obj.setMobile(cursor.getString(8));
                    Obj.setMail(cursor.getString(9));
                    Obj.setOpenInvoice(cursor.getInt(10));
                    Obj.setDebt(cursor.getLong(1));
                    Obj.setAvgDate(cursor.getInt(12));
                    Obj.setRate(cursor.getInt(13));
                    Obj.setDescription(cursor.getString(14));
                    Obj.setAddress(cursor.getString(15));
                    Obj.setX(cursor.getString(16));
                    Obj.setY(cursor.getString(17));
                    lst.add(Obj);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }

    @Override
    public Customer find(int id) {
        Customer Obj = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select " +
                col_id			+ "," +
                col_Code		+ "," +
                col_Name		+ "," +
                col_F_Name		+ "," +
                col_L_name		+ "," +
                col_IdType		+ "," +
                col_IdGroup		+ "," +
                col_tel			+ "," +
                col_mob			+ "," +
                col_mail		+ "," +
                col_OpenInvoice	+ "," +
                col_Debt		+ "," +
                col_AvgDate		+ "," +
                col_rate		+ "," +
                col_Description	+ "," +
                col_Address		+ "," +
                col_x			+ "," +
                col_y 			+ "," +
                col_rate + " from " +  TableName ;
            Cursor cursor = sqLiteDatabase.rawQuery(query + " where " + col_id + " = ?  " , new String[] { Integer.toString(id)  });
            if (cursor.moveToFirst()) {
                do {
                    Obj = new Customer();
                    Obj.setId (cursor.getInt(0));
                    Obj.setCode(cursor.getString(1));
                    Obj.setName(cursor.getString(2));
                    Obj.setFName(cursor.getString(3));
                    Obj.setLName(cursor.getString(4));
                    Obj.setIdType(cursor.getInt(5));
                    Obj.setIdGroup(cursor.getInt(6));
                    Obj.setTel(cursor.getString(7));
                    Obj.setMobile(cursor.getString(8));
                    Obj.setMail(cursor.getString(9));
                    Obj.setOpenInvoice(cursor.getInt(10));
                    Obj.setDebt(cursor.getLong(1));
                    Obj.setAvgDate(cursor.getInt(12));
                    Obj.setRate(cursor.getInt(13));
                    Obj.setDescription(cursor.getString(14));
                    Obj.setAddress(cursor.getString(15));
                    Obj.setX(cursor.getString(16));
                    Obj.setY(cursor.getString(17));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Obj = null;
        }
        return Obj;
    }

    @Override
    public boolean update(Customer obj) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(col_id, obj.getId());
            cv.put(col_Code, obj.getCode());
            cv.put(col_Name, obj.getName());
            cv.put(col_F_Name, obj.getFName());
            cv.put(col_L_name, obj.getLName());
            cv.put(col_IdType, obj.getIdType());
            cv.put(col_IdGroup, obj.getIdGroup());
            cv.put(col_tel, obj.getTel());
            cv.put(col_mob, obj.getMobile());
            cv.put(col_mail, obj.getMail());
            cv.put(col_OpenInvoice, obj.getOpenInvoice());
            cv.put(col_Debt, obj.getDebt());
            cv.put(col_AvgDate, obj.getAvgDate());
            cv.put(col_rate, obj.getRate());
            cv.put(col_Description, obj.getDescription());
            cv.put(col_Address, obj.getAddress());
            cv.put(col_x, obj.getX());
            cv.put(col_y, obj.getY());
            result = db.update(TableName, cv, col_id + " = ?", new String[]{ String.valueOf(obj.getId()) }) > 0;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
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
