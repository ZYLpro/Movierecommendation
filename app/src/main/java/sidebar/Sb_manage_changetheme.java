package sidebar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.movierecommendation.R;


public class Sb_manage_changetheme extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_manage_theme);
        RadioButton rb_day = findViewById(R.id.day);
        RadioButton rb_night = findViewById(R.id.night);
        rb_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sb_manage_changetheme.this.finish();
            }
        });
        rb_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sb_manage_changetheme.this.finish();
            }
        });
    }
}
