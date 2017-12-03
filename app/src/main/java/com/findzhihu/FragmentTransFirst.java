package com.findzhihu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.findzhihu.backinterface.BackHandlerHelper;
import com.findzhihu.backinterface.FragmentBackHandler;

import static android.content.ContentValues.TAG;

/**
 * Created by fred on 2017/11/13.
 */

public class FragmentTransFirst extends Fragment implements FragmentBackHandler{
    private FragmentFirst fragmentFirst;
    private FragmentFirstDetail fragmentFirstDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trans_first, container, false);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentFirst = FragmentFirst.newInstance();
        fragmentFirstDetail = FragmentFirstDetail.newInstance();
        fragmentTransaction.add(R.id.content, fragmentFirst,"fragmentFirst" );
        fragmentTransaction.add(R.id.content, fragmentFirstDetail,"fragmentFirstDetail" );
        fragmentTransaction.show(fragmentFirst).hide(fragmentFirstDetail).commit();
        return view;
    }

    public static FragmentTransFirst newInstance(){
        FragmentTransFirst fragmentTransFirst = new FragmentTransFirst();

        return fragmentTransFirst;
    }

    public void transFragment(){
        getChildFragmentManager().beginTransaction().hide(fragmentFirst).show(fragmentFirstDetail).commit();
        if (getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).bringViewPagerToFront();
        }
    }

    public void showPagingFragment(){
        getChildFragmentManager().beginTransaction().hide(fragmentFirstDetail).show(fragmentFirst).commit();
        if (getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).bringViewPagerToBack();
        }
    }

    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }
}
