package com.stack.simple.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinema.fragmentstacklibrary.RootFragment;
import com.mr.wang.frametest.R;

/**
 * User: chengwangyong(chengwangyong@vcinema.com)
 * Date: 2016-01-19
 * Time: 10:02
 */
public class Fragment3 extends RootFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment3, null);
        inflate.findViewById(R.id.fragment1).setOnClickListener(this);
        inflate.findViewById(R.id.fragment2).setOnClickListener(this);
        inflate.findViewById(R.id.fragment4).setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment1:
                open(new Fragment1());
                break;
            case R.id.fragment2:
                open(new Fragment2());
                break;
            case R.id.fragment4:
                open(new Fragment4());
                break;
        }
    }
}
