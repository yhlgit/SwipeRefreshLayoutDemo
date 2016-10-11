package com.example.swiperefreshlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mLayout;
    private RecyclerView recyclerView;
    private List<String> datas = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
        initRecyclerView();
        initRefreshLayout();
    }

    private void initRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        myAdapter = new MyAdapter(datas);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        myAdapter.setOnItemClickLisener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position, String city) {
                Toast.makeText(MainActivity.this, "City:" + city + ",position:" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRefreshLayout() {
        mLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mLayout.setColorSchemeColors(android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light
        );
        mLayout.setDistanceToTriggerSync(100);
        mLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.item_press));
        mLayout.setSize(SwipeRefreshLayout.LARGE);
        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
for(int i=0;i<10;i++){
myAdapter.addData(i,"new City:"+i);
}
                        myAdapter.notifyItemRangeRemoved(0,10);
                        recyclerView.scrollToPosition(0);
                        mLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    private void initDatas() {
        datas.add("New York");
        datas.add("Boston");
        datas.add("Washington");
        datas.add("San Francisco");
        datas.add("California");
        datas.add("Houston");
        datas.add("Bei Jing");
        datas.add("Las Vegas");
        datas.add("Chicago");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                myAdapter.addData(0, "new City");
                break;
            case R.id.id_action_delete:
                myAdapter.remove(1);
                break;
            case R.id.id_action_gridview:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case R.id.id_action_listview:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_staggered:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                        StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
