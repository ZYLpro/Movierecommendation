package global_variable;

import android.app.Application;

public class Myapplication extends Application {
    private String NICKNAME = "123";

    @Override
    public void onCreate() {
        super.onCreate();
    }
    public void setname(String name){
        NICKNAME = name;
    }
    public String getname(){
        return NICKNAME;
    }
}
