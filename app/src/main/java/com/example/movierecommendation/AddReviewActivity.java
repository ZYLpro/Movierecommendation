package com.example.movierecommendation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.Database.Copy;
import com.example.utils.DbManager;



import java.text.SimpleDateFormat;
import java.util.Date;

import global_variable.Myapplication;
import sidebar.Sb_manage_changeavatar;

public class AddReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText editText;
    private Button button;

    private float rating;
    private String content;

    private int userid;
    private int movieid;
    private String oldrating;
    private String time;
    private SQLiteDatabase db;
    private Myapplication myapp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addreview);

        myapp = (Myapplication) AddReviewActivity.this.getApplication();

        //获取id
        // 获取意图对象
        final Intent intent = getIntent();
        Bundle b=intent.getExtras();
        //获取传递的值

        movieid=b.getInt("movieid");
        oldrating=b.getString("rating");

        //初始化
        ratingBar=findViewById(R.id.ratingBar);
        editText=findViewById(R.id.reviewcontent);
        button=findViewById(R.id.submit);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(AddReviewActivity.this,"rating"+String.valueOf(v),Toast.LENGTH_SHORT).show();
                rating=v;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content=editText.getText().toString();
                //写数据库
                Copy copy=new Copy();
                db=copy.openDatabase(getApplicationContext());
//

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                time = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳


                String sql = "select * from reviews";
                Cursor cursor = DbManager.selectDataBySQL(db, sql, null);
                int id=1000;
                while (cursor.moveToNext()){
                    id=cursor.getInt(cursor.getColumnIndex("id"));
                }
                Log.i("test", "最后一个review的id"+id);
                String sql2="select * from loginusers where username=?";
                Cursor cursor2 = DbManager.selectDataBySQL(db, sql2, new String[]{myapp.getname()});
                while (cursor2.moveToNext()){
                    userid=cursor2.getInt(cursor2.getColumnIndex("userid"));
                }
                Log.i("tag", String.valueOf(userid));

                String insert = "insert into reviews values(" + String.valueOf(id+1) + "," + userid  +","+  movieid + ",\'" + content + "\',\'" + time+"\',\'"+myapp.getname()+"\')";
                db.execSQL(insert);


                //跳转回电影界面
                Intent intent1=new Intent(AddReviewActivity.this,MovieActivity.class);
                Bundle b=new Bundle();
                b.putInt("movieid",movieid);
                b.putString("rating",oldrating);
                intent1.putExtras(b);
                startActivity(intent1);



            }
        });
    }
}
