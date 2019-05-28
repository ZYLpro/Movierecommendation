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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addreview);

        //获取id
        // 获取意图对象
        final Intent intent = getIntent();
        Bundle b=intent.getExtras();
        //获取传递的值

        movieid=b.getInt("movieid");
        userid=b.getInt("userid");
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

//                ContentValues cv=new ContentValues();
//                cv.put("userid",userid);
//                cv.put("movieid",movieid);
//                cv.put("rating",rating);
//                cv.put("time",time);
//                db.insert("ratingrecord",null,cv);

                //加评论
//                ContentValues cv2=new ContentValues();
//                cv2.put("id",0);
//                cv2.put("userid",userid);
//                cv2.put("movieid",movieid);
//                cv2.put("content",content);
//                cv2.put("time",time);
//                db.insert("reviews",null,cv2);


//                String sql2="select * from ratingrecord";
//                Cursor cursor2 = DbManager.selectDataBySQL(db, sql2, null);
//                int id2=1000;
//                while (cursor2.moveToNext()){
//                    id2=cursor2.getInt(cursor2.getColumnIndex("id"));
//                }
//                Log.i("test", "最后一个ratingrecord的id"+id2);
//                String insert2 = "insert into reviews values(" + String.valueOf(id2+1) + "," + userid  +","+  movieid + ",\'" + rating + "\',\'" + time+"\')";
//                db.execSQL(insert2);

                String sql = "select * from reviews";
                Cursor cursor = DbManager.selectDataBySQL(db, sql, null);
                int id=1000;
                while (cursor.moveToNext()){
                    id=cursor.getInt(cursor.getColumnIndex("id"));
                }
                Log.i("test", "最后一个review的id"+id);
//                int count=cursor.getCount();
//
                String insert = "insert into reviews values(" + String.valueOf(id+1) + "," + userid  +","+  movieid + ",\'" + content + "\',\'" + time+"\')";
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
