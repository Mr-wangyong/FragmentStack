package com.mr.wang.frametest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class RootActivity extends AppCompatActivity {

    private StackManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.setId(R.id.framLayoutId);
        setContentView(frameLayout);
    }

    public void setRootFragment(BaseFragment fragment) {
        manager.setFragment(fragment);
    }

    public void open(BaseFragment fragment){

    }

    public void open(BaseFragment fragment,Bundle bundle){

    }

    public void open(BaseFragment fragment,Bundle bundle,int stackMode){

    }


}
