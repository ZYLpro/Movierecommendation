package sidebar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.movierecommendation.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import global_variable.Myapplication;
import sidebar.geographical_choice.City;
import sidebar.geographical_choice.District;
import sidebar.geographical_choice.Province;

public class Sb_manage_changeavatar extends Activity {
    private ImageView mImage;
    private Button mAddImage;
    private Bitmap mBitmap;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    protected static Uri tempUri;
    private static final int CROP_SMALL_PICTURE = 2;

    private Spinner spinner1,spinner2,spinner3;
    private Province province = null;
    private List<Province> list = new ArrayList<Province>();
    ArrayAdapter<Province> arrayAdapter1;
    ArrayAdapter<City> arrayAdapter2;
    ArrayAdapter<District>arrayAdapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_manage_message);
        initUI();
        initListeners();

        Button button = findViewById(R.id.确认修改);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.昵称);
                String name = editText.getText().toString();
                /*Myapplication myapp = (Myapplication)Sb_manage_changeavatar.this.getApplication();
                myapp.setname(name);*/
                Sb_manage_changeavatar.this.finish();
            }
        });

        spinner3 = (Spinner)findViewById(R.id.s3);
        spinner2 = (Spinner)findViewById(R.id.s2);
        spinner1 = (Spinner)findViewById(R.id.s1);
        list= parser();
        arrayAdapter1 = new ArrayAdapter<Province>(Sb_manage_changeavatar.this,R.layout.support_simple_spinner_dropdown_item,list);
        arrayAdapter2 = new ArrayAdapter<City>(Sb_manage_changeavatar.this,R.layout.support_simple_spinner_dropdown_item,list.get(0).getCitys());
        arrayAdapter3 = new ArrayAdapter<District>(Sb_manage_changeavatar.this,R.layout.support_simple_spinner_dropdown_item,list.get(0).getCitys().get(0).getDistricts());
        spinner1.setAdapter(arrayAdapter1);
        spinner1.setSelection(0, true);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setSelection(0, true);
        spinner3.setAdapter(arrayAdapter3);
        spinner3.setSelection(0, true);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                province = list.get(position);
                arrayAdapter2 = new ArrayAdapter<City>(Sb_manage_changeavatar.this, R.layout.support_simple_spinner_dropdown_item, list.get(position).getCitys());
                spinner2.setAdapter(arrayAdapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrayAdapter3 = new ArrayAdapter<District>(Sb_manage_changeavatar.this,R.layout.support_simple_spinner_dropdown_item,province.getCitys().get(position).getDistricts());
                spinner3.setAdapter(arrayAdapter3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    public List<Province> parser(){
        List<Province>list =null;
        Province province = null;

        List<City>cities = null;
        City city = null;

        List<District>districts = null;
        District district = null;

        // 创建解析器，并制定解析的xml文件
        XmlResourceParser parser = getResources().getXml(R.xml.actions);
        try{
            int type = parser.getEventType();
            while(type!=1) {
                String tag = parser.getName();//获得标签名
                switch (type) {
                    case XmlResourceParser.START_DOCUMENT:
                        list = new ArrayList<Province>();
                        break;
                    case XmlResourceParser.START_TAG:
                        if ("p".equals(tag)) {
                            province=new Province();
                            cities = new ArrayList<City>();
                            int n =parser.getAttributeCount();
                            for(int i=0 ;i<n;i++){
                                //获得属性的名和值
                                String name = parser.getAttributeName(i);
                                String value = parser.getAttributeValue(i);
                                if("p_id".equals(name)){
                                    province.setId(value);
                                }
                            }
                        }
                        if ("pn".equals(tag)){//省名字
                            province.setName(parser.nextText());
                        }
                        if ("c".equals(tag)){//城市
                            city = new City();
                            districts = new ArrayList<District>();
                            int n =parser.getAttributeCount();
                            for(int i=0 ;i<n;i++){
                                String name = parser.getAttributeName(i);
                                String value = parser.getAttributeValue(i);
                                if("c_id".equals(name)){
                                    city.setId(value);
                                }
                            }
                        }
                        if ("cn".equals(tag)){
                            city.setName(parser.nextText());
                        }
                        if ("d".equals(tag)){
                            district = new District();
                            int n =parser.getAttributeCount();
                            for(int i=0 ;i<n;i++){
                                String name = parser.getAttributeName(i);
                                String value = parser.getAttributeValue(i);
                                if("d_id".equals(name)){
                                    district.setId(value);
                                }
                            }
                            district.setName(parser.nextText());
                            districts.add(district);
                        }
                        break;
                    case XmlResourceParser.END_TAG:
                        if ("c".equals(tag)){
                            city.setDistricts(districts);
                            cities.add(city);
                        }
                        if("p".equals(tag)){
                            province.setCitys(cities);
                            list.add(province);
                        }
                        break;
                    default:
                        break;
                }
                type = parser.next();
            }
        }catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } */
        catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}
