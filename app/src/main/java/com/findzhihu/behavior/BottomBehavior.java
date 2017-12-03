package com.findzhihu.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.findzhihu.anim.BottomBehaviorAnim;


/**
 * 仿知乎底部behavior
 * Created by fred on 2017/11/6.
 */

public class BottomBehavior extends CoordinatorLayout.Behavior<View> {
    final String tag = "BottomBehavior";

    public onCanScrollCallback mOnCanScrollCallback;
    private boolean isHide;
    protected BottomBehaviorAnim mBottomAnim;
    private boolean isInit = true;
    int dyConsumedTotal;

    public BottomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public void setOnCanScrollCallback(onCanScrollCallback mOnCanScrollCallback) {
        this.mOnCanScrollCallback = mOnCanScrollCallback;

    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {


        if (isInit) {
            mBottomAnim = new BottomBehaviorAnim(child);
            isInit = false;
        }


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
        if (mOnCanScrollCallback.callbackCanScroll()){
            dyConsumedTotal += dyConsumed;
            if (Math.abs(dyConsumedTotal) > 100)
            {
                if (dyConsumed <0 && isHide) {
                    mBottomAnim.show();
                    isHide = false;
                }
                if (dyConsumed >0 &&!isHide) {
                    mBottomAnim.hide();
                    isHide = true;
                }
            }
            if (Math.abs(dyConsumed)<10){
                dyConsumedTotal = 0;
            }
        }

    }

    public interface onCanScrollCallback{
        boolean callbackCanScroll();
    }

    public void showBottom(){
        mBottomAnim.show();
        isHide = false;
    }


    
}
