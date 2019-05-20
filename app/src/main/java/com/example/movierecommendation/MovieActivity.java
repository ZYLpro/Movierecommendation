package com.example.movierecommendation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MovieActivity extends AppCompatActivity {

    private TextView moviename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_single);

        moviename=findViewById(R.id.moviename);
        //
        // 获取意图对象
        Intent intent = getIntent();
        //获取传递的值
        String name = intent.getStringExtra("movieid");
        moviename.setText(name);

    }



}
