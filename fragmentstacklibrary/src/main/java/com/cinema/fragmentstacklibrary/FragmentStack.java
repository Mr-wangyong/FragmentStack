package com.cinema.fragmentstacklibrary;

import java.util.ArrayList;

/**
 * Fragment任务栈
 * User: chengwangyong(chengwangyong@vcinema.com)
 * Date: 2015-12-06
 * Time: 19:39
 */
public class FragmentStack {
    public static final int STANDARD=0;
    public static final int SINGLE_TOP =1;
    public static final int SINGLE_TASK =2;
    public static final int SINGLE_INSTANCE=3;


    private ArrayList<ArrayList<OnNewIntent>> stackList = new ArrayList<>();
    private ArrayList<OnNewIntent> stack;

    public FragmentStack() {
        if (stack ==null){
            stack =new ArrayList<>();
        }
        stackList.add(stack);
    }



    /**
     * standard 模式 直接加入到当前的任务栈
     * @param fragment 加入的fragment
     */
    public void putStandard(OnNewIntent fragment){
        stackList.get(stackList.size()-1).add(fragment);
    }

    /**
     * SingleTop模式 如果顶部有 则不创建
     * @param fragment 加入的fragment
     */
    public void putSingleTop(OnNewIntent fragment){
        ArrayList<OnNewIntent> lastList = stackList.get(stackList.size() - 1);
        OnNewIntent last = lastList.get(lastList.size() - 1);
        if (last!=fragment){
            lastList.add(fragment);
        }else{
            fragment.onNewIntent();
        }
    }

    /**
     * singTask模式 如果当前任务栈中有 则不创建 并清空所有的上层实例
     * @param fragment 加入的fragment
     */
    public void putSingleTask(OnNewIntent fragment){
        ArrayList<OnNewIntent> lastList = stackList.get(stackList.size() - 1);
        boolean isClear=false;
        for (int x=lastList.size()-1;x>=0;x--){
            if (isClear){
                lastList.remove(x);
            }
            if (lastList.get(x)==fragment){
                //清空上层所有的实例
                isClear=true;
            }else{
                lastList.add(fragment);
            }
        }
    }

    /**
     * singleInstance 模式 每次都创建一个新的任务栈
     * @param fragment 加入的fragment
     */
    public void putSingleInstance(OnNewIntent fragment){
        ArrayList<OnNewIntent> frags=new ArrayList<>();
        frags.add(fragment);
        stackList.add(frags);
    }

    public void onBackPressed() {
        ArrayList<OnNewIntent> lastStack = stackList.get(stackList.size() - 1);
        if (lastStack==null||lastStack.isEmpty()){
            stackList.remove(lastStack);
        }else{
            lastStack.remove(lastStack.size()-1);
        }
    }
}
