package com.example.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderDbHelper extends SQLiteOpenHelper{

    public static final String dbname="myorder";
    public OrderDbHelper(Context context) { super(context, "dbname", null, 1);
    }
    //创建用户表
    @Override
    public void onCreate(SQLiteDatabase db) {
        //账号/手机号userId，密码passWord，姓名name，邮箱email，地址address
        db.execSQL("create table if not exists orders" +
                "(orderid integer primary key autoincrement," +
                "ordertitle text," +
                "ordercategory text," +
                "orderprice float," +
                "orderdescription text," +
                "orderpicture blob," +
                "orderstuId text)");
        //商品编号id，发布者账号userId，标题title，类别kind，内容info，价格price，图片image
//        db.execSQL("create table if not exists iteminfo" +
//                "(id integer primary key  AUTOINCREMENT," +
//                "userId varchar(100)," +
//                "title varchar(20)," +
//                "kind varchar(100)," +
//                "info varchar(100)," +
//                "price varchar(100)," +
//                "image blob," +
//                "time DATETIME," +
//                "contact varchar(50))");
        //db.execSQL("create table iteminfo (id integer primary key AUTOINCREMENT,userId varcher(20),title varchar(20),kind varchar(20),info varchar(20),price varchar(20),image blob,time DATETIME,contact varchar(20))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

