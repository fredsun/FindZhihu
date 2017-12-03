package com.findzhihu.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by fred on 2017/11/13.
 */

public class NonSwipeableViewPager extends ViewPager {

    private boolean noScroll = false;
    private boolean intercept;
    int xBefore = -1;
    int yBefore = -1;
    public NonSwipeableViewPager(@NonNull Context context) {
        super(context);
    }

    public NonSwipeableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    //默认返回true消费事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return noScroll ;
    }

    //默认返回true消费事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return noScroll ;
    }

    //去除切换效果
    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item,false);
    }


}
