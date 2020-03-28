package com.smartVisitor.avand.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.smartVisitor.avand.dataTransferObjects.PriceListDetailDTO;
import com.smartVisitor.avand.dataTransferObjects.ProductDTO;
import com.smartVisitor.avand.entities.Customer;
import com.smartVisitor.avand.entities.PriceList;
import com.smartVisitor.avand.entities.PriceListDetail;

import java.util.ArrayList;
import java.util.List;

public class GeneralDbHelper extends SQLiteOpenHelper {
    private Context context;
    private static String dbName = "SmartVisitorDB";
    private static int dbVersion = 1;
    //===========================================================================
    final String Tbl_PriceList = "PriceList";
    final String Tbl_PriceListDetail = "PriceListDetail";
    final String Tbl_LinkPriceListCustomer = "LinkPriceListCustomer";
    final String Tbl_Product = "Product";
    final String Tbl_Inventory = "Inventory";
    final String Tbl_Customer = "Customer";
    final String Tbl_CustomerGroup = "CustomerGroup";
    final String Tbl_CustomerType = "CustomerType";

    //===========================================================================
    public GeneralDbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public PriceList PriceListGetByCustomer(Customer customer) {
        PriceList priceList  = new PriceList() ;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query =  "select p.ID,P.BeginDate ,P.EndDate,P.Description\n" +
                "from "+ Tbl_PriceList+" p\n" +
                "inner join "+ Tbl_LinkPriceListCustomer+" Lnk on Lnk.ID_PriceList  =  p.ID\n" +
                "where Lnk.ID_CustomerType = "+ customer.idType +" and Lnk.ID_CustomerGroup = "+customer.idGroup+"  \n" +
                "LIMIT 1";
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                priceList = new PriceList();
                do {
                    priceList.ID=cr.getInt(0);
                    priceList.BeginDate=cr.getInt(1);
                    priceList.EndDate=cr.getInt(2);
                    priceList.Description=cr.getString(3);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            priceList = null;
            Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return priceList;
    }
    public PriceListDetail PriceListDetailGetByProductIDAndPriceID( int ID_priceList , int  ID_Product ) {
        PriceListDetail Prd  = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query =  "select ID ,ID_PriceList,ID_Product,Price,ConsumerPrice,Tax,Duration \n" +
                            "from "+ Tbl_PriceListDetail+"\n" +
                            "WHERE ID_Product ="+ ID_Product +"\n" +
                            "And ID_PriceList = " +ID_priceList ;
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                Prd = new PriceListDetail();
                do {
                    Prd.ID=cr.getInt(0);
                    Prd.ID_PriceList=cr.getInt(1);
                    Prd.ID_Product=cr.getInt(2);
                    Prd.Price=cr.getInt(3);
                    Prd.ConsumerPrice=cr.getInt(4);
                    Prd.Tax=cr.getInt(5);
                    Prd.Duration=cr.getInt(6);

                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            Prd = null;
            Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return Prd;
    }
    public List<PriceListDetailDTO> PriceListDetailGetByProductID( int  ID_Product) {
        List<PriceListDetailDTO> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query =  "SELECT p.Description ,pd.Price,pd.ConsumerPrice,pd.Duration\n" +
                            "FROM "+Tbl_PriceList+" p \n" +
                            "JOIN "+Tbl_PriceListDetail+" pd  ON pd.ID_PriceList = p.ID\n" +
                            "WHERE pd.ID_Product = " + Integer.toString(ID_Product);
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<PriceListDetailDTO>();
                do {
                    PriceListDetailDTO Obj = new PriceListDetailDTO();
                    Obj.Description=cr.getString(0);
                    Obj.Price=cr.getInt(1);
                    Obj.ConsumerPrice=cr.getInt(2);
                    Obj.Duration=cr.getInt(3);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
            Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return lst;
    }
    public List<ProductDTO> ProductGetAll() {
        List<ProductDTO> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query =  "SELECT p.Id , P.Code,p.Name,p.IdBrand , p.IdGroup,P.CountInBox,P.Weight,p.Detail\n" +
                            ", SUM(i.Quantity) Inventory  \n" +
                            ",Count(i.ID) WarehouseCount\n" +
                            "FROM "+Tbl_Product+" P\n" +
                            "JOIN "+Tbl_Inventory+" I ON  I.ID_Product = P.Id\n" +
                            "GROUP BY p.Id , P.Code,p.Name,p.IdBrand , p.IdGroup,P.CountInBox,P.Weight,p.Detail";
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<ProductDTO>();
                do {
                    ProductDTO Obj = new ProductDTO();
                    Obj.id=cr.getInt(0);
                    Obj.code=cr.getString(1);
                    Obj.name=cr.getString(2);
                    Obj.idBrand=cr.getInt(3);
                    Obj.idGroup=cr.getInt(4);
                    Obj.countInBox=cr.getInt(5);
                    Obj.weight=cr.getInt(6);
                    Obj.detail=cr.getString(7);
                    Obj.Inventory=cr.getInt(8);
                    Obj.WarehouseCount=cr.getInt(9);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
            Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return lst;
    }
    public List<ProductDTO> ProductGetAll(PriceList priceList) {
        List<ProductDTO> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query =  "SELECT p.Id , P.Code,p.Name,p.IdBrand , p.IdGroup,P.CountInBox,P.Weight,p.Detail \n" +
                ", SUM(i.Quantity) Inventory  \n" +
                ",Count(i.ID) WarehouseCount\n" +
                ",Pd.Price,Pd.ConsumerPrice,Pd.Tax,Pd.Duration \n" +
                "FROM "+Tbl_Product+" P\n" +
                "JOIN "+Tbl_Inventory+" I ON  I.ID_Product = P.Id\n" +
                "JOIN "+Tbl_PriceListDetail+" Pd on Pd.ID_Product = p.id\n"+
                "JOIN "+Tbl_PriceList +" Pr on Pr.ID = Pd.ID_PriceList\n"+
                "Where 1=1 \n"+
                "And Pr.ID = "+ priceList.ID+" \n" +
                "GROUP BY p.Id , P.Code,p.Name,p.IdBrand , p.IdGroup,P.CountInBox,P.Weight,p.Detail,Pd.Price,Pd.ConsumerPrice,Pd.Tax,Pd.Duration ";
            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<ProductDTO>();
                do {
                    ProductDTO Obj = new ProductDTO();
                    Obj.id=cr.getInt(0);
                    Obj.code=cr.getString(1);
                    Obj.name=cr.getString(2);
                    Obj.idBrand=cr.getInt(3);
                    Obj.idGroup=cr.getInt(4);
                    Obj.countInBox=cr.getInt(5);
                    Obj.weight=cr.getInt(6);
                    Obj.detail=cr.getString(7);
                    Obj.Inventory=cr.getInt(8);
                    Obj.WarehouseCount=cr.getInt(9);
                    Obj.priceListDetail.Price =cr.getInt(10);
                    Obj.priceListDetail.ConsumerPrice=cr.getInt(11);
                    Obj.priceListDetail.Tax=cr.getInt(12);
                    Obj.priceListDetail.Duration=cr.getInt(13);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
            Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return lst;
    }
    public List<ProductDTO> ProductGetByLike(String keyword) {
        List<ProductDTO> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query =  "SELECT p.Id , P.Code,p.Name,p.IdBrand , p.IdGroup,P.CountInBox,P.Weight,p.Detail\n" +
                ", SUM(i.Quantity) Inventory  \n" +
                ",Count(i.ID) WarehouseCount\n" +
                "FROM "+Tbl_Product+" P\n" +
                "JOIN "+Tbl_Inventory+" I ON  I.ID_Product = P.Id\n" +
                "WHERE p.Name LIKE '%"+keyword+"%'  OR  P.Code LIKE '%"+ keyword+"%' \n" +
                "GROUP BY p.Id , P.Code,p.Name,p.IdBrand , p.IdGroup,P.CountInBox,P.Weight,p.Detail";

            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<ProductDTO>();
                do {
                    ProductDTO Obj = new ProductDTO();
                    Obj.id=cr.getInt(0);
                    Obj.code=cr.getString(1);
                    Obj.name=cr.getString(2);
                    Obj.idBrand=cr.getInt(3);
                    Obj.idGroup=cr.getInt(4);
                    Obj.countInBox=cr.getInt(5);
                    Obj.weight=cr.getInt(6);
                    Obj.detail=cr.getString(7);
                    Obj.Inventory=cr.getInt(8);
                    Obj.WarehouseCount=cr.getInt(9);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
            Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return lst;
    }
    public List<ProductDTO> ProductGetByLike(String keyword ,PriceList priceList) {
        List<ProductDTO> lst = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query =  "SELECT p.Id , P.Code,p.Name,p.IdBrand , p.IdGroup,P.CountInBox,P.Weight,p.Detail  \n" +
                ", SUM(i.Quantity) Inventory  \n" +
                ",Count(i.ID) WarehouseCount\n" +
                ",Pd.Price,Pd.ConsumerPrice,Pd.Tax,Pd.Duration \n" +
                "FROM "+Tbl_Product+" P\n" +
                "JOIN "+Tbl_Inventory+" I ON  I.ID_Product = P.Id\n" +
                "JOIN "+Tbl_PriceListDetail+" Pd on Pd.ID_Product = p.id\n"+
                "JOIN "+Tbl_PriceList +" Pr on Pr.ID = Pd.ID_PriceList\n"+
                "WHERE p.Name LIKE '%"+keyword+"%'  OR  P.Code LIKE '%"+ keyword+"%' \n" +
                "And Pr.ID = "+ priceList.ID+" \n" +
                "GROUP BY p.Id , P.Code,p.Name,p.IdBrand , p.IdGroup,P.CountInBox,P.Weight,p.Detail,Pd.Price,Pd.ConsumerPrice,Pd.Tax,Pd.Duration  ";

            Cursor cr = db.rawQuery(query, null);
            if (cr.moveToFirst()) {
                lst = new ArrayList<ProductDTO>();
                do {
                    ProductDTO Obj = new ProductDTO();
                    Obj.id=cr.getInt(0);
                    Obj.code=cr.getString(1);
                    Obj.name=cr.getString(2);
                    Obj.idBrand=cr.getInt(3);
                    Obj.idGroup=cr.getInt(4);
                    Obj.countInBox=cr.getInt(5);
                    Obj.weight=cr.getInt(6);
                    Obj.detail=cr.getString(7);
                    Obj.Inventory=cr.getInt(8);
                    Obj.WarehouseCount=cr.getInt(9);
                    Obj.priceListDetail.Price =cr.getInt(10);
                    Obj.priceListDetail.ConsumerPrice=cr.getInt(11);
                    Obj.priceListDetail.Tax=cr.getInt(12);
                    Obj.priceListDetail.Duration=cr.getInt(13);
                    lst.add(Obj);
                } while (cr.moveToNext());
            }
        } catch (Exception e) {
            lst = null;
            Toast.makeText(this.context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return lst;
    }


}
