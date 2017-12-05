package com.findzhihu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.findzhihu.MainActivity;
import com.findzhihu.R;
import com.findzhihu.backinterface.BackHandlerHelper;
import com.findzhihu.backinterface.FragmentBackHandler;

/**
 * Created by fred on 2017/11/13.
 */

public class FirstTransFragment extends Fragment implements FragmentBackHandler{
    private FirstListFragment firstListFragment;
    private FirstDetailFragment firstDetailFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trans_first, container, false);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        firstListFragment = FirstListFragment.newInstance();
        firstDetailFragment = FirstDetailFragment.newInstance();
        fragmentTransaction.add(R.id.content, firstListFragment,"firstListFragment" );
        fragmentTransaction.add(R.id.content, firstDetailFragment,"firstDetailFragment" );
        fragmentTransaction.show(firstListFragment).hide(firstDetailFragment).commit();
        return view;
    }

    public static FirstTransFragment newInstance(){
        FirstTransFragment firstTransFragment = new FirstTransFragment();
        return firstTransFragment;
    }

    /*
    * @Description: 切换 ListFragment 为 DetailFragment
    *  先把toolbar从可能的 behavior 隐藏里放出来
    *  再把 MainActivity 的 TabLayout GONE 掉后再从可能的 behavior 隐藏里出来
    *  最后hide, show, commit.
    * @author: fred
    * @date: 2017/12/4
    * @attention:
    */
    public void showDetailFragment(){
        firstListFragment.showToolBar();
        if (getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).bringViewPagerToFront();
        }
        getChildFragmentManager().beginTransaction().hide(firstListFragment).show(firstDetailFragment).commit();
    }

    /*
    * @Description: 切换 DetailFragment 为 ListFragment
    *  先把TabLayout VISIBLE
    *  再hide, show, commit
    * @author: fred
    * @date: 2017/12/4
    * @attention:
    */
    public void showListFragment(){
        if (getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).bringViewPagerToBack();
        }
        getChildFragmentManager().beginTransaction().hide(firstDetailFragment).show(firstListFragment).commit();
    }

    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }
}
