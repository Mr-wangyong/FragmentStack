package com.mrwang.stacklibrary;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Fragment task stack manager
 * User: chengwangyong(cwy545177162@163.com)
 * Date: 2015-12-06
 * Time: 20:25
 */
public class StackManager implements CloseFragment {
    private FragmentStack stack;
    private final FragmentActivity context;
    private long CLICK_SPACE = 500;
    private long currentTime;
    private int currentMode;
    private int nextIn;
    private int nextOut;
    private int quitIn;
    private int quitOut;
    private Animation next_in;
    private Animation next_out;
    private int dialog_in;
    private int dialog_out;

    /**
     * Set the time to click to Prevent repeated clicks,default 500ms
     *
     * @param CLICK_SPACE Repeat click time
     */
    public void setClickSpace(long CLICK_SPACE) {
        this.CLICK_SPACE = CLICK_SPACE;
    }


    public StackManager(FragmentActivity context) {
        stack = new FragmentStack();
        stack.setCloseFragmentListener(this);
        this.context = context;

    }

    /**
     * Set the bottom of the fragment
     */
    public void setFragment(@NonNull RootFragment mTargetFragment) {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.framLayoutId, mTargetFragment, mTargetFragment.getClass().getName())
                .commit();
        stack.putStandard(mTargetFragment);
    }

    /**
     * Jump to the specified fragment
     */
    public void addFragment(@NonNull final Fragment from, @NonNull final Fragment to) {
        if (System.currentTimeMillis() - currentTime > CLICK_SPACE) {
            currentTime = System.currentTimeMillis();

            FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
            if (nextIn != 0 && nextOut != 0 && quitIn != 0 && quitOut != 0) {
                transaction
                        .setCustomAnimations(nextIn, nextOut)
                        .add(R.id.framLayoutId, to, to.getClass().getName())
                        .setCustomAnimations(nextIn, nextOut)
                        .hide(from)
                        .commit();
            } else {
                transaction
                        .add(R.id.framLayoutId, to, to.getClass().getName())
                        .hide(from)
                        .commit();
            }

        }
    }

    /**
     * Set page switch animation
     *
     * @param nextIn  The next page to enter the animation
     * @param nextOut The next page out of the animation
     * @param quitIn  The current page into the animation
     * @param quitOut Exit animation for the current page
     */
    public void setAnim(@AnimRes int nextIn, @AnimRes int nextOut, @AnimRes int quitIn, @AnimRes int quitOut) {
        this.nextIn = nextIn;
        this.nextOut = nextOut;
        this.quitIn = quitIn;
        this.quitOut = quitOut;
        next_in = AnimationUtils.loadAnimation(context, quitIn);
        next_out = AnimationUtils.loadAnimation(context, quitOut);
    }


    /**
     * Jump to the specified fragment
     */
    public void addFragment(RootFragment from, RootFragment to, Bundle bundle, int stackMode) {
        if (stackMode != 0) {
            currentMode = stackMode;
        }
        if (bundle != null) {
            to.setArguments(bundle);
        }
        switch (currentMode) {
            case FragmentStack.SINGLE_TOP:
                if (!stack.putSingleTop(to)) {
                    addFragment(from, to);
                }
                break;
            case FragmentStack.SINGLE_TASK:
                if (!stack.putSingleTask(to)) {
                    addFragment(from, to);
                }
                break;
            case FragmentStack.SINGLE_INSTANCE:
                stack.putSingleInstance(to);
                addFragment(from, to);
                break;
            default:
                stack.putStandard(to);
                addFragment(from, to);
                break;
        }


    }


    public void openFragment(RootFragment from, RootFragment to) {
        addFragment(from, to, null, 0);
    }

    /**
     * Jump to the specified fragment with a parameter form
     */
    public void addFragment(RootFragment from, RootFragment to, Bundle bundle) {
        addFragment(from, to, bundle, 0);
    }

    /**
     * Jump to the specified fragment and do not hide the current page.
     *
     * @param to To jump to the page
     */
    public void dialogFragment(Fragment to) {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            if (dialog_in != 0 && dialog_out != 0) {
                transaction
                        .setCustomAnimations(dialog_in, dialog_out)
                        .add(R.id.framLayoutId, to, to.getClass().getName())
                        .commit();
            } else {
                transaction
                        .add(R.id.framLayoutId, to, to.getClass().getName())
                        .commit();
            }

        }
    }

    /**
     * Set the animation to add fragment in dialog mode
     *
     * @param dialog_in  The next page to enter the animation
     * @param dialog_out The next page out of the animation
     */
    public void setDialogAnim(@AnimRes int dialog_in, @AnimRes int dialog_out) {
        this.dialog_in = dialog_in;
        this.dialog_out = dialog_out;
    }

    /**
     * Closes the specified fragment
     *
     * @param mTargetFragment fragment
     */
    public void closeFragment(Fragment mTargetFragment) {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.remove(mTargetFragment).commit();
    }

    /**
     * Close the specified fragment by tag
     *
     * @param tag fragment tag
     */
    public void closeFragment(String tag) {
        Fragment fragmentByTag = context.getSupportFragmentManager().findFragmentByTag(tag);
        if (fragmentByTag != null) {
            closeFragment(fragmentByTag);
            context.getSupportFragmentManager().popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void close() {
        context.getSupportFragmentManager().popBackStack();
    }


    /**
     * Close all fragment
     */
    public void closeAllFragment() {
        int backStackCount = context.getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            int backStackId = context.getSupportFragmentManager().getBackStackEntryAt(i).getId();
            context.getSupportFragmentManager().popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void onBackPressed() {
        Fragment[] last = stack.getLast();
        final Fragment from = last[0];
        Fragment to = last[1];

        if (from != null) {
            if (to != null) {
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                transaction.show(to).commit();
            }
            View fromVie = from.getView();
            if (fromVie != null && next_out != null) {
                fromVie.startAnimation(next_out);
                next_out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        stack.onBackPressed();
                        closeFragment(from);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            } else {
                stack.onBackPressed();
                closeFragment(from);
            }
        }
        if (to != null) {
            View toView = to.getView();
            if (toView != null && next_in != null) {
                toView.startAnimation(next_in);
            }
        } else {
            closeAllFragment();
            context.finish();
        }
    }

    public static boolean isFirstClose = true;

    @Override
    public void close(final RootFragment fragment) {
        if (isFirstClose) {
            View view = fragment.getView();
            if (view != null) {
                if (next_out != null) {
                    view.startAnimation(next_out);
                    next_out.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            closeFragment(fragment);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                } else {
                    closeFragment(fragment);
                }
            }
            isFirstClose = false;
        } else {
            closeFragment(fragment);
        }

    }

    @Override
    public void show(RootFragment fragment) {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.show(fragment).commit();
        View view = fragment.getView();
        if (view != null && next_in != null) {
            view.startAnimation(next_in);
        }
    }
}
