package com.cinema.fragmentstacklibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * User: chengwangyong(chengwangyong@vcinema.com)
 * Date: 2015-12-06
 * Time: 20:25
 */
public class StackManager implements CloseFragment {
    private FragmentStack stack;
    private final FragmentActivity context;
    private long CLICK_SPACE = 500;
    private long currentTime;
    private int currentMode;

    /**
     * 设置点击间隔时间 防止重复点击 默认500ms
     *
     * @param CLICK_SPACE 重复点击时间
     */
    public void setClickSpace(long CLICK_SPACE) {
        this.CLICK_SPACE = CLICK_SPACE;
    }


    public StackManager(FragmentActivity context) {
        stack = new FragmentStack();
        stack.setCloseFragmentListener(this);
        this.context = context;
    }

    /**
     * 设置底层的fragment
     */
    public void setFragment(Fragment mTargetFragment) {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.framLayoutId, mTargetFragment, mTargetFragment.getClass().getName())
                .commit();
    }


    public void popFragment(Fragment from, Fragment to) {
        if (System.currentTimeMillis() - currentTime > CLICK_SPACE) {
            FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
            transaction
                    .setCustomAnimations(R.anim.next_in, R.anim.next_out, R.anim.pop_enter, R.anim.pop_exit)//必须在add、remove、replace调用之前被设置，否则不起作用。
                    .add(R.id.framLayoutId, to, to.getClass().getName())
                    .setCustomAnimations(R.anim.next_in, R.anim.next_out, R.anim.pop_enter, R.anim.pop_exit)
                    .hide(from)
                    .addToBackStack(to.getClass().getName())
                    .commit();
            currentTime = System.currentTimeMillis();
        }
    }


    /**
     * 跳转到指定的fragment
     */
    public void popFragment(RootFragment from, RootFragment to, Bundle bundle, int stackMode) {
        if (stackMode != 0) {
            currentMode = stackMode;
        }
        switch (currentMode) {
            case FragmentStack.SINGLE_TOP:
                stack.putSingleTop(to);
                break;
            case FragmentStack.SINGLE_TASK:
                stack.putSingleTask(to);
                break;
            case FragmentStack.SINGLE_INSTANCE:
                stack.putSingleInstance(to);
                break;
            default:
                stack.putStandard(to);
                break;
        }
        if (bundle != null) {
            to.setArguments(bundle);
        }
        popFragment(from, to);
    }


    public void openFragment(RootFragment from, RootFragment to) {
        popFragment(from, to, null, 0);
    }

    /**
     * 跳转到指定的fragment 带参形式
     */
    public void popFragment(RootFragment from, RootFragment to, Bundle bundle) {
        popFragment(from, to, bundle, 0);
    }

    /**
     * 跳转到指定的fragment 并且不隐藏当前页面
     *
     * @param to 要跳转的页面
     */
    public void popFragment(Fragment to) {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction
                    .setCustomAnimations(R.anim.dialog_in, R.anim.dialog_out)
                    .add(R.id.framLayoutId, to, to.getClass().getName())
                    .addToBackStack(to.getClass().getName())
                    .commit();
        }
    }

    /**
     * 关闭指定的fragment
     *
     * @param mTargetFragment fragment
     */
    public void closeFragment(Fragment mTargetFragment) {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.remove(mTargetFragment).commit();
    }

    /**
     * 根据tag 关闭指定的fragment
     *
     * @param tag fragment的tag
     */
    public void closeFragment(String tag) {
        Fragment fragmentByTag = context.getSupportFragmentManager().findFragmentByTag(tag);
        if (fragmentByTag != null) {
            closeFragment(fragmentByTag);
            context.getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void close() {
        context.getSupportFragmentManager().popBackStack();
    }


    /**
     * 关闭所有的fragment
     */
    public void closeAllFragment() {
        int backStackCount = context.getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            int backStackId = context.getSupportFragmentManager().getBackStackEntryAt(i).getId();
            context.getSupportFragmentManager().popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void onBackPressed() {
        stack.onBackPressed();
    }



    @Override
    public void close(String tag) {
        closeFragment(tag);
    }
}
