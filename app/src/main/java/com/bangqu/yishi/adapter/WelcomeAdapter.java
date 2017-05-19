package com.bangqu.yishi.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bangqu.yishi.R;

import java.util.List;

/**
 * Created by 豚趣 on 2016/12/14.
 */
public class WelcomeAdapter extends PagerAdapter {

    private List<Integer> listRes;
    private Context context;

    public WelcomeAdapter(List<Integer> listRes, Context context){
        this.listRes=listRes;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listRes.size();
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == (View) arg1;
    }

    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.welcome_item,null);
        ImageView ivCover=(ImageView)view.findViewById(R.id.ivCover);
        ivCover.setImageResource(listRes.get(position));
        ((ViewPager) container).addView(view, 0);
        return view;
    }
}
