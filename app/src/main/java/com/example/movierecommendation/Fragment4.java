package com.example.movierecommendation;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.Database.Copy;
import com.example.bean.Review;
import com.example.utils.DbManager;


public class Fragment4 extends Fragment {
    private RecyclerView mRvTextList;
    private SQLiteDatabase db;
    private int count,columnNum;
    private String moviename;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page4, container, false);
        mRvTextList= (RecyclerView) view.findViewById(R.id.rv_text_list);
        mRvTextList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        Copy copy=new Copy();
        db=copy.openDatabase(getContext());

        String sql="select * from reviews";
        Cursor cursor = DbManager.selectDataBySQL(db,sql,null);
        columnNum=cursor.getCount();
        cursor.moveToLast();
        String[] reviewContent=new String[columnNum];
        String[] reviewName=new String[columnNum];
        count=0;
        while(cursor.moveToPrevious()) {
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int movieid = cursor.getInt(cursor.getColumnIndex("movieid"));

            String sql4 = "select * from movies where movieid=?";
            Cursor cursor2 = DbManager.selectDataBySQL(db, sql4, new String[]{String.valueOf(movieid)});
            while(cursor2.moveToNext()){
                moviename = cursor2.getString(cursor2.getColumnIndex("moviename"));
            }
            Log.i("tag",moviename);
            reviewContent[count] = content;
            reviewName[count] = name+"            "+moviename;
            count++;
            if (count == columnNum)
                break;
        }
        Review review=new Review();
        review.setContentArray(reviewContent);
        review.setNameArray(reviewName);
        db.close();
        mRvTextList.setAdapter(new TextListAdapter(getActivity(),review));

        return view;
    }
}

class TextListAdapter extends RecyclerView.Adapter<TextListAdapter.TextHolder> {
    private Activity mContent;
    private Review mReview;

    private final int MAX_LINE_COUNT = 3;

    private final int STATE_UNKNOW = -1;

    private final int STATE_NOT_OVERFLOW = 1;//文本行数不能超过限定行数

    private final int STATE_COLLAPSED = 2;//文本行数超过限定行数，进行折叠

    private final int STATE_EXPANDED = 3;//文本超过限定行数，被点击全文展开

    private SparseArray<Integer> mTextStateList;

    public TextListAdapter(Activity context, Review review) {
        mContent = context;
        mReview = review;
        mTextStateList = new SparseArray<>();
    }

    @Override
    public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextHolder(mContent.getLayoutInflater().inflate(R.layout.item_text_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final TextHolder holder, final int position) {
        holder.hend.setText(position + 1 + "");//设置头部的文字
        holder.name.setText(mReview.getName(position));//设置名称
        int state = mTextStateList.get(position, STATE_UNKNOW);
//        如果该itme是第一次初始化，则取获取文本的行数
        if (state == STATE_UNKNOW) {
            holder.content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
//                    这个回掉会调用多次，获取玩行数后记得注销监听
                    holder.content.getViewTreeObserver().removeOnPreDrawListener(this);
//                    holder.content.getViewTreeObserver().addOnPreDrawListener(null);
//                    如果内容显示的行数大于限定显示行数
                    if (holder.content.getLineCount() > MAX_LINE_COUNT) {
                        holder.content.setMaxLines(MAX_LINE_COUNT);//设置最大显示行数
                        holder.expandOrCollapse.setVisibility(View.VISIBLE);//让其显示全文的文本框状态为显示
                        holder.expandOrCollapse.setText("全文");//设置其文字为全文
                        mTextStateList.put(position, STATE_COLLAPSED);
                    } else {
                        holder.expandOrCollapse.setVisibility(View.GONE);//显示全文隐藏
                        mTextStateList.put(position, STATE_NOT_OVERFLOW);//让其不能超过限定的行数
                    }
                    return true;
                }
            });

            holder.content.setMaxLines(Integer.MAX_VALUE);//设置文本的最大行数，为整数的最大数值
            holder.content.setText(mReview.getContent(position));//用Util中的getContent方法获取内容
        } else {
//            如果之前已经初始化过了，则使用保存的状态，无需在获取一次
            switch (state) {
                case STATE_NOT_OVERFLOW:
                    holder.expandOrCollapse.setVisibility(View.GONE);
                    break;
                case STATE_COLLAPSED:
                    holder.content.setMaxLines(MAX_LINE_COUNT);
                    holder.expandOrCollapse.setVisibility(View.VISIBLE);
                    holder.expandOrCollapse.setText("全文");
                    break;
                case STATE_EXPANDED:
                    holder.content.setMaxLines(Integer.MAX_VALUE);
                    holder.expandOrCollapse.setVisibility(View.VISIBLE);
                    holder.expandOrCollapse.setText("收起");
                    break;
            }
            holder.content.setText(mReview.getContent(position));
        }


//        设置显示和收起的点击事件
        holder.expandOrCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = mTextStateList.get(position, STATE_UNKNOW);
                if (state == STATE_COLLAPSED) {
                    holder.content.setMaxLines(Integer.MAX_VALUE);
                    holder.expandOrCollapse.setText("收起");
                    mTextStateList.put(position, STATE_EXPANDED);
                } else if (state == STATE_EXPANDED) {
                    holder.content.setMaxLines(MAX_LINE_COUNT);
                    holder.expandOrCollapse.setText("全文");
                    mTextStateList.put(position, STATE_COLLAPSED);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 25;
    }

    public class TextHolder extends RecyclerView.ViewHolder {
        public TextView hend;
        public TextView name;
        public TextView content;
        public TextView expandOrCollapse;

        public TextHolder(View itemView) {
            super(itemView);
//            绑定xml布局中的控件
            hend = (TextView) itemView.findViewById(R.id.tv_hend);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            expandOrCollapse = (TextView) itemView.findViewById(R.id.tv_expand_or_collapse);
        }
    }
}