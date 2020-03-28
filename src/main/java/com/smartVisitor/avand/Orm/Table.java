package com.smartVisitor.avand.Orm;

import java.util.List;

public class Table {
    public String dbName = "SmartVisitorDB";
    public int dbVersion ;
    public  String Name ;
    public List<Field> Fields;
    public Field Key =null;

    public Table(String dbName, int dbVersion, String name, List<Field> Fields) {
        this.dbName = dbName;
        this.dbVersion = dbVersion;
        this.Name = name;
        this.Fields = Fields;
        Init();
    }

    private void Init() {
        for (Field f: this.Fields) {
            if(f.Key){
                this.Key = f;
            }
        }
        if ( this.Key == null){
            throw new ArithmeticException("Key field not specified");
        }
    }

}
