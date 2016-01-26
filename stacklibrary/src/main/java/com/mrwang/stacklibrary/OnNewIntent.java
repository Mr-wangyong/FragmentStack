package com.mrwang.stacklibrary;

/**
 * In the SingleTop mode, if the current task stack has, then call the callback fragment
 * <p>
 * User: chengwangyong(cwy545177162@163.com)
 * Date: 2015-12-28
 * Time: 23:41
 */
public interface OnNewIntent {
    /**
     * SingleTop mode,this fragment not be Re create
     */
    void onNewIntent();
}
