package com.mrwang.stacklibrary;

import android.os.Bundle;
import android.support.annotation.AnimRes;
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
     *
     * @param fragment fragment
     */
    public void open(@NonNull RootFragment fragment) {
        getRoot().manager.addFragment(this, fragment, null);
    }

    /**
     * open a new Fragment,And transfer parameters with bundle
     * <p>
     * Like this
     * <pre>   {@code
     * Bundle bundle=new Bundle();
     * bundle.put(key,value);
     * In the new fragment, you can accept parameters like this
     * Bundle bundle = fragment.getArguments();
     * bundle.get(key);
     * }</pre>
     *
     * @param fragment fragment
     * @param bundle   bundle
     */
    public void open(@NonNull RootFragment fragment, Bundle bundle) {
        getRoot().manager.addFragment(this, fragment, bundle);
    }

    /**
     * open a new Fragment,And transfer parameters with bundle andr set StackMode
     * Like this
     * <pre>   {@code
     * Bundle bundle=new Bundle();
     * bundle.put(key,value);
     *
     * }</pre>
     * In the new fragment, you can accept parameters like this
     * <pre>   {@code
     * Bundle bundle = fragment.getArguments();
     * bundle.get(key);<br/>
     * }</pre>
     *
     * @param fragment  fragment
     * @param bundle    bundle
     * @param stackMode stackMode,{@link StackManager#STANDARD} or more
     */

    public void open(@NonNull RootFragment fragment, Bundle bundle, int stackMode) {
        getRoot().manager.addFragment(this, fragment, bundle, stackMode);
    }

    /**
     * Jump to the specified fragment and do not hide the current page.
     *
     * @param to To jump to the page
     */
    public void dialogFragment(Fragment to) {
        getRoot().manager.dialogFragment(to);
    }

    /**
     * Set the animation to add fragment in dialog mode
     *
     * @param dialog_in  The next page to enter the animation
     * @param dialog_out The next page out of the animation
     */
    public void setDialogAnim(@AnimRes int dialog_in, @AnimRes int dialog_out) {
        getRoot().manager.setDialogAnim(dialog_in, dialog_out);
    }

    /**
     * close this current Fragment
     */
    public void close() {
        getRoot().manager.close(this);
    }

    /**
     * Closes the specified fragment
     *
     * @param fragment the specified fragment
     */
    public void close(RootFragment fragment) {
        getRoot().manager.close(fragment);
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
