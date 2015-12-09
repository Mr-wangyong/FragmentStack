package com.mr.wang.frametest;

import java.util.ArrayList;

/**
 * User: chengwangyong(chengwangyong@vcinema.com)
 * Date: 2015-12-06
 * Time: 19:39
 */
public class FragmentStack {
    public static final int STANDARD=0;
    public static final int SINGLETOP=1;
    public static final int SINGLETASK=2;
    public static final int SINGLEINSTANCE=3;

    private ArrayList<ArrayList<BasePresenterFragment>> lists = new ArrayList<>();
    private ArrayList<BasePresenterFragment> list;

    public FragmentStack() {
        if (list==null){
            list=new ArrayList<>();
        }
        lists.add(list);
    }



    /**
     * standard 模式 直接加入到当前的任务栈
     * @param fragment 加入的fragment
     */
    public void putStandard(BasePresenterFragment fragment){
        list.add(fragment);
    }

    /**
     * SingleTop模式 如果顶部有 则不创建
     * @param fragment 加入的fragment
     */
    public void putSingleTop(BasePresenterFragment fragment){
        ArrayList<BasePresenterFragment> lastList = lists.get(lists.size() - 1);
        BasePresenterFragment last = lastList.get(lastList.size() - 1);
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
    public void putSingleTask(BasePresenterFragment fragment){
        ArrayList<BasePresenterFragment> lastList = lists.get(lists.size() - 1);
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
    public void putSingleInstance(BasePresenterFragment fragment){
        ArrayList<BasePresenterFragment> frags=new ArrayList<>();
        frags.add(fragment);
        lists.add(frags);
    }
}
