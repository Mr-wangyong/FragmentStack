package com.cinema.fragmentstacklibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * extends this Activity to manage multiple fragment
 * User: chengwangyong(cwy545177162@163.com)
 * Date: 2016-01-19
 * Time: 18:32
 */
public abstract class RootActivity extends AppCompatActivity {

    public StackManager manager;
    private KeyCallBack callBack;


    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.setId(R.id.framLayoutId);
        setContentView(frameLayout);
        RootFragment fragment = getRootFragment();
        manager = new StackManager(this);
        manager.setFragment(fragment);
        onCreateNow(savedInstanceState);
    }

    /**
     * 设置最底层的fragment
     *
     * @return fragment
     */
    protected abstract RootFragment getRootFragment();

    /**
     * 重写的onCreate方法
     *
     * @param savedInstanceState savedInstanceState
     */
    public void onCreateNow(Bundle savedInstanceState) {

    }


    @Override
    public final boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                manager.onBackPressed();
                return true;
            default:
                if (callBack != null) {
                    return callBack.onKeyDown(keyCode, event);
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置键盘点击回调
     *
     * @param callBack 回调
     */
    public void setKeyCallBack(KeyCallBack callBack) {
        this.callBack = callBack;
    }


}
