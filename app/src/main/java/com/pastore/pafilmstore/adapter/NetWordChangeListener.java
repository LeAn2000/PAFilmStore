package com.pastore.pafilmstore.adapter;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.pastore.pafilmstore.R;
import com.pastore.pafilmstore.WelcomeActivity;

public class NetWordChangeListener extends  BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!Check.isConnectedToInternet(context)){

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.test, null);
            builder.setView(layout_dialog);

            AppCompatButton btnRety = layout_dialog.findViewById(R.id.btnRety);

            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            btnRety.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!Check.isConnectedToInternet(context))
                    {
                        dialog.dismiss();
                        onReceive(context, intent);
                    }
                    else
                    {
                        Intent intent = new Intent(context, WelcomeActivity.class);
                        ContextCompat.startActivity(context,intent,null);
                    }


                }
            });
        }
    }
}
