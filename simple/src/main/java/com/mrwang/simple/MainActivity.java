package com.mrwang.simple;


import android.os.Bundle;

import com.mrwang.stacklibrary.RootActivity;
import com.mrwang.stacklibrary.RootFragment;

/**
 * User: chengwangyong(chengwangyong@vcinema.com)
 * Date: 2016-01-19
 * Time: 09:48
 */
public class MainActivity extends RootActivity {

    @Override
    protected RootFragment getRootFragment() {
        return new HomeFragment();
    }

    @Override
    public void onCreateNow(Bundle savedInstanceState) {
        setAnim(R.anim.next_in, R.anim.next_out, R.anim.quit_in, R.anim.quit_out);
    }
}
