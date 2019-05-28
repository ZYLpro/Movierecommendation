package com.example.movierecommendation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartHabitChoose extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habit_choose);

        Button button = findViewById(R.id.OK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartHabitChoose.this,MainActivity.class);
                startActivity(intent);
                StartHabitChoose.this.finish();
            }
        });
    }
}
