package sidebar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.Database.Copy;
import com.example.bean.Loginuser;
import com.example.movierecommendation.MainActivity;
import com.example.movierecommendation.R;
import com.example.utils.DbManager;

import java.io.File;
import java.util.List;

import global_variable.Myapplication;

public class Sb_manage_changeavatar extends Activity {
    private ImageView mImage;
    private Button mAddImage;
    private Bitmap mBitmap;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;

    private RadioGroup mSex_group;
    private RadioButton mMale,mFemale;
    private String email;
    private int age,gender,occupation;
    private Myapplication myapp;

    private SQLiteDatabase db;
    private List<Loginuser> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_manage_message);
        initUI();
        initListeners();

        Copy copy=new Copy();
        db=copy.openDatabase(getApplicationContext());

        myapp = (Myapplication)Sb_manage_changeavatar.this.getApplication();
        EditText editText = findViewById(R.id.nickname);
        editText.setText(myapp.getname());
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        final EditText emailEdit=(EditText)findViewById(R.id.email);
        String sql = "select * from loginusers where username=?";
        Cursor cursor = DbManager.selectDataBySQL(db, sql, new String[]{myapp.getname()});
        list = DbManager.cursorToList(cursor);
        if (list.size() == 1) {
            Log.i("tag",list.toString());
            String user=list.toString();
            gender=Integer.valueOf(user.substring(user.indexOf("gender")+7,user.indexOf("gender")+8));
            age= Integer.valueOf(user.substring(user.indexOf("age")+4,user.indexOf("age")+5));
            occupation=Integer.valueOf(user.substring(user.indexOf("occupation")+11,user.length()-2));
            email=user.substring(user.indexOf("email")+6,user.indexOf(" occupation")-1);
            //Log.i("tag",user.substring(user.indexOf("gender")+7,user.indexOf("gender")+8));
            //Log.i("tag",user.substring(user.indexOf("age")+4,user.indexOf("age")+5));
            Log.i("tag",user.substring(user.indexOf("email")+6,user.indexOf(" occupation")-1));
        }
        emailEdit.setText(email);
        //将可选内容与ArrayAdapter连接，
        final Spinner ageSpinner = (Spinner) findViewById(R.id.agespinner);
        String[] mAges = getResources().getStringArray(R.array.age);
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mAges);
        ageSpinner.setAdapter(ageAdapter);
        ageSpinner.setSelection(age,true);
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String str = parent.getItemAtPosition(position).toString();
                Toast.makeText(Sb_manage_changeavatar.this, "选择的年龄段是:" + str, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        final Spinner occupationSpinner = (Spinner) findViewById(R.id.occupationspinner);
        String[] mOccupations = getResources().getStringArray(R.array.occupation);
        ArrayAdapter<String> adapterOccupation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mOccupations);
        occupationSpinner.setAdapter(adapterOccupation);
        occupationSpinner.setSelection(occupation,true);
        occupationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String str = parent.getItemAtPosition(position).toString();
                Toast.makeText(Sb_manage_changeavatar.this, "选择的职业是:" + str, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        mSex_group = (RadioGroup) findViewById(R.id.sex_group);
        mMale = (RadioButton) findViewById(R.id.male);
        mFemale = (RadioButton) findViewById(R.id.female);
        if(gender==0){
            mMale.setChecked(true);
        }else{
            mFemale.setChecked(true);
        }
        mSex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mMale.getId() == checkedId) {
                    gender = 0;
                } else if (mFemale.getId() == checkedId) {
                    gender = 1;
                }
            }
        });

        Button button = findViewById(R.id.确认修改);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int occupation = (int) occupationSpinner.getSelectedItemId();
                int num = (int) ageSpinner.getSelectedItemId();
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
                String update = "update loginusers set gender="  + String.valueOf(gender) + " ,age=" + String.valueOf(age) + ", occupation=" + String.valueOf(occupation) +", email=\'" + emailEdit.getText().toString() +  "\' where username=\'"+myapp.getname()+"\'";
                DbManager.execSQL(db,update);
                Log.i("tag",update);
                db.close();
                Toast.makeText(Sb_manage_changeavatar.this, "个人信息修改成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Sb_manage_changeavatar.this, MainActivity.class);
                startActivity(intent);
                Sb_manage_changeavatar.this.finish();

            }
        });
    }

    private void initUI() {
        mImage= (ImageView) findViewById(R.id.iv_image);
        mAddImage= (Button) findViewById(R.id.btn_add_image);
    }
    private void initListeners() {
        mAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePicDialog();
            }
        });
    }
    /*
    显示修改图片对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Sb_manage_changeavatar.this);
        builder.setTitle("添加图片");
        String[] items = { "选择本地照片", "拍照" };
        builder.setNegativeButton("取消", null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
                        startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "temp_image.jpg"));
                        // 将拍照所得的相片保存到SD卡根目录
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
        builder.show();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Sb_manage_changeavatar.RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    cutImage(tempUri); // 对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    cutImage(data.getData()); // 对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }
    /**
     * 裁剪图片方法实现
     */
    protected void cutImage(Uri uri) {
        if (uri == null) {
            Log.i("alanjet", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        //com.android.camera.action.CROP这个action是用来裁剪图片用的
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }
    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            mBitmap = extras.getParcelable("data");
            //这里图片是方形的，可以用一个工具类处理成圆形（很多头像都是圆形，这种工具类网上很多不再详述）
            mImage.setImageBitmap(mBitmap);//显示图片
            //在这个地方可以写上上传该图片到服务器的代码，后期将单独写一篇这方面的博客，敬请期待...
        }
    }


}
