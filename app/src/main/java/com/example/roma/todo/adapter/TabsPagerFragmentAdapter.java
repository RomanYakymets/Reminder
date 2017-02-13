package com.example.roma.todo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.example.roma.todo.R;
import com.example.roma.todo.fragment.AbstractTabFragment;
import com.example.roma.todo.fragment.AllItemFragment;
import com.example.roma.todo.fragment.IdeasFragment;
import com.example.roma.todo.fragment.NotificationsFragment;
import com.example.roma.todo.fragment.TodoFragment;

import java.util.HashMap;
import java.util.Map;



public class TabsPagerFragmentAdapter extends FragmentStatePagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;
    private FragmentManager fm;

    public TabsPagerFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.context = context;
        initTabsMap();

    }

    private void initTabsMap() {
        tabs = new HashMap<>();
        tabs.put(0, AllItemFragment.getInstance());
        tabs.put(1, IdeasFragment.getInstance());
        tabs.put(2, TodoFragment.getInstance());
        tabs.put(3, NotificationsFragment.getInstance());
    }
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if(obj instanceof Fragment){
            AbstractTabFragment fragment = (AbstractTabFragment) obj;
            tabs.put(position, fragment);
        }
        return obj;
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getString(R.string.tab_item_all);
            case 1:
                return context.getString(R.string.tab_item_ideas);
            case 2:
                return context.getString(R.string.tab_item_todo);
            case 3:
                return context.getString(R.string.tab_item_notification);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    public Fragment getFragment(int position) {
    String tag = tabs.get(position).getTag();
        if (tag == null)
            return null;

        return fm.findFragmentByTag(tag);

    }
}
