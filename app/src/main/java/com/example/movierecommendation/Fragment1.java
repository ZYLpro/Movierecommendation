package com.example.movierecommendation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Database.Copy;
import com.example.movierecommendation.R;
import com.example.utils.DbManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment1 extends Fragment {
    private ListView lv;
    public SQLiteDatabase db;
    private List<Map<String, String>> data;
    private Fragment1.MyAdapter adapter;

    String moviename=null;
    String rating=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page1, container, false);
        //在数据库中查询改类别，显示该类别的电影
        data=new ArrayList<Map<String,String>>();
        Copy copy=new Copy();
        db=copy.openDatabase(getContext());
        String sql = "select * from movierating order by rating desc";
        Cursor cursor = DbManager.selectDataBySQL(db, sql, null);
        Map<String, String> map;
        int count=0;
        while(cursor.moveToNext()) {
            //从movierating表读评分
            rating=cursor.getString(cursor.getColumnIndex("rating"));
            int movieid = cursor.getInt(cursor.getColumnIndex("movieid"));
            String sql2 = "select * from movies where movieid=?";
            Cursor cursor2 = DbManager.selectDataBySQL(db, sql2, new String[]{String.valueOf(movieid)});
            while (cursor2.moveToNext()) {
                moviename = cursor2.getString(cursor2.getColumnIndex("moviename"));
            }
            Log.i("tag", moviename+"  "+rating);
            map = new HashMap<String, String>();


            map.put("moviename", moviename);
            map.put("movieid", movieid + "");
            map.put("rating", rating);
            //cursor1.close();
            data.add(map);
            count++;
            if(count==30)
                break;
        }
        db.close();
        cursor.close();

        lv=(ListView) view.findViewById(R.id.movielist);
        adapter=new Fragment1.MyAdapter(getContext());
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


                Intent intent = new Intent(getActivity(), MovieActivity.class);
                intent.putExtra("rating",rating);//到时候再替换
                intent.putExtra("movieid",movieid);//到时候再替换
                startActivity(intent);

                //大多数情况下，position和id相同，并且都从0开始
//                String showText = "点击第" + position + "项，文本内容为：" + text + "，ID为：" + id;
//                Toast.makeText(CategoryActivity.this, showText,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    static class ViewHolder
    {
        public TextView moviename;
        public TextView rating;
        public TextView movieid;
    }
    class MyAdapter extends BaseAdapter
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
            CategoryActivity.ViewHolder item ;
            Log.v("BaseAdapterTest", "getView " + position + " " + convertView);

            if(convertView ==null)
            {
                item = new CategoryActivity.ViewHolder();
                convertView = mInflater.inflate(R.layout.movie_item, null);
                item.moviename=(TextView) convertView.findViewById(R.id.moviename);
                item.rating=(TextView) convertView.findViewById(R.id.rating);
                item.movieid=(TextView) convertView.findViewById(R.id.movieid);

                convertView.setTag(item); //绑定ViewHolder对象
            }
            else
            {
                item = (CategoryActivity.ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
            /**设置TextView显示的内容，即我们存放在动态数组中的数据*/


            // Log.v("test", "Map中读出来movie的内容"+content);
            item.moviename.setText(data.get(position).get("moviename"));
            item.movieid.setText(data.get(position).get("movieid"));
            item.rating.setText(data.get(position).get("rating"));


            return convertView;
        }

    }
}