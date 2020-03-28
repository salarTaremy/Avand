package com.smartVisitor.avand.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.smartVisitor.avand.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDbHelper extends DbHelper_Base  implements IDbHelper<Product> {

    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    private static String TableName = "Product";

    private static String col_id = "id";
    private static String col_code = "code";
    private static String col_name = "name";
    private static String col_idBrand = "idBrand";
    private static String col_idGroup = "idGroup";
//    private static String col_price = "price";
//    private static String col_consumerPrice = "consumerPrice";
    private static String col_count = "count";
    private static String col_countInBox = "countInBox";
    private static String col_weight = "weight";
//    private static String col_duration = "duration";
    private static String col_detail = "detail";
    private static String col_rate = "rate";

    public ProductDbHelper(Context context) {
        super(context, dbName, null, dbVersion,TableName);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
                db.execSQL("create table  IF NOT EXISTS  " + TableName + "(" +
                    col_id  + " integer primary key , " +
                    col_code + " text, " +
                    col_name + " text, " +
                    col_idBrand + " integer, " +
                    col_idGroup + " integer, " +
                    col_count + " integer, " +
                    col_countInBox + " integer, " +
                    col_weight + " integer, " +
                    col_detail + " text, " +
                    col_rate + " integer " +
                ")"
        );
    }
    public boolean create(final Product product) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(col_id, product.getId());
            contentValues.put(col_code , product.getCode());
            contentValues.put(col_name , product.getName());
            contentValues.put(col_idBrand , product.getIdBrand());
            contentValues.put(col_idGroup , product.getIdGroup());
            contentValues.put(col_count , product.getCount());
            contentValues.put(col_countInBox , product.getCountInBox());
            contentValues.put(col_weight , product.getWeight());
            contentValues.put(col_detail , product.getDetail());
            contentValues.put(col_rate , product.getRate());
            db.insert(TableName, null, contentValues) ;
            //db.close();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    public List<Product> findAll() {
        List<Product> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = "select " +
                col_id  + "," +
                col_code + "," +
                col_name + "," +
                col_idBrand + "," +
                col_idGroup + "," +
                col_count + "," +
                col_countInBox + "," +
                col_weight + "," +
                col_detail + "," +
                col_rate + " from " +  TableName ;

            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                lst = new ArrayList<Product>();
                do {
                    Product prd = new Product();
                    prd.setId(cursor.getInt(0));
                    prd.setCode(cursor.getString(1));
                    prd.setName(cursor.getString(2));
                    prd.setIdBrand(cursor.getInt(3));
                    prd.setIdGroup(cursor.getInt(4));
                    prd.setCount(cursor.getInt(5));
                    prd.setCountInBox(cursor.getInt(6));
                    prd.setWeight(cursor.getInt(7));
                    prd.setDetail(cursor.getString(8));
                    prd.setRate(cursor.getInt(9));
                    lst.add(prd);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
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
    public Product find(int id) {
        Product obj = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select " +
                col_id  + "," +
                col_code + "," +
                col_name + "," +
                col_idBrand + "," +
                col_idGroup + "," +
                col_count + "," +
                col_countInBox + "," +
                col_weight + "," +
                col_detail + "," +
                col_rate + " from " +  TableName ;
            Cursor cursor = sqLiteDatabase.rawQuery(query + " where " + col_id + " = ?  " , new String[] { Integer.toString(id)  });
            if (cursor.moveToFirst()) {
                do {
                    obj  = new Product();
                    obj.setId(cursor.getInt(0));
                    obj.setCode(cursor.getString(1));
                    obj.setName(cursor.getString(2));
                    obj.setIdBrand(cursor.getInt(3));
                    obj.setIdGroup(cursor.getInt(4));
                    obj.setCount(cursor.getInt(5));
                    obj.setCountInBox(cursor.getInt(6));
                    obj.setWeight(cursor.getInt(7));
                    obj.setDetail(cursor.getString(8));
                    obj.setRate(cursor.getInt(9));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }
    public List<Product> search(String keyword) {
        List<Product> lst = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String query = "select " +
                col_id  + "," +
                col_code + "," +
                col_name + "," +
                col_idBrand + "," +
                col_idGroup + "," +
                col_count + "," +
                col_countInBox + "," +
                col_weight + "," +
                col_detail + "," +
                col_rate + " from " +  TableName ;
            Cursor cursor = sqLiteDatabase.rawQuery(query + " where " + col_name + " like ? or " + col_code + " like ?", new String[] { "%" + keyword + "%" , "%" + keyword + "%"  });
            if (cursor.moveToFirst()) {
                lst = new ArrayList<Product>();
                do {
                    Product prd = new Product();
                    prd.setId(cursor.getInt(0));
                    prd.setCode(cursor.getString(1));
                    prd.setName(cursor.getString(2));
                    prd.setIdBrand(cursor.getInt(3));
                    prd.setIdGroup(cursor.getInt(4));
                    prd.setCount(cursor.getInt(5));
                    prd.setCountInBox(cursor.getInt(6));
                    prd.setWeight(cursor.getInt(7));
                    prd.setDetail(cursor.getString(8));
                    prd.setRate(cursor.getInt(9));
                    lst.add(prd);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
        }
        return lst;
    }
    public boolean update(Product product) {
        boolean result = true;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(col_id, product.getId());
            contentValues.put(col_code , product.getCode());
            contentValues.put(col_name , product.getName());
            contentValues.put(col_idBrand , product.getIdBrand());
            contentValues.put(col_idGroup , product.getIdGroup());
            contentValues.put(col_count , product.getCount());
            contentValues.put(col_countInBox , product.getCountInBox());
            contentValues.put(col_weight , product.getWeight());
            contentValues.put(col_detail , product.getDetail());
            contentValues.put(col_rate , product.getRate());
            result = db.update(TableName, contentValues, col_id + " = ?", new String[]{ String.valueOf(product.getId()) }) > 0;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
