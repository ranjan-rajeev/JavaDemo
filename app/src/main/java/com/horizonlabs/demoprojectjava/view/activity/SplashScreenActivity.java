package com.horizonlabs.demoprojectjava.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.horizonlabs.demoprojectjava.R;
import com.horizonlabs.demoprojectjava.utility.Constants;
import com.horizonlabs.demoprojectjava.view.base.BaseActivity;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;

public class SplashScreenActivity extends BaseActivity {

    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.content_splash_screen);
        ivLogo = findViewById(R.id.ivLogo);
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, Constants.SPLASH_TIME);
    }

}
