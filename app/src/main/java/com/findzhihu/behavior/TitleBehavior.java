package com.findzhihu.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.findzhihu.anim.TitleBehaviorAnim;


/**
 * 仿知乎底部behavior
 * Created by fred on 2017/11/6.
 */

public class TitleBehavior extends CoordinatorLayout.Behavior<View> {
    final String tag = "BottomBehavior";

    public onCanScrollCallback mOnCanScrollCallback;
    private boolean isHide;
    protected TitleBehaviorAnim mTitleAnim;
    private boolean isInit = true;


    public TitleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public void setOnCanScrollCallback(onCanScrollCallback mOnCanScrollCallback) {
        this.mOnCanScrollCallback = mOnCanScrollCallback;

    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.i(tag, "name"+ dependency.getClass().getName());
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {


        if (isInit) {
            mTitleAnim = new TitleBehaviorAnim(child);
            isInit = false;
        }

        Log.i(tag, "name"+ directTargetChild.getClass().getName());

//        //RecyclerView的高度超过4000才值得启动behavior
//        if ((((RecyclerView)directTargetChild).computeVerticalScrollRange())>4000){
//            return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
//        }else {
//            return false;
//        }
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
//        if (mOnCanScrollCallback.callbackCanScroll()) {
            if (dyConsumed > 0 && Math.abs(dyConsumed) > 80) {
                //快速下拉, 隐藏
                if (!isHide) {
                    mTitleAnim.hide();
                    isHide = true;
                    Log.i(tag, "hide");
                }

            }
            if (dyConsumed < 0 && Math.abs(dyConsumed) > 80) {
                //快速上拉, 显示
                if (isHide) {
                    mTitleAnim.show();
                    isHide = false;
                    Log.i(tag, "show");
                }
            }

            if (!target.canScrollVertically(1)) {
                //如果不能下拉了
                if (isHide) {
                    mTitleAnim.show();
                    isHide = false;
                    Log.i(tag, "bottom show");
                }
            }

            if (!target.canScrollVertically(-1)) {
                //如果不能上滑了
                if (isHide) {
                    mTitleAnim.show();
                    isHide = false;
                    Log.i(tag, "bottom show");
                }
            }
//        }
    }
    

    public interface onCanScrollCallback{
        boolean callbackCanScroll();
    }
    
}
