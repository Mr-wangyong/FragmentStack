package com.cinema.fragmentstacklibrary;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class RootActivity extends AppCompatActivity {

    public StackManager manager;
    private KeyCallBack callBack;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.setId(R.id.framLayoutId);
        setContentView(frameLayout);
    }

    public void setRootFragment(RootFragment fragment) {
        manager.setFragment(fragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                manager.onBackPressed();
                break;
            default:
                if (callBack!=null){
                    return callBack.onKeyDown(keyCode,event);
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置键盘点击回调
     * @param callBack 回调
     */
    public void setKeyCallBack(KeyCallBack callBack){
        this.callBack = callBack;
    }



}
