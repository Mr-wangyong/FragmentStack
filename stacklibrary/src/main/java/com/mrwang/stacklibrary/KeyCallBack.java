package com.mrwang.stacklibrary;

import android.view.KeyEvent;

/**
 * Intercept key event callback
 * User: chengwangyong(cwy545177162@163.com)
 * Date: 2015-10-14
 * Time: 21:41
 */
public interface KeyCallBack {
    boolean onKeyDown(int keyCode, KeyEvent event);
}
