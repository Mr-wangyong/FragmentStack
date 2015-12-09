package com.mr.wang.frametest;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;



public class MainActivity extends BaseActivity {

    public KeyCallBack listener;

    @Override
    protected void initView() {
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.setId(R.id.framLayoutId);
        setContentView(frameLayout);
    }

    @Override
    protected void initDate() {
        StackManager manager=new StackManager(this);
        BasePresenterFragment  fragment = new HomeFragment();
        manager.setFragment(fragment);
        Log.i("TAG", "承载Fragment的Activity基类");
    }



}
