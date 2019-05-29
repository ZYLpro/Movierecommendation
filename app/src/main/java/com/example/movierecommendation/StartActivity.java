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

public class StartActivity extends Activity {

    public SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button button = (Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据库
                Log.i("test", "------创建copy类,得到数据库--------");
                Copy copy=new Copy();
                db=copy.openDatabase(getApplicationContext());

                EditText editUser=(EditText)findViewById(R.id.username);
                EditText editPass=(EditText)findViewById(R.id.password) ;
                String username=editUser.getText().toString();
                String password=editPass.getText().toString();
                if(login(username,password)!=0){
                    //用完数据库一定要关闭
                    Myapplication myapp = (Myapplication) StartActivity.this.getApplication();
                    myapp.setname(username);
                    myapp.setUserid(login(username,password));
                    Log.i("tag", "userid="+String.valueOf(myapp.getUserid()));
                    db.close();

                    Intent intent = new Intent(StartActivity.this,MainActivity.class);
                    startActivity(intent);
                    StartActivity.this.finish();
                }
                else {
                    Toast.makeText(StartActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button Signupbutton = (Button)findViewById(R.id.register);
        Signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,RegActivity.class);
                startActivity(intent);

            }
        });
        Button forgetButton = (Button)findViewById(R.id.forget);
        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,FindPasswordActivity.class);
                startActivity(intent);

            }
        });
    }
    public int login(String username,String password) {
        String sql = "select * from loginusers where username=? and password=?";
        Cursor cursor = DbManager.selectDataBySQL(db,sql,new String[]{username,password});
        List<Loginuser> list=DbManager.cursorToList(cursor);
        for(Loginuser l:list){
            Log.i("tag", l.toString());
        }
        if (cursor.moveToFirst()) {
            int logintime=cursor.getInt(cursor.getColumnIndex("logintime"));
            int userid=cursor.getInt(cursor.getColumnIndex("userid"));
            String update = "update loginusers set logintime="  + String.valueOf(logintime+1)+" where username=\'"+username+"\'";
            DbManager.execSQL(db,update);
            Log.i("tag",String.valueOf(userid));
            return userid;
        }
        return 0;
    }


}
