package com.findzhihu.anim;

import android.animation.ValueAnimator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;

public class BottomBehaviorAnim {

    private View mBottomView;
    private float mOriginalY;

    public BottomBehaviorAnim(View bottomView) {
        mBottomView = bottomView;
        mOriginalY = mBottomView.getY();
    }


    public void show() {
        ValueAnimator animator = ValueAnimator.ofFloat(mBottomView.getY(), mOriginalY);
        animator.setDuration(400);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mBottomView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }


    public void hide() {
        ValueAnimator animator = ValueAnimator.ofFloat(mBottomView.getY(), mOriginalY + mBottomView.getHeight());
        animator.setDuration(400);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mBottomView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }
}
