package com.stack.simple;

import com.cinema.fragmentstacklibrary.RootActivity;
import com.cinema.fragmentstacklibrary.RootFragment;
import com.stack.simple.fragment.HomeFragment;

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
}
