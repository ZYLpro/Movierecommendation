package global_variable;

import android.app.Application;
import android.widget.ImageView;

public class Myapplication extends Application {
    private String NICKNAME = "123";
    private int USERID = 0;
    private ImageView AVATAR = null;
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
    public void setimage(ImageView image) {AVATAR = image; }
    public ImageView getimage() { return AVATAR; }
    public void setUserid(int userid){
        USERID = userid;
    }
    public int getUserid(){
        return USERID;
    }
}
