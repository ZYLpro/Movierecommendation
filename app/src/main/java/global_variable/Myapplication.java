package global_variable;

import android.app.Application;
import android.widget.ImageView;

public class Myapplication extends Application {
    private String NICKNAME = "123";
    private ImageView AVATAR = null;
    private int IsFirst = -1;//登录次数，-1未初始化
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

    public void setIsFirst(int isf) {IsFirst = isf; }
    public int getIsFirst() { return IsFirst; }
}
