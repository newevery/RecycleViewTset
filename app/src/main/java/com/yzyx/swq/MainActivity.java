package com.yzyx.swq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Item> mItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化recycleview，添加数据
        recyclerView = (RecyclerView) findViewById(R.id.view);
        mItemList = new ArrayList<>();
        mItemList.add(new Item(1, "AAAAA", "OOOOOOOOOOOOOOO", true));
        mItemList.add(new Item(2, "AAAAA", "OOOOOOOOOOOOOOO", false));
        mItemList.add(new Item(4, "AAAAA", "OOOOOOOOOOOOOOO", true));
        mItemList.add(new Item(5, "AAAAA", "OOOOOOOOOOOOOOO", false));
        mItemList.add(new Item(6, "AAAAA", "OOOOOOOOOOOOOOO", true));
        mItemList.add(new Item(7, "AAAAA", "OOOOOOOOOOOOOOO", false));
        mItemList.add(new Item(8, "AAAAA", "OOOOOOOOOOOOOOO", true));
        mItemList.add(new Item(9, "AAAAA", "OOOOOOOOOOOOOOO", false));
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        MyAdapter adapter=new MyAdapter(this,mItemList);
        recyclerView.setAdapter(adapter);

    }
}
