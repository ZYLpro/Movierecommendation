package com.example.movierecommendation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Database.Copy;
import com.example.bean.Loginuser;
import com.example.utils.DbManager;

import java.util.List;

import global_variable.Myapplication;


public class RegActivity extends Activity {
    //private MySqliteHelper helper;//数据库辅助操作类
    public SQLiteDatabase db;
    private RadioGroup mSex_group;
    private RadioButton mMale,mFemale;
    private int sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //获取数据库
        Copy copy=new Copy();
        db=copy.openDatabase(getApplicationContext());

        // 初始化控件
        final Spinner mSpinner = (Spinner) findViewById(R.id.agespinner);
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.age);
        //将可选内容与ArrayAdapter连接，
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String str = parent.getItemAtPosition(position).toString();
                Toast.makeText(RegActivity.this, "选择的年龄段是:" + str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        final Spinner occupationSpinner = (Spinner) findViewById(R.id.occupationspinner);
        // 建立数据源
        String[] mOccupations = getResources().getStringArray(R.array.occupation);
        //将可选内容与ArrayAdapter连接，
        ArrayAdapter<String> adapterOccupation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mOccupations);
        //绑定 Adapter到控件
        occupationSpinner.setAdapter(adapterOccupation);
        occupationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String str = parent.getItemAtPosition(position).toString();
                Toast.makeText(RegActivity.this, "选择的职业是:" + str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        mSex_group = (RadioGroup) findViewById(R.id.sex_group);
        mMale = (RadioButton) findViewById(R.id.male);
        mFemale = (RadioButton) findViewById(R.id.female);
        mSex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mMale.getId() == checkedId) {
                    sex = 0;
                } else if (mFemale.getId() == checkedId) {
                    sex = 1;
                }
            }
        });
        Button button = (Button) findViewById(R.id.reg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editUser = (EditText) findViewById(R.id.username);
                EditText editPass = (EditText) findViewById(R.id.password);
                String username = editUser.getText().toString();
                String password = editPass.getText().toString();
                int occupation=(int)occupationSpinner.getSelectedItemId();
                int num = (int) mSpinner.getSelectedItemId();
                int age = 1;
                switch (num) {
                    case 0:
                        age = 1;break;
                    case 1:
                        age = 18;break;
                    case 2:
                        age = 25;break;
                    case 3:
                        age = 35;break;
                    case 4:
                        age = 45;break;
                    case 5:
                        age = 50;break;
                    case 6:
                        age = 56;break;
                }
                if (!username.equals("") && !password.equals("")) {
                    if (judge(username)) {
//                        SQLiteDatabase db = helper.getWritableDatabase();
                        String sql = "select * from loginusers";
                        Cursor cursor = DbManager.selectDataBySQL(db, sql, null);
                        List<Loginuser> list = DbManager.cursorToList(cursor);
                        String insert = "insert into loginusers values(" + String.valueOf(list.size() + 1) + ",\'" + username + "\',\'" + password + "\'," + String.valueOf(sex) + "," + String.valueOf(age) + ", "+String.valueOf(occupation)+")";
                        db.execSQL(insert);
                        for (Loginuser l : list) {
                            Log.i("tag", l.toString());
                        }
                        Myapplication myapp = (Myapplication) RegActivity.this.getApplication();
                        myapp.setname(username);
                        db.close();
                        Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegActivity.this, MainActivity.class);
                        startActivity(intent);
                        RegActivity.this.finish();
                    }else {
                        Toast.makeText(RegActivity.this, "用户名已存在，请更换昵称", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean judge(String username) {
        String sql = "select * from loginusers where username=?";
        Cursor cursor = DbManager.selectDataBySQL(db, sql, new String[]{username});
        List<Loginuser> list = DbManager.cursorToList(cursor);
        if (list.size() > 0) {
            return false;//已经存在用户名
        }
        return true;
    }
}
