package com.sjs.neo.appframe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private FragMessage mFragMsg;
    private FragClient mFragClient;
    private FragMy mFragMy;

    private ViewPager mPager;
    private RadioGroup mTabs;
    private RadioButton mTabMsg, mTabClient, mTabMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        mPager = (ViewPager) findViewById(R.id.main_pager);
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));
        mPager.setOnPageChangeListener(this);
        mTabs = (RadioGroup) findViewById(R.id.main_tabs);
        mTabMsg = (RadioButton) findViewById(R.id.tab_msg);
        mTabClient = (RadioButton) findViewById(R.id.tab_client);
        mTabMy = (RadioButton) findViewById(R.id.tab_my);
        initFragmentIndicator();

    }


    private void setTabIndicator(RadioButton btn) {
        btn.setChecked(true);
        btn.setTextColor(getResources().getColor(R.color.blue));
        if (btn != mTabMsg) {
            mTabMsg.setChecked(false);
            mTabMsg.setTextColor(getResources().getColor(R.color.menu_gray));
        }
        if (btn != mTabClient) {
            mTabClient.setChecked(false);
            mTabClient.setTextColor(getResources().getColor(R.color.menu_gray));
        }
        if (btn != mTabMy) {
            mTabMy.setChecked(false);
            mTabMy.setTextColor(getResources().getColor(R.color.menu_gray));
        }

    }


    private void initFragmentIndicator() {
        setTabIndicator(mTabMsg);
        mTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_msg:
                        setTabIndicator(mTabMsg);
                        mPager.setCurrentItem(0, mPager.getCurrentItem() != 2);
                        break;
                    case R.id.tab_client:
                        setTabIndicator(mTabClient);
                        mPager.setCurrentItem(1);
                        break;
                    case R.id.tab_my:
                        setTabIndicator(mTabMy);
                        mPager.setCurrentItem(2, mPager.getCurrentItem() != 0);
                        break;
                    default:
                        break;
                }
            }
        });
    }



    private class MainFragmentPagerAdapter extends FragmentPagerAdapter {

        public MainFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int id) {
            switch (id) {
                case 0:
                    if (mFragMsg == null) {
                        mFragMsg = new FragMessage();
                    }
                    return mFragMsg;
                case 1:
                    if (mFragClient== null) {
                        mFragClient = new FragClient();
                    }
                    return mFragClient;
                case 2:
                    if (mFragMy == null) {
                        mFragMy = new FragMy();
                    }
                    return mFragMy;

                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        switch (position) {
            case 0:
                setTabIndicator(mTabMsg);
                break;
            case 1:
                setTabIndicator(mTabClient);
                break;
            case 2:
                setTabIndicator(mTabMy);
                break;
            default:
                break;
        }




    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
