package com.example.movierecommendation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movierecommendation.R;

public class Fragment2 extends Fragment {

    private TextView cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,cat12,cat13,cat14,cat15,cat16,cat17,cat18;
    private ImageButton cate1,cate2,cate3,cate4,cate5,cate6,cate7,cate8,cate9,cate10,cate11,cate12,cate13,cate14,cate15,cate16,cate17,cate18;

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
        cat9=view.findViewById(R.id.category9);
        cat10=view.findViewById(R.id.category10);
        cat11=view.findViewById(R.id.category11);
        cat12=view.findViewById(R.id.category12);
        cat13=view.findViewById(R.id.category13);
        cat14=view.findViewById(R.id.category14);
        cat15=view.findViewById(R.id.category15);
        cat16=view.findViewById(R.id.category16);
        cat17=view.findViewById(R.id.category17);
        cat18=view.findViewById(R.id.category18);
        cate1=view.findViewById(R.id.cat1);
        cate2=view.findViewById(R.id.cat2);
        cate3=view.findViewById(R.id.cat3);
        cate4=view.findViewById(R.id.cat4);
        cate5=view.findViewById(R.id.cat5);
        cate6=view.findViewById(R.id.cat6);
        cate7=view.findViewById(R.id.cat7);
        cate8=view.findViewById(R.id.cat8);
        cate9=view.findViewById(R.id.cat9);
        cate10=view.findViewById(R.id.cat10);
        cate11=view.findViewById(R.id.cat11);
        cate12=view.findViewById(R.id.cat12);
        cate13=view.findViewById(R.id.cat13);
        cate14=view.findViewById(R.id.cat14);
        cate15=view.findViewById(R.id.cat15);
        cate16=view.findViewById(R.id.cat16);
        cate17=view.findViewById(R.id.cat17);
        cate18=view.findViewById(R.id.cat18);

        //给textview添加点击事件
        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cat 1:action
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Action");//到时候再替换
                intent.putExtra("catid","1");
                startActivity(intent);
            }
        });
        cate1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //cat 1:action
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Action");//到时候再替换
                intent.putExtra("catid","1");
                startActivity(intent);
            }
        });
        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Adventure");//到时候再替换
                intent.putExtra("catid","2");
                startActivity(intent);
            }
        });
        cate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Adventure");//到时候再替换
                intent.putExtra("catid","2");
                startActivity(intent);
            }
        });
        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Animation");//到时候再替换
                intent.putExtra("catid","3");
                startActivity(intent);
            }
        });
        cate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Animation");//到时候再替换
                intent.putExtra("catid","3");
                startActivity(intent);
            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Children's");//到时候再替换
                intent.putExtra("catid","4");
                startActivity(intent);
            }
        });
        cate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Children's");//到时候再替换
                intent.putExtra("catid","4");
                startActivity(intent);
            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Comedy");//到时候再替换
                intent.putExtra("catid","5");
                startActivity(intent);
            }
        });
        cate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Comedy");//到时候再替换
                intent.putExtra("catid","5");
                startActivity(intent);
            }
        });
        cat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Crime");//到时候再替换
                intent.putExtra("catid","6");
                startActivity(intent);
            }
        });
        cate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Crime");//到时候再替换
                intent.putExtra("catid","6");
                startActivity(intent);
            }
        });
        cat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Documentary");//到时候再替换
                intent.putExtra("catid","7");
                startActivity(intent);
            }
        });
        cate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Documentary");//到时候再替换
                intent.putExtra("catid","7");
                startActivity(intent);
            }
        });
        cat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Drama");//到时候再替换
                intent.putExtra("catid","8");
                startActivity(intent);
            }
        });
        cate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Drama");//到时候再替换
                intent.putExtra("catid","8");
                startActivity(intent);
            }
        });
        cat9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Fantasy");//到时候再替换
                intent.putExtra("catid","9");
                startActivity(intent);
            }
        });
        cate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Fantasy");//到时候再替换
                intent.putExtra("catid","9");
                startActivity(intent);
            }
        });
        cat10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Film-Noir");//到时候再替换
                intent.putExtra("catid","10");
                startActivity(intent);
            }
        });
        cate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Film-Noir");//到时候再替换
                intent.putExtra("catid","10");
                startActivity(intent);
            }
        });
        cat11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Horror");//到时候再替换
                intent.putExtra("catid","11");
                startActivity(intent);
            }
        });
        cate11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Horror");//到时候再替换
                intent.putExtra("catid","11");
                startActivity(intent);
            }
        });
        cat12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Musical");//到时候再替换
                intent.putExtra("catid","12");
                startActivity(intent);
            }
        });
        cate12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Musical");//到时候再替换
                intent.putExtra("catid","12");
                startActivity(intent);
            }
        });
        cat13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Mystery");//到时候再替换
                intent.putExtra("catid","13");
                startActivity(intent);
            }
        });
        cate13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Mystery");//到时候再替换
                intent.putExtra("catid","13");
                startActivity(intent);
            }
        });
        cat14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Romance");//到时候再替换
                intent.putExtra("catid","14");
                startActivity(intent);
            }
        });
        cate14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Romance");//到时候再替换
                intent.putExtra("catid","14");
                startActivity(intent);
            }
        });
        cat15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Sci-Fi");//到时候再替换
                intent.putExtra("catid","15");
                startActivity(intent);
            }
        });
        cate15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Sci-Fi");//到时候再替换
                intent.putExtra("catid","15");
                startActivity(intent);
            }
        });
        cat16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Thriller");//到时候再替换
                intent.putExtra("catid","16");
                startActivity(intent);
            }
        });
        cate16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Thriller");//到时候再替换
                intent.putExtra("catid","16");
                startActivity(intent);
            }
        });
        cat17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","War");//到时候再替换
                intent.putExtra("catid","17");
                startActivity(intent);
            }
        });
        cate17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","War");//到时候再替换
                intent.putExtra("catid","17");
                startActivity(intent);
            }
        });
        cat18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Western");//到时候再替换
                intent.putExtra("catid","18");
                startActivity(intent);
            }
        });
        cate18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                //跳转到cat1的页面,从fragemnt跳转到activity
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("category","Western");//到时候再替换
                intent.putExtra("catid","18");
                startActivity(intent);
            }
        });


        return view;
    }
}