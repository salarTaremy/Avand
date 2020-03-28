package com.smartVisitor.avand.classes.Update;

import android.os.Handler;
import android.view.View;

import com.smartVisitor.avand.database.IDbHelper;

import java.util.List;

public class      SaveObject<T> {
    final List<T> TList;
    final IDbHelper db;
    final UpdateDetail U;
    int i  = 0;
    private boolean DeleteCurrentData = true;
    public SaveObject(List<T> lst, IDbHelper db, final UpdateDetail u) {

        this.TList = lst;
        this.db = db;
        this.U = u;
    }
    public void Save(boolean DeleteCurrentData) {
        this.DeleteCurrentData = DeleteCurrentData;
       DoSave();
    }
    public void Save() {
        DoSave();
    }

    private void DoSave() {
        final Handler hdlr = new Handler();
        Thread T = new Thread (new Runnable() {
            public void run() {
                if (DeleteCurrentData == true){
                    db.delete();
                }
                i = 0;
                final int cnt = TList.size();
                U.Prc.setMax(cnt);
                for ( final T item: TList) {
                    hdlr.post(new Runnable() {
                        public void run() {
                            i +=1 ;
                            U.Prc.setProgress(i);
                            //U.Tv_Percentage.setVisibility(View.VISIBLE);
                            U.Tv_Percentage.setText(String.valueOf(i) + "/ " +   String.valueOf(cnt));
                            if (i==cnt){
                                U.Tv_Percentage.setVisibility(View.INVISIBLE);
                                U.ImgUpdateOk.setVisibility(View.VISIBLE);
                                U.Prc.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                    db.create(item);
                }
            }
        });
        T.setName(db.getClass().getName().toString());
        T.start();
    }
}