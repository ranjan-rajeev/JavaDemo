package com.horizonlabs.demoprojectjava.view.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Rajeev Ranjan -  ABPB on 19-08-2019.
 */
public class BaseActivity extends AppCompatActivity {
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void showDialog(Context context) {
        // dialog = ProgressDialog.show(context, "", "Loading...", true);
        if (context != null)
            showDialog(context, "Loading...");
    }

    protected void showDialog(Context context, String msg) {
        if (dialog == null)
            dialog = ProgressDialog.show(context, "", msg, true);
        else {
            if (!dialog.isShowing())
                dialog = ProgressDialog.show(context, "", msg, true);
        }
    }

    protected void cancelDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
