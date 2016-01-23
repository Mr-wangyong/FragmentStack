package com.mrwang.simple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrwang.stacklibrary.FragmentStack;
import com.mrwang.stacklibrary.RootFragment;


/**
 * User: chengwangyong(chengwangyong@vcinema.com)
 * Date: 2016-01-19
 * Time: 10:02
 */
public class HomeFragment extends RootFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_layout, null);
        inflate.findViewById(R.id.standard).setOnClickListener(this);
        inflate.findViewById(R.id.single_top).setOnClickListener(this);
        inflate.findViewById(R.id.single_task).setOnClickListener(this);
        inflate.findViewById(R.id.single_instance).setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.standard:
                open(new Fragment1(), null, FragmentStack.STANDARD);
                break;
            case R.id.single_top:
                open(new Fragment1(), null, FragmentStack.SINGLE_TOP);

                break;
            case R.id.single_task:
                open(new Fragment1(), null, FragmentStack.SINGLE_TASK);
                break;
            case R.id.single_instance:
                open(new Fragment1(), null, FragmentStack.SINGLE_INSTANCE);
                break;
        }
    }
}

