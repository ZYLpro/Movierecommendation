package com.example.movierecommendation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Database.Copy;
import com.example.utils.DbManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryActivity extends AppCompatActivity{

    private TextView cat;
    private int catid;

//    private TextView movie1,movie2,movie3,movie4;//先写4个

    private ListView lv;
    private View footer;
    //分页
    private boolean loadFinishFlag;
    private int startIndex;
    private int endIndex;
    private final int pageSize = 10;
    private int total;

    public SQLiteDatabase db;
    private List<Map<String, String>> data;
    private MyAdapter adapter;

    String moviename=null;
    String rating=null;

    //设置海报
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_single);

        // 获取意图对象
        Intent intent = getIntent();
        //获取传递的值
        String category = intent.getStringExtra("category");
        catid=Integer.parseInt(intent.getStringExtra("catid"));
        //int catid=Integer.parseInt(intent.getStringExtra("catid"));
        Log.i("test", "类别id："+catid);

        cat=findViewById(R.id.cat);
        cat.setText(category);
        data=new ArrayList<Map<String,String>>();

        Copy copy=new Copy();
        db=copy.openDatabase(getApplicationContext());
        String sql3 = "select * from movie_cat where catid=?";
        Cursor cursor = DbManager.selectDataBySQL(db, sql3, new String[]{String.valueOf(catid)});


        Map<String, String> map;


        while(cursor.moveToNext()){
            Log.i("test", "------读每一个movieid，再到movies去查询-----");
            int movieid=cursor.getInt(cursor.getColumnIndex("movieid"));

            //从movies表中读电影名和年份
            String sql = "select * from movies where movieid=?";
            Cursor cursor1= DbManager.selectDataBySQL(db, sql, new String[]{String.valueOf(movieid)});
            while(cursor1.moveToNext()){
                moviename=cursor1.getString(cursor1.getColumnIndex("moviename"));
            }
            //从movierating表读评分
            String sql2 = "select * from movierating where movieid=?";
            Cursor cursor2= DbManager.selectDataBySQL(db, sql2, new String[]{String.valueOf(movieid)});
            while(cursor2.moveToNext()){
                rating=cursor2.getString(cursor2.getColumnIndex("rating"));
            }
            map = new HashMap<String, String>();


            map.put("moviename",moviename);
            map.put("movieid",movieid+"");
            map.put("rating",rating);
            //cursor1.close();
            data.add(map);
        }
        cursor.close();
        db.close();





        lv=(ListView) findViewById(R.id.movielist);
        adapter=new MyAdapter(this);
        lv.setAdapter(adapter);


        //添加点击事件的监听
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //通过view获取其内部的组件，进而进行操作
                String text = (String) ((TextView)view.findViewById(R.id.movieid)).getText();
                int movieid=Integer.parseInt(text);

                Bundle b=new Bundle();
                b.putString("rating",rating);
                b.putInt("movieid",movieid);

                Intent intent = new Intent(CategoryActivity.this, MovieActivity.class);
                intent.putExtras(b);//到时候再替换
                startActivity(intent);

                //大多数情况下，position和id相同，并且都从0开始
