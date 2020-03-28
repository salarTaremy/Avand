package com.smartVisitor.avand.classes.Update;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UpdateDetail {
    public Integer id;
    public String name;
    public String description;

    public TextView Tv_Percentage;
    public ProgressBar Prc;
    public ImageView ImgUpdateOk;
    public ImageView ImgUpdateErr;


    public UpdateDetail(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
