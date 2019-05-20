package com.example.movierecommendation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

public class Sb_manage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_manage);
        Button button_change_information = findViewById(R.id.change_information);
        Button button_change_theme = findViewById(R.id.change_theme);
        Button button_account_manage = findViewById(R.id.account_manage);
        Button button_quit_account = findViewById(R.id.quit_account);
        //设置中四个按钮的事件
        button_change_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_change_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_account_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Sb_manage.this,StartActivity.class);
                startActivity(intent);

            }
        });

        button_quit_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Sb_manage.this,StartActivity.class);
                //startActivity(intent);
               //onDestroy();
            }
        });

    }
}
