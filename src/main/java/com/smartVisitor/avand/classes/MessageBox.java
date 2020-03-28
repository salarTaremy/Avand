package com.smartVisitor.avand.classes;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.smartVisitor.avand.R;

public class MessageBox  {
    private Context context;
    private View dialogView;
    private AlertDialog alertDialog;
    String Title,Message;
    private  OnOkClickListener OkListener = new OnOkClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private  OnNoClickListener NoListener = new OnNoClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private  OnYesClickListener YesListener = new OnYesClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    public MessageBox(Context context) {
        this.context = context;
    }
    public interface OnOkClickListener {
        void onClick(View v);
    }
    public interface OnYesClickListener {
        void onClick(View v);
    }
    public interface OnNoClickListener {
        void onClick(View v);
    }
    public void ShowInfo(String Title , String Message, final OnOkClickListener listener) {
        this.Title = Title;
        this.Message = Message;
        this.OkListener = listener;
        ShowInfo();
    }
    public void ShowInfo(String Title , String Message) {
        this.Title = Title;
        this.Message = Message;
        ShowInfo();
    }
    private void ShowInfo() {
        this.dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_info_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setCancelable(false).setView(dialogView);
        alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((TextView) dialogView.findViewById(R.id.Tv_Msg_Inf_Title)).setText(Title);
        ((TextView) dialogView.findViewById(R.id.Tv_Msg_Inf_Message)).setText(Message);
        dialogView.findViewById(R.id.BtnConfirmInf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkListener.onClick(v);
                alertDialog.dismiss();
            }
        });
    }
    public void ShowOk(String Title , String Message, final OnOkClickListener listener) {
        this.Title = Title;
        this.Message = Message;
        this.OkListener = listener;
        ShowOk();
    }
    public void ShowOk(String Title , String Message) {
        this.Title = Title;
        this.Message = Message;
        ShowOk();
    }
    private void ShowOk() {
        this.dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_success_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setCancelable(false).setView(dialogView);
        alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((TextView) dialogView.findViewById(R.id.Tv_Msg_Succ_Title)).setText(Title);
        ((TextView) dialogView.findViewById(R.id.Tv_Msg_Succ_Message)).setText(Message);
        dialogView.findViewById(R.id.BtnConfirmSucc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkListener.onClick(v);
                alertDialog.dismiss();
            }
        });
    }
    public void ShowError(String Title , String Message, final OnOkClickListener listener) {
        this.Title = Title;
        this.Message = Message;
        this.OkListener = listener;
        ShowError();
    }
    public void ShowError(String Title , String Message) {
        this.Title = Title;
        this.Message = Message;
        ShowError();
    }
    private  void ShowError(){
        this.dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_warning_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setCancelable(false).setView(dialogView);
        alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((TextView) dialogView.findViewById(R.id.Tv_Msg_Warning_Title)).setText(Title);
        ((TextView) dialogView.findViewById(R.id.Tv_Msg_Warning_Message)).setText(Message);
        dialogView.findViewById(R.id.BtnConfirmWarning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkListener.onClick(v);
                alertDialog.dismiss();
            }
        });
    }
    public void ShowQuestion(String Title , String Message, final OnYesClickListener YesListener) {
        this.Title = Title;
        this.Message = Message;
        this.YesListener = YesListener;
        ShowQuestion();
    }
    public void ShowQuestion(String Title , String Message,final OnYesClickListener YesListener,final OnNoClickListener NoListener) {
        this.Title = Title;
        this.Message = Message;
        this.YesListener = YesListener;
        this.NoListener = NoListener;
        ShowQuestion();
    }

    private void ShowQuestion() {
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_confirm_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setCancelable(false).setView(dialogView);
        final AlertDialog alertDialog = builder.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((TextView) dialogView.findViewById(R.id.Tv_Msg_Qestion_Title)).setText(Title);
        ((TextView) dialogView.findViewById(R.id.Tv_Msg_Qestion_Message)).setText(Message);
        dialogView.findViewById(R.id.BtnCancellQestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoListener.onClick(v);
                alertDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.BtnConfirmQestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YesListener.onClick(v);
                alertDialog.dismiss();
            }
        });
    }


}
