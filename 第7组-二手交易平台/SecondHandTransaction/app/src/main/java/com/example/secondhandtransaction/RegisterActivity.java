package com.example.secondhandtransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.UserDbHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText UserId;
    private EditText Password;
    private EditText QrPassword;
    private EditText Email;
    private Button Bt_Register;
    private Button Bt_ReturnLogin;
    String userid=null;
    String password=null;
    String qrpassword=null;
    String email=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始化设置监听
        UserId=(EditText)findViewById(R.id.userid);
        Password=(EditText)findViewById(R.id.password);
        QrPassword=(EditText)findViewById(R.id.qrpassword);
        Email=(EditText)findViewById(R.id.email);
        Bt_Register=(Button)findViewById(R.id.register);
        Bt_ReturnLogin=(Button)findViewById(R.id.returnlogin);
        Bt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid=UserId.getText().toString();
                email=Email.getText().toString();
                password=Password.getText().toString();
                qrpassword=QrPassword.getText().toString();

                if(userid==null||userid.equals("")){
                    Toast.makeText(getApplicationContext(), "请输入手机号！", Toast.LENGTH_SHORT).show();
                }
                else if(password==null||password.equals("")){
                    Toast.makeText(getApplicationContext(), "请输入密码！", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(qrpassword)){
                    Toast.makeText(getApplicationContext(), "两次输入的密码不一致!", Toast.LENGTH_SHORT).show();
                }
                else{
                    checkUser(userid,password,email);
                }

            }


        });
        //返回登录页面
        Bt_ReturnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });
    }
    private void checkUser(String userid, String password,String email) {
        UserDbHelper dbhelper=new UserDbHelper(this);
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        try{
            String sql="SELECT * FROM users WHERE userId=?";
            Cursor cursor=db.rawQuery(sql,new String[]{userid});
            if(cursor.getCount()>0){
                Toast.makeText(getApplicationContext(), "手机号已存在！", Toast.LENGTH_SHORT).show();
            }
            else{
                ContentValues values = new ContentValues();
                //开始组装第一条数据   //账号userId，密码password，姓名name，邮箱email,地址address
                values.put("userId",userid);
                values.put("password",password);
                values.put("email",email);
                db.insert("users",null,values);//插入第一条数据
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
        }
    }


}