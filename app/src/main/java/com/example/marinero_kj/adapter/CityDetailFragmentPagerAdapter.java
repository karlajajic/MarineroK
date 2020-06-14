package com.example.marinero_kj.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.marinero_kj.R;
import com.example.marinero_kj.ReviewFragment;
import com.example.marinero_kj.SightDetailFragment;
import com.example.marinero_kj.SunriseFragment;
import com.example.marinero_kj.pojo.Sight;

public class CityDetailFragmentPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.city_tab_text_1, R.string.city_tab_text_2, R.string.city_tab_text_3};
    private final Context mContext;
    private final Sight  sight;
    private final String username;

    public CityDetailFragmentPagerAdapter(Context context, FragmentManager fm, Sight sight, String username) {
        super(fm);
        mContext = context;

        this.username=username;
        if(sight==null)
            this.sight=new Sight("", context.getResources().getString(R.string.error).toString(),"", true);
        else this.sight=sight;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0)
            return SightDetailFragment.newInstance(sight);
        else if(position==2)
            return SunriseFragment.newInstance(sight.getLongitude(), sight.getLatitude());
        else return ReviewFragment.newInstance(sight.getName(), username);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}