package com.bangqu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bangqu.lib.R;

/**
 * Created by 豚趣 on 2016/12/12.
 */
public class FooterView extends RelativeLayout {

    private View footerView;
    private RelativeLayout rlFooter;
    private ImageView ivFooterLoading;
    private LinearLayout xlistview_footer_content;

    public FooterView(Context context) {
       this(context,null);
    }

    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        footerView= LayoutInflater.from(context).inflate(R.layout.xlistview_footer,null);
        rlFooter=(RelativeLayout)footerView.findViewById(R.id.rlFooter);
        ivFooterLoading=(ImageView) footerView.findViewById(R.id.ivFooterLoading);
        xlistview_footer_content=(LinearLayout) footerView.findViewById(R.id.xlistview_footer_content);
        addView(footerView);

        LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        footerView.setLayoutParams(params);
        Loading(ivFooterLoading);

    }

    /**
     * 设置显示隐藏
     */
    public void setFootertVisibility(int Visibility){
        rlFooter.setVisibility(Visibility);
    }

    /**
     * 设置高度
     */
    public void setLayoutParams(int h){
        LayoutParams layoutParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,h);
        xlistview_footer_content.setLayoutParams(layoutParams);
    }

    /**
     * 获取显示状态
     * @return
     */
    public int getFootertVisibility(){
        return rlFooter.getVisibility();
    }

    private RotateAnimation rotateAnimation;
    private void Loading(ImageView iv){
        rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());// 不停顿
        rotateAnimation.setDuration(2500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(-1);
        iv.setAnimation(rotateAnimation);
        rotateAnimation.startNow();
    }
}
