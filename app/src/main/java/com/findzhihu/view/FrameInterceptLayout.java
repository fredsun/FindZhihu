package com.findzhihu.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * 仿知乎命名的FrameLayout
 * 通过 dispatchTouchEvent 传递 touch
 * Created by fred on 2017/11/24.
 */

public class FrameInterceptLayout extends FrameLayout {
    String tag = "FrameInterceptLayout";
    float xBefore;
    float yBefore;
    float xDown;
    float yDown;
    DispatchTouchListener mDispatchTouchListener;
    public FrameInterceptLayout(@NonNull Context context) {
        super(context);
    }

    public FrameInterceptLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FrameInterceptLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FrameInterceptLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /*
    * @Description: 通过dispatchTouchEvent分发事件
    * @author: fred
    * @date: 2017/11/29
    */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            xBefore = event.getX();
            yBefore = event.getY();
            xDown = event.getX();
            yDown = event.getY();
            mDispatchTouchListener.onDownTouchEvent(event);
        }
        //速度小于10, 初始记录点刷新
        if (event.getAction() == MotionEvent.ACTION_MOVE){

            if (Math.abs((event.getX() - xBefore))<10){
                xDown = xBefore;
            }
            if (Math.abs((event.getY() - yBefore))<10){
                yDown = yBefore;
            }

            mDispatchTouchListener.onMoveTouchEvent(event, (int)(event.getX() - xBefore), (int)(event.getY() - yBefore), (int)(event.getX() - xDown), (int)(event.getY() - yDown));
            xBefore = event.getX();
            yBefore = event.getY();

        }
        return super.dispatchTouchEvent(event);
    }

    public void setDispatchTouchListener(DispatchTouchListener DispatchTouchListener){
        this.mDispatchTouchListener = DispatchTouchListener;
    }
    public interface DispatchTouchListener{
        void onDownTouchEvent(MotionEvent event);
        void onMoveTouchEvent(MotionEvent event, int offsetX, int offsetY, int offsetDownX, int offsetDownY);
    }


}
