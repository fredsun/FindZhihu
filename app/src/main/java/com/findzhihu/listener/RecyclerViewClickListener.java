package com.findzhihu.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * RecyclerView的点击监听
 * 根据谷歌提供的 RecyclerView.OnItemTouchListener 和 手势监听 GestureDetector
 * Created by fred on 2017/11/9.
 */

public class RecyclerViewClickListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector mGestureDetector;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public RecyclerViewClickListener(Context context, final RecyclerView recyclerView, final OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener(){

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        View childViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childViewUnder != null && listener != null){
                            mListener.onItemClick(childViewUnder, recyclerView.getChildLayoutPosition(childViewUnder));
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View childViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childViewUnder != null && listener != null){
                            mListener.onItemLongClick(childViewUnder, recyclerView.getChildLayoutPosition(childViewUnder));
                        }
                    }
                });

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (mGestureDetector.onTouchEvent(e)){
            return true;
        }else
        return false;

    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
