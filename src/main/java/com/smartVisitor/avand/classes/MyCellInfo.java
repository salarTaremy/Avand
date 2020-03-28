package com.smartVisitor.avand.classes;

import java.io.Serializable;

public class MyCellInfo implements Serializable {

    public int mcc;         //Integer (Country code)
    public int  mnc;        //Integer (Network operator code)
    public int  cellid;     //Integer (Cell id)
    public int  lac;        //Integer (Area or location code)
    public String  OperatorName;
    public boolean  IsRegistered;

    public MyCellInfo(String OperatorName ,boolean IsRegistered,int mcc, int mnc, int cellid, int lac) {
        this.mcc = mcc;
        this.mnc = mnc;
        this.cellid = cellid;
        this.lac = lac;
        this.IsRegistered = IsRegistered;
    }

}