//                String showText = "点击第" + position + "项，文本内容为：" + text + "，ID为：" + id;
//                Toast.makeText(CategoryActivity.this, showText,Toast.LENGTH_LONG).show();
            }
        });

    }



    static class ViewHolder
    {
        public TextView moviename;
        public TextView rating;
        public TextView movieid;
    }
    public class MyAdapter extends BaseAdapter
    {

        private LayoutInflater mInflater = null;
        public MyAdapter(Context context)
        {
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
            ViewHolder item ;
            Log.v("BaseAdapterTest", "getView " + position + " " + convertView);

            if(convertView ==null)
            {
                item = new ViewHolder();
                convertView = mInflater.inflate(R.layout.movie_item, null);

                imageView=convertView.findViewById(R.id.poster);

                item.moviename=(TextView) convertView.findViewById(R.id.moviename);
                item.rating=(TextView) convertView.findViewById(R.id.rating);
                item.movieid=(TextView) convertView.findViewById(R.id.movieid);

                convertView.setTag(item); //绑定ViewHolder对象
            }
            else
            {
                item = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
            /**设置TextView显示的内容，即我们存放在动态数组中的数据*/

            String movie=data.get(position).get("movieid");
            int movieid=Integer.parseInt(movie);
           // Log.v("test", "Map中读出来movie的内容"+content);
            item.moviename.setText(data.get(position).get("moviename"));
            item.movieid.setText(data.get(position).get("movieid"));
            item.rating.setText(data.get(position).get("rating"));

            //设置海报
           // imageView.getDrawable().setLevel(movieid-1);

            return convertView;
        }

    }


//    //分页
//    public List<Map<String, String>> getDataService(int from, int to) {
//        //在数据库中查询改类别，显示该类别的电影
//        data=new ArrayList<Map<String,String>>();
//
//        Cursor cursor;
//       //剩下的多余10条
////        if(to>total-1) {
////            //每次读10条
////            String sql3 = "select * from movie_cat where catid=? limit ?,10";
////            cursor = DbManager.selectDataBySQL(db, sql3, new String[]{String.valueOf(catid), String.valueOf(startIndex)});
////        }else{//剩下的小于等于10
////            String sql3 = "select * from movie_cat where catid=? limit ?,?";
////            cursor = DbManager.selectDataBySQL(db, sql3, new String[]{String.valueOf(catid), String.valueOf(startIndex),String.valueOf(total-from)});
////        }
//        String sql3 = "select * from movie_cat where catid=? limit ?,10";
//        cursor = DbManager.selectDataBySQL(db, sql3, new String[]{String.valueOf(catid), String.valueOf(startIndex)});
//
//        Map<String, String> map;
//
//
//        while(cursor.moveToNext()){
//            Log.i("test", "------读每一个movieid，再到movies去查询-----");
//            int movieid=cursor.getInt(cursor.getColumnIndex("movieid"));
//
//            //从movies表中读电影名和年份
//            String sql = "select * from movies where movieid=?";
//            Cursor cursor1= DbManager.selectDataBySQL(db, sql, new String[]{String.valueOf(movieid)});
//            while(cursor1.moveToNext()){
//                moviename=cursor1.getString(cursor1.getColumnIndex("moviename"));
//            }
//            //从movierating表读评分
//            String sql2 = "select * from movierating where movieid=?";
//            Cursor cursor2= DbManager.selectDataBySQL(db, sql2, new String[]{String.valueOf(movieid)});
//            while(cursor2.moveToNext()){
//                rating=cursor2.getString(cursor2.getColumnIndex("rating"));
//            }
//            map = new HashMap<String, String>();
//
//
//            map.put("moviename",moviename);
//            map.put("movieid",movieid+"");
//            map.put("rating",rating);
//            //cursor1.close();
//            data.add(map);
//        }
//        cursor.close();
//
//
//       return data;
//    }
//
//
//    public final class ScrollListener implements AbsListView.OnScrollListener {
//        @Override
//        public void onScrollStateChanged(AbsListView view, int scrollState) {
//            Log.i("test","---->" + scrollState);
//            switch (scrollState) {
//                case SCROLL_STATE_IDLE:
//                    break;
//                case SCROLL_STATE_TOUCH_SCROLL:
//                    break;
//                case SCROLL_STATE_FLING:
//                    break;
//            }
//        }
//
//        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//            //获取屏幕最后Item的ID
//            Log.i("test","调用---->"+firstVisibleItem+"   "+visibleItemCount +"  "+totalItemCount );
//            int lastVisibleItem = lv.getLastVisiblePosition();
//            if (lastVisibleItem + 1 == totalItemCount) {
//                if (loadFinishFlag) {
//                    //标志位，防止多次加载
//                    loadFinishFlag = false;
//                    lv.addFooterView(footer);
//                    //开线程加载数据
//                    new Thread() {
//                        @Override
//                        public void run() {
//                            super.run();
//                            try {
//                                Thread.sleep(3000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            startIndex += pageSize;
//                            endIndex += pageSize;
//                            if(startIndex<total) {
//                                Message message = handler.obtainMessage(0x123, getDataService(startIndex, endIndex));
//                                message.sendToTarget();
//                            }
//
//                        }
//                    }.start();
//                }
//            }
//        }
//    }
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 0x123) {
//                data.addAll((List<Map<String, String>>) msg.obj);
//                adapter.notifyDataSetChanged();
//                lv.removeFooterView(footer);
//                loadFinishFlag = true;
//            }
//        }
//    };


}
