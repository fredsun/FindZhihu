package com.findzhihu.anim;

import android.animation.ValueAnimator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;

public class TitleBehaviorAnim {

    private View mHeadView;


    public TitleBehaviorAnim(View headView) {
        mHeadView = headView;
    }


    public void show() {
        ValueAnimator animator = ValueAnimator.ofFloat(mHeadView.getY(), 0);
        animator.setDuration(400);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mHeadView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }


    public void hide() {
        ValueAnimator animator = ValueAnimator.ofFloat(mHeadView.getY(), -mHeadView.getHeight());
        animator.setDuration(400);
        animator.setInterpolator(new LinearOutSlowInInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mHeadView.setY((Float) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }
}
