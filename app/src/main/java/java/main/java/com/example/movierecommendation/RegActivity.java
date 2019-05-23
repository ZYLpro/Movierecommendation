package main.java.com.example.movierecommendation;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import com.example.bean.Loginuser;
import com.example.movierecommendation.R;
import com.example.utils.Constant;
import com.example.utils.DbManager;
import com.example.utils.MySqliteHelper;

import java.util.List;

import static android.R.layout.simple_spinner_item;


public class RegActivity extends Activity {
    private MySqliteHelper helper;//数据库辅助操作类
    private RadioGroup mSex_group;
    private RadioButton mMale,mFemale;
    private int sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        helper = DbManager.getIntance(this);
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
        mSex_group = (RadioGroup) findViewById(R.id.sex_group);
        mMale = (RadioButton) findViewById(R.id.male);
        mFemale = (RadioButton) findViewById(R.id.female);
        mSex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mMale.getId() == checkedId) {
                    sex=0;
                } else if (mFemale.getId() == checkedId) {
                    sex=1;
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
                int num = (int) mSpinner.getSelectedItemId();
                int age=1;
                switch (num){
                    case 0:
                        age=1;break;
                    case 1:
                        age=18;break;
                    case 2:
                        age=25;break;
                    case 3:
                        age=35;break;
                    case 4:
                        age=45;break;
                    case 5:
                        age=50;break;
                    case 6:
                        age=56;break;
                }
                boolean test=register(username,password,age,sex);
                /*if (register(username, password, age,sex)) {
                    Intent intent = new Intent(RegActivity.this, MainActivity.class);
                    startActivity(intent);
                    RegActivity.this.finish();
                }*/
            }
        });
    }

    public boolean register(String username, String password, int age,int sex) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Log.i("tag","username="+username+"pwd="+password+"age="+String.valueOf(age)+"gender="+String.valueOf(sex));
        if (!username.equals("") && !password.equals("")) {
            if (judge(username)) {
                ContentValues values=new ContentValues();
                values.put(Constant.LOGIN_USERNAME,username);//第一字段表示插入字段名称，第二为具体值
                values.put(Constant.LOGIN_PASSWORD,password);
                values.put(Constant.LOGIN_AGE,age);
                values.put(Constant.LOGIN_GENDER,sex);
                long result=db.insert(Constant.TABLE_LOGINUSER,null,values);
                if(result>0){
                    Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    return true;
                }else{
                    Toast.makeText(RegActivity.this, "插入失败", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(RegActivity.this, "用户名已存在，请更换昵称", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(RegActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        /*
        Cursor cursor = DbManager.selectDataBySQL(db,sql,new String[]{username,password});
        List<Loginuser> list=DbManager.cursorToList(cursor);
        for(Loginuser l:list){
            Log.i("tag", l.toString());
        }
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        db.close();*/
    }

    public boolean judge(String username) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "select * from loginusers where username=?";
        Cursor cursor = DbManager.selectDataBySQL(db, sql, new String[]{username});
        List<Loginuser> list = DbManager.cursorToList(cursor);
        if (list.size() > 0) {
            return false;
        }
        db.close();
        return true;
    }
}
