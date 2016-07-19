package com.mrwang.stacklibrary.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrwang.stacklibrary.R;
import com.mrwang.stacklibrary.RootFragment;

/**
 * Created by dell on 2016/7/19.
 */
public class RootFragmentIml extends RootFragment {
    //for test
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_main, null);
        return inflate;
    }
}
