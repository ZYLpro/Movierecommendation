package main.java.com.example.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.utils.Constant;

public class MySqliteHelper extends SQLiteOpenHelper {
    private static String DB_PATH= "data/data/com.example.movierecommendation/databases/";
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    public MySqliteHelper(Context context) {
        super(context, com.example.utils.Constant.DATABASE_NAME,null, Constant.DATABASE_VERSION);
    }
    //数据库创建时回调函数
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.i("tag","-------------onCreate--------------");
        String table_age="create table ages(id Integer primary key AUTOINCREMENT, ageid Integer,agerange varchar(255))";
        String table_category="create table categories(cid Integer primary key AUTOINCREMENT, cname varchar(255))";
        String table_loginusers="create table loginusers(userid Integer primary key AUTOINCREMENT, username varchar(255),password varchar(255),gender Integer,age Integer)";
        String table_movieandage="create table movieandage(movieid Integer, age Integer,count Integer)";
        String table_movieandgender="create table movieandgender(movieid Integer, male Integer,female Integer)";
        String table_movieandoccupation="create table movieandoccupation(movieid Integer, occupation Integer,count Integer)";
        String table_movieandrank="create table movieandrank(movieid Integer, rank Integer,count Integer)";
        String table_movierating="create table movierating(movieid Integer, rating Float,num Integer, sum Integer)";
        String table_movies="create table movies(movieid Integer primary key AUTOINCREMENT, moviename varchar(255),category varchar(255), intro varchar(255),year varchar(255),director varchar(255))";
        String table_movie_cat="create table movie_cat(movieid Integer, catid Integer)";
        String table_occupations="create table occupations(oid Integer primary key AUTOINCREMENT, oname varchar(255))";
        String table_ratingrecord="create table ratingrecord(id Integer primary key AUTOINCREMENT, userid Integer,movieid Integer, rating Integer)";
        String table_reviews="create table ratingrecord(id Integer primary key AUTOINCREMENT, userid Integer,movieid Integer, content varchar(255))";
        db.execSQL(table_age);//执行sql语句
        db.execSQL(table_category);
        db.execSQL(table_loginusers);
        db.execSQL(table_movieandage);
        db.execSQL(table_movieandgender);
        db.execSQL(table_movieandoccupation);
        db.execSQL(table_movieandrank);
        db.execSQL(table_movierating);
        db.execSQL(table_movies);
        db.execSQL(table_movie_cat);
        db.execSQL(table_occupations);
        db.execSQL(table_ratingrecord);
        db.execSQL(table_reviews);
        db.close();
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
