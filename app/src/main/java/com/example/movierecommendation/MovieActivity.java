package com.example.movierecommendation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Database.Copy;
import com.example.utils.DbManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieActivity extends AppCompatActivity {
    private ListView list_lv;
    public  SQLiteDatabase db;
    private List<Map<String, String>> data;
    private MyAdapter adapter;

    //上方信息
    private TextView moviename;
    private TextView category;
    private TextView intro;
    private TextView director;
    private TextView rating;

    private ImageView poster;

    private String movienamestring;
    private String categorystring;
    private String introstring;
    private String directorstring;
    private String ratingstring;

    //添加评论
    private Button add;
    int userid=1;
    private int movieid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_single);
        //获取数据库
        Copy copy=new Copy();
        db=copy.openDatabase(getApplicationContext());
        //获取movieid
        // 获取意图对象
        Intent intent = getIntent();
        Bundle b=intent.getExtras();
        //获取传递的值

        movieid=b.getInt("movieid");
        ratingstring=b.getString("rating");
        Log.i("tag", String.valueOf(movieid));

        //填写上方信息

        //初始化部件
        moviename=findViewById(R.id.moviename);
        category=findViewById(R.id.category);
        director=findViewById(R.id.director);
        rating=findViewById(R.id.rating);
        intro=findViewById(R.id.intro);

        poster=findViewById(R.id.poster);

        add=findViewById(R.id.add);

        //获取内容
        String sql = "select * from movies where movieid=?";
        Cursor cursor2 = DbManager.selectDataBySQL(db, sql, new String[]{String.valueOf(movieid)});
        while(cursor2.moveToNext()){
            Log.i("test", "------读movie内容-----");
            movienamestring=cursor2.getString(cursor2.getColumnIndex("moviename"));
            categorystring=cursor2.getString(cursor2.getColumnIndex("category"));
            introstring=cursor2.getString(cursor2.getColumnIndex("intro"));
            directorstring=cursor2.getString(cursor2.getColumnIndex("director"));
            String sql5 = "select * from movierating where movieid=?";
            Cursor cursor5 = DbManager.selectDataBySQL(db, sql5, new String[]{String.valueOf(movieid)});
            while(cursor5.moveToNext()){
                ratingstring=cursor5.getString((cursor5.getColumnIndex("rating")));
            }
        }

        //设置内容
        moviename.setText(movienamestring);
        category.setText(categorystring);
        director.setText(directorstring);
        rating.setText(ratingstring);
        intro.setText(introstring);

        poster.getDrawable().setLevel(movieid-1);

        //取reviewc
        data=new ArrayList<Map<String,String>>();

        String sql3 = "select * from reviews where movieid=?";
        Cursor cursor = DbManager.selectDataBySQL(db, sql3, new String[]{String.valueOf(movieid)});


        //  List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map;
        String username=null;
        while(cursor.moveToNext()){
            Log.i("test", "------读review内容-----");
            int userid=cursor.getInt(cursor.getColumnIndex("userid"));
            String content=cursor.getString(cursor.getColumnIndex("content"));

            //从loginuser中毒username
            String sql4 = "select * from loginusers where userid=?";
            Cursor cursor4 = DbManager.selectDataBySQL(db, sql4, new String[]{String.valueOf(userid)});
            while (cursor4.moveToNext()){
                username=cursor4.getString(cursor4.getColumnIndex("username"));
            }

            map = new HashMap<String, String>();
            map.put("username", username);
            map.put("content",content);
            data.add(map);
        }
        cursor.close();
        db.close();

        list_lv=(ListView) findViewById(R.id.reviewlist);
        adapter=new MyAdapter(this);
        list_lv.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MovieActivity.this,AddReviewActivity.class);
                Bundle b=new Bundle();
                b.putInt("movieid",movieid);
                b.putInt("userid",userid);
                //还要传rating，因为跳回的时候需要
                b.putString("rating",String.valueOf(rating));
                intent1.putExtras(b);//到时候再替换


                startActivity(intent1);
            }
        });
    }

    static class ViewHolder
    {
        public TextView username;
        public TextView content;
    }
    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater = null;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder item;
            Log.v("BaseAdapterTest", "getView " + position + " " + convertView);

            if (convertView == null) {
                item = new ViewHolder();
                convertView = mInflater.inflate(R.layout.review_item, null);
                item.username = (TextView) convertView.findViewById(R.id.username);
                item.content = (TextView) convertView.findViewById(R.id.content);
                convertView.setTag(item); //绑定ViewHolder对象
            } else {
                item = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
            /**设置TextView显示的内容，即我们存放在动态数组中的数据*/
            item.username.setText(data.get(position).get("username").toString());
            item.content.setText(data.get(position).get("content").toString());


            return convertView;
        }
    }




}
