package com.yzhg.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.yzhg.ydialog.base.BaseDialog;
import com.yzhg.ydialog.base.CommonDialog;
import com.yzhg.ydialog.base.ViewConvertListener;
import com.yzhg.ydialog.base.ViewHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.butOpenDialog).setOnClickListener(v -> {
            CommonDialog commonDialog = CommonDialog.newInstance().setLayoutId(R.layout.common_layout);
            commonDialog.setConvertListener((holder, dialog) -> holder.getView(R.id.butSureDialog).setOnClickListener(v1 -> {
                commonDialog.dismissDialog();
            }));
            commonDialog.showDialog(getSupportFragmentManager());
        });
    }
}
