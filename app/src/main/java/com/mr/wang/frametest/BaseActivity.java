package com.mr.wang.frametest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public abstract class BaseActivity extends AppCompatActivity implements Handler.Callback, View.OnClickListener {

    // 获取到前台进程的Activity
    public static Activity mForegroundActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            //UIUtils.getContext().setCallback(this);
            //UIUtils.getContext().unDestroyActivityList.add(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        initView();
        initDate();
    }


    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化数据 此方法一定是可以复用的 刷新数据调用即可
     */
    protected abstract void initDate();


    @Override
    protected void onResume() {
        super.onResume();
        mForegroundActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mForegroundActivity = null;
    }

    public static Activity getForegroundActivity() {
        return mForegroundActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //UIUtils.getContext().setCallback(null);
        //UIUtils.getContext().unDestroyActivityList.remove(this);
    }

    /**
     * 如需使用handler 只需要重写此方法即可
     */
    @Override
    public boolean handleMessage(Message msg) {
        return true;
    }

    /**
     * 打开Activity
     *
     * @param pClass class
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 打开Activity并传递参数
     *
     * @param pClass  class
     * @param pBundle bundle
     */
    public void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 设置控件点击事件
     *
     * @param ids 控件id
     */
    public void setOnClickListenerById(int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(this);
        }
    }



    public void scrollToFinishActivity() {
        //overridePendingTransition(R.anim.next_in, R.anim.next_out);
        finish();
    }
}
