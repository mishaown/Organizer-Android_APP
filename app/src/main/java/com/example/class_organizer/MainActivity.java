package com.example.class_organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Animation top;
    Animation bottom;
    ImageView logo;
    TextView app_name, projectname;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        top = AnimationUtils.loadAnimation(this,R.anim.top_part);
        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_part);

        logo = findViewById(R.id.logo);
        app_name = findViewById(R.id.app_name);
        projectname = findViewById(R.id.projectname);

        logo.setAnimation(top);
        app_name.setAnimation(bottom);
        projectname.setAnimation(bottom);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, DashBoard.class));
                finish();
            }
        };
        handler.postDelayed(runnable, 3000);
    }
}