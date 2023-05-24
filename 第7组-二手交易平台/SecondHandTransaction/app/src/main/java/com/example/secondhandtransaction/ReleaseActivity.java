package com.example.secondhandtransaction;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.UserDbHelper;
import com.example.po.Commodity;
import com.example.database.CommodityDbHelper;


import java.io.ByteArrayOutputStream;

//将用户唯一ID导入
import static com.example.secondhandtransaction.LoginActivity.only_userid;
import static com.example.secondhandtransaction.LoginEmailActivity.only_emailid;

//发布商品页面
public class ReleaseActivity extends AppCompatActivity {
    TextView tvStuId;
    ImageButton ivPhoto;
    EditText etTitle,etPrice,etPhone,etDescription;
    Spinner spType;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        //取出学号
        //tvStuId = findViewById(R.id.tv_student_id);
        //tvStuId.setText(this.getIntent().getStringExtra("user_id"));
//        Button btnBack = findViewById(R.id.btn_back);
//        //返回按钮点击事件
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        userid=only_userid;
        if (only_userid.equals("")) {
            UserDbHelper dbhelper1 = new UserDbHelper(this);
            SQLiteDatabase db1 = dbhelper1.getReadableDatabase();
            String sql1 = "SELECT * FROM users WHERE email=?";
            Cursor cursor1 = db1.rawQuery(sql1, new String[]{only_emailid});
            if (cursor1.getCount() == 0) {
                Toast.makeText(getApplicationContext(), "用户不存在！", Toast.LENGTH_SHORT).show();
            } else {
                if (cursor1.moveToFirst()) {
                    try {
                        userid = cursor1.getString(cursor1.getColumnIndex("userId"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        ivPhoto = findViewById(R.id.iv_photo);
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,1);
            }
        });
        etTitle = findViewById(R.id.et_title);
        etPrice = findViewById(R.id.et_price);
        etPhone = findViewById(R.id.et_phone);
        etDescription = findViewById(R.id.et_description);
        spType = findViewById(R.id.spn_type);
        Button btnPublish = findViewById(R.id.btn_publish);
        //发布按钮点击事件
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先检查合法性
                if(CheckInput()) {
                    CommodityDbHelper dbHelper = new CommodityDbHelper(getApplicationContext(), CommodityDbHelper.DB_NAME, null, 1);
                    Commodity commodity = new Commodity();
                    //把图片先转化成bitmap格式
                    BitmapDrawable drawable = (BitmapDrawable) ivPhoto.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    //二进制数组输出流
                    ByteArrayOutputStream byStream = new ByteArrayOutputStream();
                    //将图片压缩成质量为100的PNG格式图片
                    bitmap.compress(Bitmap.CompressFormat.PNG, 40, byStream);
                    //把输出流转换为二进制数组
                    byte[] byteArray = byStream.toByteArray();
                    commodity.setPicture(byteArray);
                    commodity.setTitle(etTitle.getText().toString());
                    commodity.setCategory(spType.getSelectedItem().toString());
                    commodity.setPrice(Float.parseFloat(etPrice.getText().toString()));
                    commodity.setPhone(etPhone.getText().toString());
                    commodity.setDescription(etDescription.getText().toString());
                    commodity.setStuId(userid);
                    if (dbHelper.AddCommodity(commodity)) {
                        Toast.makeText(getApplicationContext(), "商品信息发布成功!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "商品信息发布失败!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //从相册返回的数据
            if (data != null) {
                //得到图片的全路径
                Uri uri = data.getData();
                ivPhoto.setImageURI(uri);
            }
        }
    }

    /**
     * 检查输入是否合法
     */
    public boolean CheckInput() {
        String title = etTitle.getText().toString();
        String price = etPrice.getText().toString();
        String type = spType.getSelectedItem().toString();
        String phone = etPhone.getText().toString();
        String description = etDescription.getText().toString();
        if (title.trim().equals("")) {
            Toast.makeText(this,"商品标题不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (price.trim().equals("")) {
            Toast.makeText(this,"商品价格不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (type.trim().equals("请选择类别")) {
            Toast.makeText(this,"商品类别未选择!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phone.trim().equals("")) {
            Toast.makeText(this,"手机号码不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (description.trim().equals("")) {
            Toast.makeText(this,"商品描述不能为空!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


//        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
//
//        dbHelper = new DatabaseHelper(this);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        String[] ctype = new String[]{"生活用品", "学习用品", "电子产品", "体育用品"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
//        Spinner spinner = (Spinner) super.findViewById(R.id.m1_style);
//        spinner.setAdapter(adapter);
//        sp = (Spinner) findViewById(R.id.m1_style);
//        final String kind = (String) sp.getSelectedItem();
//
//        imageButton=(ImageButton)findViewById(R.id.m1_image);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(ReleaseActivity.this,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(ReleaseActivity.this, new
//                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                } else {
//                    //打开系统相册
//                    Intent intent = new Intent(Intent.ACTION_PICK,
//                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, 1);
//                }
//
//            }
//        });
//
//        Button fabu=(Button)findViewById(R.id.fabu);
//        fabu.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                EditText title=(EditText)findViewById(R.id.m1_title);
//                EditText price=(EditText)findViewById(R.id.m1_price);
//                EditText phone=(EditText)findViewById(R.id.m1_phone);
//                EditText nr=(EditText)findViewById(R.id.m1_nr);
//                Date curDate = new Date(System.currentTimeMillis());
//                String time = formatter.format(curDate);
//                ContentValues values =new ContentValues();
//                values.put("userId",only_userid);
//                values.put("title",title.getText().toString());
//                values.put("kind", kind);
//                values.put("info",nr.getText().toString());
//                values.put("price",price.getText().toString());
//                values.put("image",image);
//                values.put("time",time);
//                values.put("contact",phone.getText().toString());
//                db.insert("iteminfo",null,values);
//                //db.execSQL("insert into iteminfo (userId,title,kind,info,price,image,time,contact) values (?,?,?,?,?,?,?,?)",new Object[] {only_userid,title.getText().toString(),kind,nr.getText().toString(),price.getText().toString(),image,time,phone.getText().toString()});
//                db.close();
//                Intent intent=new Intent(ReleaseActivity.this,IndexActivity.class);
//                Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//
//
//            }
//        });
//
//
//
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //获取图片路径
//        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumns = {MediaStore.Images.Media.DATA};
//            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
//            c.moveToFirst();
//            int columnIndex = c.getColumnIndex(filePathColumns[0]);
//            String imagePath = c.getString(columnIndex);
//            showImage(imagePath);
//            c.close();
//        }
//    }
//
//    //加载图片
//    private void showImage(String imaePath) {
//        Bitmap bm = BitmapFactory.decodeFile(imaePath);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        image = baos.toByteArray();
//        imageButton.setImageBitmap(bm);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                } else {
//                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//        }
//    }

}