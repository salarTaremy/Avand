package com.smartVisitor.avand.Orm;

public class Field {
    public static Object DbType;
    public String Name  ;
    public  Types.DbType Type ;
    public boolean Key = false;
    public boolean AUTOINCREMENT = false;

    public Field(String name, Types.DbType type, boolean key,boolean AUTOINCREMENT ) {
        this.Name = name;
        this.Type = type;
        this.Key = key;
        this.AUTOINCREMENT = AUTOINCREMENT;
    }
    public Field(String name, Types.DbType type, boolean key) {
        this.Name = name;
        this.Type = type;
        this.Key = key;
    }
    public Field(String name, Types.DbType type) {
        this.Name = name;
        this.Type = type;
    }
}


