package com.bangqu.yishi.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bangqu.base.activity.BaseActivity;
import com.bangqu.utils.SharedUtils;
import com.bangqu.yishi.R;
import com.bangqu.yishi.adapter.WelcomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 欢迎页
 */
public class WelcomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private Button btnLogin, btnShop;
    private LinearLayout llPoint;

    private List<Integer> listRes;

    private List<ImageView> listImageViews;
    private ImageView imageView;
    private WelcomeAdapter welcomeAdapter;

    @Override
    public void setContentView() {
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initViews() {
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnShop=(Button) findViewById(R.id.btnShop);
        llPoint=(LinearLayout) findViewById(R.id.llPoint);
    }

    @Override
    public void initDatas() {
        SharedUtils.setWelcome(this,true);
        listRes=new ArrayList<>();
        listRes.add(R.mipmap.welcome_bg);
        listRes.add(R.mipmap.welcome_bg);
        listRes.add(R.mipmap.welcome_bg);
        welcomeAdapter=new WelcomeAdapter(listRes,this);
        viewPager.setAdapter(welcomeAdapter);
        AddPoint();

    }

    private void AddPoint(){
        listImageViews=new ArrayList<>();
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,2,10,2);
        for (int i=0;i<3;i++){
            imageView=new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            if (i==0){
                imageView.setImageResource(R.mipmap.dot_white);
            }else {
                imageView.setImageResource(R.mipmap.dot_gary);
            }
            llPoint.addView(imageView);
            listImageViews.add(imageView);
        }
    }

    private void setPoint(int pos){
        for (int i=0;i<listImageViews.size();i++){
            if (pos==i){
                listImageViews.get(i).setImageResource(R.mipmap.dot_white);
            }else {
                listImageViews.get(i).setImageResource(R.mipmap.dot_gary);
            }
        }
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        viewPager.setOnPageChangeListener(this);
        btnLogin.setOnClickListener(this);
        btnShop.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Jump(LoginActivity.class);
                finish();
                break;
            case R.id.btnShop:
                Jump(RegisterActivity.class);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPoint(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
