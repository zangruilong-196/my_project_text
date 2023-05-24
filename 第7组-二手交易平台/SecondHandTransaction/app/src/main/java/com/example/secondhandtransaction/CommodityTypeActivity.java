package com.example.secondhandtransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.AllCommodityAdapter;
import com.example.database.CommodityDbHelper;
import com.example.po.Commodity;

import java.util.LinkedList;
import java.util.List;

/**
 * 创建时间: 2021/12/5 13:21
 * 作者: zjx
 * 描述: 商品分类
*/
public class CommodityTypeActivity extends AppCompatActivity {
    TextView tvCommodityType;
    ListView lvCommodityType;
    //ListView lvAllCommodity;

    List<Commodity> commodities = new LinkedList<>();
    String stuNum =null;
    CommodityDbHelper dbHelper;
    AllCommodityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_type);
        //lvAllCommodity=findViewById(R.id.lv_all_commodity);
        tvCommodityType = findViewById(R.id.tv_type);
        lvCommodityType = findViewById(R.id.list_commodity);
        dbHelper = new CommodityDbHelper(getApplicationContext(),CommodityDbHelper.DB_NAME,null,1);
        adapter = new AllCommodityAdapter(getApplicationContext());

        //返回
        ImageView imageView = findViewById(R.id.type_return);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //为每一个item设置点击事件
//        lvAllCommodity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Commodity commodity = (Commodity) lvAllCommodity.getAdapter().getItem(position);
//                Bundle bundle1 = new Bundle();
//                bundle1.putInt("position",position);
//                bundle1.putByteArray("picture",commodity.getPicture());
//                bundle1.putString("title",commodity.getTitle());
//                bundle1.putString("description",commodity.getDescription());
//                bundle1.putFloat("price",commodity.getPrice());
//                bundle1.putString("phone",commodity.getPhone());
//                bundle1.putString("stuId",stuNum);
//                Intent intent = new Intent(CommodityTypeActivity.this, DetailInfoActivity.class);
//                intent.putExtras(bundle1);
//                startActivity(intent);
//            }
//        });

        //根据不同的状态显示不同的界面
        int status = this.getIntent().getIntExtra("status",0);
        if(status == 1) {
            tvCommodityType.setText("学习用品");
        }else if(status == 2) {
            tvCommodityType.setText("电子产品");
        }else if(status == 3) {
            tvCommodityType.setText("生活用品");
        }else if(status == 4) {
            tvCommodityType.setText("体育用品");
        }
        //根据不同类别显示不同的商品信息
        commodities = dbHelper.readCommodityType(tvCommodityType.getText().toString());
        adapter.setData(commodities);
        lvCommodityType.setAdapter(adapter);
    }
}