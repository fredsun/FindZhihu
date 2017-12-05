package com.findzhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.findzhihu.R;

/**
 * Created by fred on 2017/11/13.
 */

public class CommonFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        return view;
    }

    public static CommonFragment newInstance(){
        CommonFragment FragmentFourth = new CommonFragment();
        return FragmentFourth;
    }
}
