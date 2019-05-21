package sidebar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.movierecommendation.R;

public class Sb_manage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_manage);
        Button button_change_information = findViewById(R.id.change_information);
        Button button_change_theme = findViewById(R.id.change_theme);
        Button button_account_manage = findViewById(R.id.account_manage);
        //设置中四个按钮的事件
        button_change_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sb_manage.this,Sb_manage_changeavatar.class);
                startActivity(i);
            }
        });

        button_change_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Sb_manage.this,Sb_manage_changetheme.class);
                startActivity(i);
            }
        });

        button_account_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });



    }
}
