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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.Database.Copy;
import com.example.movierecommendation.R;
import com.example.utils.DbManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

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

    public LinearLayout linearLayout;

    //图片轮播
    private  Banner banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page1, container, false);

        linearLayout=view.findViewById(R.id.hhh);

        banner = (Banner) view.findViewById(R.id.banner);
        //选择banner的模式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

        int imageresource[]=new int[]{R.drawable.i1,R.drawable.i2,R.drawable.i4,R.drawable.i5};
        String titles[]=new String[]{"电影1","电影2","电影3","电影4"};

        List<Integer> imagelist=new ArrayList<>();
        List<String>  titlelist=new ArrayList<>();
        for(int i=0;i<imageresource.length;i++){
            imagelist.add(imageresource[i]);
            titlelist.add(titles[i]);
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(Fragment1.this).load(path).into(imageView);
                }
            });
            banner.setBannerTitles(titlelist);
            banner.setDelayTime(3000);//轮播时间
            banner.setImages(imagelist);//图片资源
            banner.start();
        }

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Log.i("tag", "你点了第"+position+"张轮播图");
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