package com.example.swiperefreshlayoutdemo;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 杨宏亮 on 2016/9/12.
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    /**
     *设置Item的边距
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


    }
    /**
     *渲染Item后调用此方法
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     *渲染Item前调用此方法
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }


}
