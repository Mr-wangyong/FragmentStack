package com.mrwang.stacklibrary;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * Fragment任务栈
 * User: chengwangyong(chengwangyong@vcinema.com)
 * Date: 2015-12-06
 * Time: 19:39
 */
public class FragmentStack {
    public static final int STANDARD = 0x11;
    public static final int SINGLE_TOP = 0x12;
    public static final int SINGLE_TASK = 0x13;
    public static final int SINGLE_INSTANCE = 0x14;


    private ArrayList<ArrayList<RootFragment>> stackList = new ArrayList<>();
    private ArrayList<RootFragment> stack;
    private CloseFragment listener;

    public FragmentStack() {
        if (stack == null) {
            stack = new ArrayList<>();
        }
        stackList.add(stack);
    }


    /**
     * standard 模式 直接加入到当前的任务栈
     *
     * @param fragment 加入的fragment
     */
    public void putStandard(RootFragment fragment) {
        stackList.get(stackList.size() - 1).add(fragment);
    }

    /**
     * SingleTop模式 如果顶部有 则不创建
     *
     * @param fragment 加入的fragment
     * @return 是否含有当前的实例
     */
    public boolean putSingleTop(RootFragment fragment) {
        ArrayList<RootFragment> lastList = stackList.get(stackList.size() - 1);
        if (lastList.isEmpty()) {
            lastList.add(fragment);
            return false;
        } else {
            RootFragment last = lastList.get(lastList.size() - 1);
            if (last.getClass().getName().equals(fragment.getClass().getName())) {
                fragment.onNewIntent();
                return true;
            } else {
                lastList.add(fragment);
                return false;
            }
        }


    }

    /**
     * singTask模式 如果当前任务栈中有 则不创建 并清空所有的上层实例
     *
     * @param fragment 加入的fragment
     * @return 是否含有当前的实例
     */
    public boolean putSingleTask(RootFragment fragment) {
        boolean isClear = false;
        ArrayList<RootFragment> lastList = stackList.get(stackList.size() - 1);
        if (lastList.isEmpty()) {
            lastList.add(fragment);
        } else {
            int tempIndex = 0;
            for (int x = 0; x <= lastList.size() - 1; x++) {
                if (lastList.get(x).getClass().getName().equals(fragment.getClass().getName())) {
                    //clear all instance
                    isClear = true;
                    tempIndex = x;
                    break;
                }
            }
            if (!isClear) {
                lastList.add(fragment);
            } else {
                if (listener!=null){
                    listener.show(lastList.get(tempIndex));
                    StackManager.isFirstClose=true;
                    for (int i = lastList.size()-1; i >tempIndex ; i--) {
                        listener.close(lastList.get(i));
                    }
                    for (int j = lastList.size()-1; j >tempIndex ; j--) {
                        lastList.remove(j);
                    }
                }

            }
        }
        return isClear;

    }

    /**
     * singleInstance 模式 每次都创建一个新的任务栈
     *
     * @param fragment 加入的fragment
     */
    public void putSingleInstance(RootFragment fragment) {
        ArrayList<RootFragment> frags = new ArrayList<>();
        frags.add(fragment);
        stackList.add(frags);
    }

    public void onBackPressed() {
        int i = stackList.size() - 1;
        if (i >= 0) {
            ArrayList<RootFragment> lastStack = stackList.get(i);
            if (lastStack != null && (!lastStack.isEmpty())) {
                lastStack.remove(lastStack.size() - 1);
                if (lastStack.isEmpty()) {
                    stackList.remove(lastStack);
                }
            } else {
                stackList.remove(lastStack);
            }
        } else {
            stackList.clear();
        }
//        int i = stackList.size() - 1;
//        if (i >= 0) {
//            ArrayList<RootFragment> lastStack = stackList.get(i);
//            if (lastStack == null || lastStack.isEmpty()) {
//                stackList.remove(lastStack);
//            } else {
//                lastStack.remove(lastStack.size() - 1);
//            }
//        } else {
//
//        }
    }

    public void setCloseFragmentListener(CloseFragment listener) {
        this.listener = listener;
    }

    public Fragment[] getLast() {
        Fragment[] fagArr = new Fragment[2];
        boolean hasFirst = false;
        for (int x = stackList.size() - 1; x >= 0; x--) {
            ArrayList<RootFragment> list = stackList.get(x);
            if (list != null && (!list.isEmpty())) {
                if (hasFirst) {
                    fagArr[1] = list.get(list.size() - 1);
                    break;
                } else {
                    hasFirst = true;
                    fagArr[0] = list.get(list.size() - 1);
                    if (list.size() > 1) {
                        fagArr[1] = list.get(list.size() - 2);
                    }
                }

            }
        }
        //return list.get(list.size()-1);
        return fagArr;
    }

//    public Fragment getSecondLast() {
////        int i = stackList.size() - 2;
////        if (i >= 0) {
////            ArrayList<RootFragment> lastStack = stackList.get(i);
////            if (lastStack == null || lastStack.isEmpty()) {
////                return null;
////            } else {
////                return lastStack.get(lastStack.size() - 1);
////            }
////        } else {
////            return null;
////        }
//
//        for (int x = stackList.size() - 1; x >= 0; x--) {
//            ArrayList<RootFragment> list = stackList.get(x);
//            if (list != null && (!list.isEmpty())) {
//                return list.get(list.size() - 2);
//            }
//        }
//        return null;
//    }
}
