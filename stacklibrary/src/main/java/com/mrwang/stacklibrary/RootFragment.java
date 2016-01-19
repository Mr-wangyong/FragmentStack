package com.mrwang.stacklibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * User: chengwangyong(chengwangyong@vcinema.com)
 * Date: 2016-01-18
 * Time: 18:19
 */
public abstract class RootFragment extends Fragment implements OnNewIntent {

    public void open(RootFragment fragment) {
        getRoot().manager.popFragment(this,fragment,null);
    }

    public void open(RootFragment fragment, Bundle bundle) {
        getRoot().manager.popFragment(this, fragment, bundle);
    }

    public void open(RootFragment fragment, Bundle bundle, int stackMode) {
        getRoot().manager.popFragment(this, fragment, bundle, stackMode);
    }



    public RootActivity getRoot() {
        FragmentActivity activity = getActivity();
        if (activity instanceof RootActivity) {
            return (RootActivity) activity;
        } else {
            throw new ClassCastException("this activity mast be extends RootActivity");
        }
    }

    @Override
    public void onNewIntent() {

    }
}
