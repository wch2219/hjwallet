package zz.hjzn.hjwallet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xuxingchen on 2017/11/17.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {



    private List<Fragment> mFragments;


    public MainViewPagerAdapter(List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if (null == mFragments) {
            return 0;
        }
        return mFragments.size();
    }


}
