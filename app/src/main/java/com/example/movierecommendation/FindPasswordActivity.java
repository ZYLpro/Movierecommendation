package com.example.movierecommendation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bean.Loginuser;
import com.example.Database.Copy;
import com.example.utils.DbManager;

import java.util.List;

import global_variable.Myapplication;

public class FindPasswordActivity extends Activity {

    public SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);

        Button button = (Button)findViewById(R.id.LogByEmail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据库
                Log.i("test", "------创建copy类,得到数据库--------");
                Copy copy=new Copy();
                db=copy.openDatabase(getApplicationContext());

                EditText editUser=(EditText)findViewById(R.id.username);
                EditText editEmail=(EditText)findViewById(R.id.email) ;
                String username=editUser.getText().toString();
                String email=editEmail.getText().toString();
                if(login(username,email)){
                    //用完数据库一定要关闭
                    Myapplication myapp = (Myapplication) FindPasswordActivity.this.getApplication();
                    myapp.setname(username);
                    db.close();
                    Intent intent = new Intent(FindPasswordActivity.this,MainActivity.class);
                    startActivity(intent);
                    FindPasswordActivity.this.finish();
                }
                else {
                    Toast.makeText(FindPasswordActivity.this, "用户名或邮箱错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button backButton = (Button)findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindPasswordActivity.this,StartActivity.class);
                startActivity(intent);

            }
        });
    }
    public boolean login(String username,String email) {
        String sql = "select * from loginusers where username=? and email=?";
        Cursor cursor = DbManager.selectDataBySQL(db,sql,new String[]{username,email});
        List<Loginuser> list=DbManager.cursorToList(cursor);
        for(Loginuser l:list){
            Log.i("tag", l.toString());
        }
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        db.close();
        return false;
    }



}
