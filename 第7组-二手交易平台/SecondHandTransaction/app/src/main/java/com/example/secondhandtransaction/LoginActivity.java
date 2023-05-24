package com.example.secondhandtransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.database.UserDbHelper;


public class LoginActivity extends AppCompatActivity {
    private TextView RegisterPage;
    private EditText UserName;
    private EditText PassWord;
    private Button Btn_Login;
    private TextView ForgetPwd;
    private ImageView toLoginEmail;
    String username=null;
    String password=null;
    public static String only_userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserName=(EditText)findViewById(R.id.username);
        PassWord=(EditText)findViewById(R.id.password);
        Btn_Login=(Button)findViewById(R.id.login);
        ForgetPwd=(TextView)findViewById(R.id.forgetpwd);
        RegisterPage=(TextView)findViewById(R.id.registerpage);
        toLoginEmail=(ImageView)findViewById(R.id.tologin_email);
        only_userid="";

        toLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,LoginEmailActivity.class);
                startActivity(intent);
            }
        });
        RegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });
        ForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
        Btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得前端Ed控件的值
                username=UserName.getText().toString();
                password=PassWord.getText().toString();
                if(username.equals("")||username==null){
                    Toast.makeText(getApplicationContext(), "账号不能为空", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("")||password==null){
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else if(username.equals("admin") && password.equals("admin")){
                    Intent intent1=new Intent(LoginActivity.this,AdminActivity.class);
                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(),"正在登录管理员",Toast.LENGTH_SHORT).show();
                }
                else {
                    checkUser(username,password);
                }

            }
        });
    }
    private void checkUser(String user,String password){
        UserDbHelper dbhelper = new UserDbHelper(this);
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        only_userid=user;
        try{
//            String sql1="SELECT * FROM users";
//            Cursor cursor1=db.rawQuery(sql1,new String[]{username,password});

            String sql="SELECT * FROM users WHERE userId=? and password=?";
            Cursor cursor=db.rawQuery(sql,new String[]{username,password});
            if(cursor.getCount()==0){

                Toast.makeText(getApplicationContext(), "账号密码错误", Toast.LENGTH_SHORT).show();
            }
            else{
                //Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,IndexActivity.class);
                System.out.println("用户名:"+only_userid);
                startActivity(intent);

            }
            cursor.close();
            db.close();
        }catch (SQLiteException e){
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}