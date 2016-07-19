package com.mrwang.stacklibrary.test;

import com.mrwang.stacklibrary.CustomBuildConfig;
import com.mrwang.stacklibrary.FragmentStack;
import com.mrwang.stacklibrary.data.RootFragmentIml;
import com.mrwang.stacklibrary.data.TestRootFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by dell on 2016/7/19.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = CustomBuildConfig.class, sdk = 21, packageName = "com.mrwang.stacklibrary")
public class FragmentStackTest {
    FragmentStack stack;

    @Before
    public void setUp() {
        stack = new FragmentStack();
    }


    @Test
    public void putSingleTask() {
        TestRootFragment rootFragment = new TestRootFragment();
        assertEquals(stack.putSingleTask(rootFragment), false);
        assertEquals(stack.putSingleTask(rootFragment), true);
    }

    @Test
    public void putSingleTop() {
        TestRootFragment rootFragment = new TestRootFragment();
        assertEquals(stack.putSingleTop(rootFragment), false);
        assertEquals(stack.putSingleTop(rootFragment), true);
    }

    @Test
    public void putStandard() {
        TestRootFragment rootFragment = new TestRootFragment();
        stack.putStandard(rootFragment);
        assertEquals(stack.putSingleTop(rootFragment), true);

        RootFragmentIml iml = new RootFragmentIml();
        assertEquals(stack.putSingleTop(iml), false);
    }

    @Test
    public void putSingleInstance() {
        TestRootFragment rootFragment = new TestRootFragment();
        stack.putSingleInstance(rootFragment);
        assertEquals(stack.putSingleTask(rootFragment), true);
    }

}
