package com.bangqu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bangqu.listener.RollPagerListener;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by coach on 16/10/18.
 * <p>
 * //设置播放时间间隔
 * mRollViewPager.setPlayDelay(3000);
 * //设置透明度
 * mRollViewPager.setAnimationDurtion(500);
 * //设置适配器
 * <p>
 * //设置指示器（顺序依次）
 * //自定义指示器图片
 * //设置圆点指示器颜色
 * //设置文字指示器
 * //隐藏指示器
 * //        mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
 * mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.WHITE)); //下标颜色
 * //        mRollViewPager.setHintView(new TextHintView(this));
 * //        mRollViewPager.setHintView(null); //无下标
 */
public class RollPagerAdapter extends StaticPagerAdapter {
    List<String> mlist;
    Context mcontext;

    public RollPagerAdapter(Context context, List<String> list) {
        mlist = list;
        mcontext = context;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        ImageView view = new ImageView(container.getContext());
        ImageLoader.getInstance().displayImage(mlist.get(position), view);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rollPagerListener != null) {
                    rollPagerListener.clickBcak(position);
                }
            }
        });
        return view;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    private RollPagerListener rollPagerListener;

    public void setRollPagetListener(RollPagerListener rollPagerListener) {
        this.rollPagerListener = rollPagerListener;
    }

}
