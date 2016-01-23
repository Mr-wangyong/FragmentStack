package com.mrwang.stacklibrary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * extends this Fragment to facilitate the management of multiple fragment instances
 * User: chengwangyong(cwy545177162@163.com)
 * Date: 2016-01-18
 * Time: 18:19
 */
public abstract class RootFragment extends Fragment implements OnNewIntent {

    /**
     * open a new Fragment
     * </p>
     *
     * @param fragment fragment
     */
    public void open(@NonNull RootFragment fragment) {
        getRoot().manager.addFragment(this, fragment, null);
    }

    /**
     * open a new Fragment,And transfer parameters with bundle
     * <br/>
     * Like this
     * <br/><p>
     * Bundle bundle=new Bundle();<br/>
     * bundle.put(key,value);
     * </p>
     * In the new fragment, you can accept parameters like this
     * <br/>
     * <p/>
     * Bundle bundle = fragment.getArguments();<br/>
     * bundle.get(key);<br/>
     * <p/>
     *
     * @param fragment fragment
     * @param bundle   bundle
     */
    public void open(@NonNull RootFragment fragment, Bundle bundle) {
        getRoot().manager.addFragment(this, fragment, bundle);
    }

    /**
     * open a new Fragment,And transfer parameters with bundle andr set StackMode
     * <br/>
     * Like this
     * <br/><p>
     * Bundle bundle=new Bundle();<br/>
     * bundle.put(key,value);
     * </p>
     * In the new fragment, you can accept parameters like this
     * <br/>
     * <p/>
     * Bundle bundle = fragment.getArguments();<br/>
     * bundle.get(key);<br/>
     * <p/>
     *
     * @param fragment  fragment
     * @param bundle    bundle
     * @param stackMode stackMode,{@link FragmentStack#STANDARD} or more
     */
    public void open(@NonNull RootFragment fragment, Bundle bundle, int stackMode) {
        getRoot().manager.addFragment(this, fragment, bundle, stackMode);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            onNowHidden();
        } else {
            onNextShow();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Override this method to facilitate access to the current page page Pause callback
     */
    private void onNowHidden() {

    }

    /**
     * Override this method to facilitate access to the current page page Resume callback
     */
    private void onNextShow() {

    }

    /**
     * Get fragment dependent Activity, many times this is very useful
     *
     * @return RootActivity dependent Activity
     */
    public RootActivity getRoot() {
        FragmentActivity activity = getActivity();
        if (activity instanceof RootActivity) {
            return (RootActivity) activity;
        } else {
            throw new ClassCastException("this activity mast be extends RootActivity");
        }
    }

    /**
     * Override this method in order to facilitate the singleTop mode to be called in
     */
    @Override
    public void onNewIntent() {
    }
}
