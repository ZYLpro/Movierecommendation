package com.example.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteHelper extends SQLiteOpenHelper {
    private static String DB_PATH= "data/data/com.example.movierecommendation/databases/";
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    public MySqliteHelper(Context context) {
        super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }
    //数据库创建时回调函数
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.i("tag","-------------onCreate--------------");
        String sql="create table admins(adminid Integer primary key, adminname varchar(255),age Integer)";
        db.execSQL(sql);//执行sql语句
    }
    @Override
    public void onOpen(SQLiteDatabase db){
        Log.i("tag","-------------onOpen--------------");
        super.onOpen(db);
    }
    //数据库版本更新时回调函数，oldVersion，newVersion
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
