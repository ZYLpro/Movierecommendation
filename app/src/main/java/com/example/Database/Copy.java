package com.example.Database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Copy {
    String filePath = "data/data/com.example.movierecommendation/databases/movies.db";
    String pathStr = "data/data/com.example.movierecommendation/databases";
    SQLiteDatabase database;

    public  SQLiteDatabase openDatabase(Context context){
        System.out.println("filePath:"+filePath);
        File file=new File(filePath);
        //查看数据库文件是否存在
        if(file.exists()){
            Log.i("test", "------存在数据库-----");
            //存在则直接返回打开的数据库
            return SQLiteDatabase.openOrCreateDatabase(file, null);
        }else{
            //不存在先创建文件夹
            File path=new File(pathStr);
            Log.i("test", "pathStr="+path);
            if (path.mkdir()){
                Log.i("test", "----数据库路径创建成功----");
            }else{
                Log.i("test", "----数据库路径创建失败----");
            };
            try {
                //得到资源
                AssetManager am= context.getAssets();
                //得到数据库的输入流
                InputStream is= null;
                try {
                    is = am.open("movies.db");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("test", is+"");
                //用输出流写到SDcard上面
                FileOutputStream fos=new FileOutputStream(file);
                Log.i("test", "fos="+fos);
                Log.i("test", "jhPath="+file);
                //创建byte数组  用于1KB写一次
                byte[] buffer=new byte[1024];
                int count = 0;
                while((count = is.read(buffer))>0){
                    Log.i("test", "----正在复制数据库-----");
                    fos.write(buffer,0,count);
                }
                //最后关闭就可以了
                fos.flush();
                fos.close();
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
            //如果没有这个数据库  我们已经把他写到SD卡上了，然后在执行一次这个方法 就可以返回数据库了
            return openDatabase(context);
        }
    }



}
