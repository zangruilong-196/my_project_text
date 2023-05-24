package com.example.fragmentclass;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.database.UserDbHelper;
import com.example.secondhandtransaction.LoginActivity;
import com.example.secondhandtransaction.MainActivity;
import com.example.secondhandtransaction.MyCollectionActivity;
import com.example.secondhandtransaction.MyPublishActivity;
import com.example.secondhandtransaction.R;
import com.example.secondhandtransaction.RegisterActivity;
import com.example.secondhandtransaction.ReleaseActivity;
import com.example.secondhandtransaction.UpdatePwdActivity;
import com.example.secondhandtransaction.UserMsgActivity;

import static com.example.secondhandtransaction.LoginActivity.only_userid;
import static com.example.secondhandtransaction.LoginEmailActivity.only_emailid;


public class MyFragment extends Fragment implements View.OnClickListener {
    private ImageView myshowimage;
    private TextView myname;
    byte[] imagedata;
    Bitmap imagebm;
    String userid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view2=null;
        if (view2==null){
            view2=inflater.inflate(R.layout.activity_my,container,false);
            RelativeLayout relativeLayout11=(RelativeLayout)view2.findViewById(R.id.my_re1);
            RelativeLayout relativeLayout=(RelativeLayout)view2.findViewById(R.id.my_re3);
            RelativeLayout relativeLayout1=(RelativeLayout)view2.findViewById(R.id.my_re2);
            RelativeLayout relativeLayout4=(RelativeLayout)view2.findViewById(R.id.my_re4);
            RelativeLayout relativeLayout2=(RelativeLayout)view2.findViewById(R.id.my_re5);
            //使用邮箱登录查找到用户ID（账号）
            userid=only_userid;
            if (only_userid.equals("")) {
                UserDbHelper dbhelper1 = new UserDbHelper(getActivity());
                SQLiteDatabase db1 = dbhelper1.getReadableDatabase();
                String sql1 = "SELECT * FROM users WHERE email=?";
                Cursor cursor1 = db1.rawQuery(sql1, new String[]{only_emailid});
                if (cursor1.getCount() == 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "用户不存在！", Toast.LENGTH_SHORT).show();
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
//            if (only_userid.equals("")){
//                UserDbHelper dbhelper1 = new UserDbHelper(getActivity());
//                SQLiteDatabase db1 = dbhelper1.getReadableDatabase();
//                String sql = "SELECT * FROM users WHERE email=?";
//                Cursor cursor1 = db1.rawQuery(sql, new String[]{only_emailid});
//                if (cursor1.moveToFirst()) {
//                    userid = cursor1.getString(cursor1.getColumnIndex("catId"));
//                }
//            }


            myshowimage=(ImageView)view2.findViewById(R.id.my_show_img);
            myname=(TextView) view2.findViewById(R.id.my_name);
            myname.setText("欢迎您~  "+userid);
            relativeLayout11.setOnClickListener(this);
            relativeLayout.setOnClickListener(this);
            relativeLayout1.setOnClickListener(this);
            relativeLayout2.setOnClickListener(this);
            relativeLayout4.setOnClickListener(this);
            UserDbHelper dbhelper = new UserDbHelper(getActivity());
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            String sql = "SELECT * FROM users WHERE userId=?";
            Cursor cursor = db.rawQuery(sql, new String[]{userid});

            if (cursor.moveToFirst()) {
                imagedata = cursor.getBlob(5);
                try {
                    //if (!imagedata.equals("") || imagedata!=null){
                        imagebm = BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
                        myshowimage.setImageBitmap(imagebm);
                    //}
                }catch (Exception e){
                    e.printStackTrace();
                }


//                try {
//                    imagebm = BitmapFactory.decodeByteArray(imagedata, 0, imagedata.length);
//                    myshowimage.setImageBitmap(imagebm);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }



            }
        }

        return  view2;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_re1:
                Intent intent =new Intent(getActivity(), UserMsgActivity.class);
                startActivity(intent);
                break;
            case R.id.my_re2:
                Intent intent1 =new Intent(getActivity(), UpdatePwdActivity.class);
                startActivity(intent1);
                break;
            case R.id.my_re3:
                Intent intent2 =new Intent(getActivity(), MyPublishActivity.class);
                startActivity(intent2);
                break;
            case R.id.my_re4:
                Intent intent4 =new Intent(getActivity(), MyCollectionActivity.class);
                startActivity(intent4);
                break;
            case R.id.my_re5:
                getActivity().finish();
//                Intent intent5 =new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent5);
                break;
        }
    }

}
