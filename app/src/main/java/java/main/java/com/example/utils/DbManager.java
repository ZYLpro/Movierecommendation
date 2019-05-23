package main.java.com.example.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bean.Loginuser;
import com.example.utils.MySqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class DbManager {
    //主要工具类
    private static MySqliteHelper helper;

    public static MySqliteHelper getIntance(Context context) {
        //获得helper类对象
        if (helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    //根据sql语句在数据库中执行增删改操作
    public static void execSQL(SQLiteDatabase db, String sql) {
        //获得helper类对象
        if (db != null && !"".equals(sql)) {
            db.execSQL(sql);
        }
    }

    //根据sql语句在数据库中执行sql语句,db为查询数据库，sql为查询语句，selectionArgs为查询条件占位符，cursor为查询结果
    public static Cursor selectDataBySQL(SQLiteDatabase db, String sql, String[] selectionArgs) {
        //获得helper类对象
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, selectionArgs);
        }
        return cursor;
    }

    //Cursor对象转换为list对象
    public static List<Loginuser> cursorToList(Cursor cursor) {
        List<Loginuser> list = new ArrayList<>();
        //moveToNext()如果返回true，则表示下一记录存在，否则表示游标中的数据读取完毕
        while (cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex("userid");//根据参数中指定字段名称获取字段下标
            int userid = cursor.getInt(columnIndex);//根据参数中int值的value值
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            int gender = cursor.getInt(cursor.getColumnIndex("gender"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            int occupation = cursor.getInt(cursor.getColumnIndex("occupation"));
            Loginuser loginuser = new Loginuser(userid, username, password,gender,age,occupation);
            list.add(loginuser);
        }
        return list;
    }
}
