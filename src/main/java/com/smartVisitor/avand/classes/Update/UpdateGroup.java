package com.smartVisitor.avand.classes.Update;


import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.security.PublicKey;

import vn.luongvo.widget.iosswitchview.SwitchView;

public class UpdateGroup {


    public Integer id;
    public String name;
    public  String description;
    public boolean IsChecked = false;

    public TextView Tv_Percentage;
    public ProgressBar Prc;
    public SwitchView Sw ;
    public ImageView ImgUpdateOk ;
    public ImageView ImgUpdateErr ;


    public UpdateGroup(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


}