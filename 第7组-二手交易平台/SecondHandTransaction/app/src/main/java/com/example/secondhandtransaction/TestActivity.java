package com.example.secondhandtransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.AllCommodityAdapter;
import com.example.po.Commodity;
import com.example.database.CommodityDbHelper;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    ListView lvAllCommodity;
    List<Commodity> allCommodities = new ArrayList<>();

    CommodityDbHelper dbHelper;
    AllCommodityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        lvAllCommodity = findViewById(R.id.lv_all_commodity);
        dbHelper = new CommodityDbHelper(getApplicationContext(), CommodityDbHelper.DB_NAME, null, 1);
        adapter = new AllCommodityAdapter(getApplicationContext());
        allCommodities = dbHelper.readAllCommodities();
        adapter.setData(allCommodities);
        lvAllCommodity.setAdapter(adapter);
        final Bundle bundle = this.getIntent().getExtras();
        //刷新界面
        TextView tvRefresh = findViewById(R.id.re);
        tvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allCommodities = dbHelper.readAllCommodities();
                adapter.setData(allCommodities);
                lvAllCommodity.setAdapter(adapter);
            }
        });
    }
}