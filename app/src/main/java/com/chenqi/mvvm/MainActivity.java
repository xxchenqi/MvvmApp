package com.chenqi.mvvm;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.chenqi.mvvm.base.BaseActivity;
import com.chenqi.mvvm.home.HomeFragment;

import butterknife.BindView;

/**
 * @date 2018-11-27
 * @author cq
 * @Description 主页
 */
public class MainActivity extends BaseActivity {

    private final int HOME = 0;
    private final int TAB2 = 1;
    private final int TAB3 = 2;
    private final int MINE = 3;


    private Fragment mHomeFragment;
    private Fragment mHomeFragment2;
    private Fragment mHomeFragment3;
    private Fragment mHomeFragment4;

    // 当前显示的 fragment
    private Fragment mCurrent;
    private FragmentManager mFragmentManager;


    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.navigation_bar)
    BottomNavigationBar navigation_bar;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();
        initNavigationBar();
        setDefaultFragment();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void reLoad() {

    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mHomeFragment2 = new HomeFragment();
        mHomeFragment3 = new HomeFragment();
        mHomeFragment4 = new HomeFragment();
    }

    private void initNavigationBar() {
        navigation_bar.setMode(BottomNavigationBar.MODE_FIXED);
        navigation_bar.addItem(new BottomNavigationItem(R.mipmap.ic_home, "首页"));
        navigation_bar.addItem(new BottomNavigationItem(R.mipmap.ic_home, "TAB2"));
        navigation_bar.addItem(new BottomNavigationItem(R.mipmap.ic_home, "TAB3"));
        navigation_bar.addItem(new BottomNavigationItem(R.mipmap.ic_home, "我的"));

        // 设置底部 BottomBar 默认选中 home
        navigation_bar.setFirstSelectedPosition(HOME);
        // 初始化
        navigation_bar.initialise();

        navigation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
                switchFragment(position);
            }
        });

    }


    private void setDefaultFragment() {
        mCurrent = mHomeFragment;
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, mHomeFragment).commit();
    }


    private void switchFragment(int position) {
        switch (position) {
            case HOME:
                sw(mCurrent, mHomeFragment);
                break;
            case TAB2:
                sw(mCurrent, mHomeFragment2);
                break;
            case TAB3:
                sw(mCurrent, mHomeFragment3);
                break;
            case MINE:
                sw(mCurrent, mHomeFragment4);
                break;
        }
    }

    // 复用 fragment
    private void sw(Fragment from, Fragment to) {
        if (mCurrent != to) {
            mCurrent = to;
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            if (to.isAdded())
                transaction.hide(from).show(to);
            else
                transaction.hide(from).add(R.id.fl_content, to);
            transaction.commit();
        }
    }
}


