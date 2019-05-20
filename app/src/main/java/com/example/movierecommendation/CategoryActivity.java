package com.example.movierecommendation;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity{

    private TextView cat;

    private TextView movie1,movie2,movie3,movie4;//先写4个

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_single);

        //进行绑定
        movie1=findViewById(R.id.movie1);
        movie2=findViewById(R.id.movie2);
        movie3=findViewById(R.id.movie3);
        movie4=findViewById(R.id.movie4);

        // 获取意图对象
        Intent intent = getIntent();
        //获取传递的值
        String category = intent.getStringExtra("category");
        cat=findViewById(R.id.cat);
        cat.setText(category);


        //进行单个电影的选择
        movie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(CategoryActivity.this, MovieActivity.class);
                intent2.putExtra("movieid","id");//到时候再替换
                startActivity(intent2);
            }
        });


    }
}
