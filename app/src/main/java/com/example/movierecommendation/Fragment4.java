package com.example.movierecommendation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Database.Copy;
import com.example.bean.Review;
import com.example.utils.DbManager;


public class Fragment4 extends Fragment {
    private RecyclerView mRvTextList;
    private SQLiteDatabase db;
    private int count,columnNum;
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
            reviewContent[count] = content;
            reviewName[count] = name;
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