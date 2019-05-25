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

public class StartActivity extends Activity {

    //private MySqliteHelper helper;//数据库辅助操作类
    public SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



//        Log.i("test", "------进行查询-------");
//        Cursor cursor = db.rawQuery("select * from movies where movieid=?", new String[]{"1"});
//        String name = null;
//        if(cursor.moveToFirst()){
//            name = cursor.getString(cursor.getColumnIndex("moviename"));
//        }
//        //这是一个TextView，把得到的数据库中的name显示出来.
//        editText.setText(name);
//        cursor.close();
//

//        helper = DbManager.getIntance(this);
        Button button = (Button)findViewById(R.id.Login);
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
                if(login(username,password)){
                    //用完数据库一定要关闭
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
    }

//    public void createDB(View v){
//        //getWritableDatabase()创建或打开数据库，如果不存在则创建，否则直接打开，默认情况下getReadable()和getWritable()都表示创建可读可写
//        SQLiteDatabase db=helper.getWritableDatabase();
//    }

    public boolean login(String username,String password) {
//        SQLiteDatabase db = helper.getWritableDatabase();
        // Cursor cursor = db.rawQuery("select * from movies where movieid=?", new String[]{"1"});


        String sql = "select * from loginusers where username=? and password=?";
        Cursor cursor = DbManager.selectDataBySQL(db,sql,new String[]{username,password});
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
