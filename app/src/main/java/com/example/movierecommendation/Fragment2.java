package com.example.movierecommendation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movierecommendation.R;

public class Fragment2 extends Fragment {

    private TextView cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page2, container, false);
        cat1=view.findViewById(R.id.category1);
        cat2=view.findViewById(R.id.category2);
        cat3=view.findViewById(R.id.category3);
        cat4=view.findViewById(R.id.category4);
        cat5=view.findViewById(R.id.category5);
        cat6=view.findViewById(R.id.category6);
        cat7=view.findViewById(R.id.category7);
        cat8=view.findViewById(R.id.category8);


        //给textview添加点击事件
        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","cat1");//到时候再替换
                startActivity(intent);
            }
        });
        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","cat2");//到时候再替换
                startActivity(intent);
            }
        });
        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","cat3");//到时候再替换
                startActivity(intent);
            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","cat4");//到时候再替换
                startActivity(intent);
            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","cat5");//到时候再替换
                startActivity(intent);
            }
        });
        cat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","cat6");//到时候再替换
                startActivity(intent);
            }
        });
        cat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","cat7");//到时候再替换
                startActivity(intent);
            }
        });
        cat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","cat8");//到时候再替换
                startActivity(intent);
            }
        });


        return view;
    }
}