package com.mrwang.stacklibrary.test;

import com.mrwang.stacklibrary.CustomBuildConfig;
import com.mrwang.stacklibrary.data.TestActivity;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by dell on 2016/7/19.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = CustomBuildConfig.class, sdk = 21, packageName = "com.mrwang.stacklibrary")
public class StackManagerTest {

    private TestActivity activity;
    @Before
    public void setUp(){
        activity = Robolectric.buildActivity(TestActivity.class).create().get();
    }


}
