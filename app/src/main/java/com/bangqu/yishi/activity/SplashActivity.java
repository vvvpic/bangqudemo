package com.bangqu.yishi.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.bangqu.base.activity.BaseActivity;
import com.bangqu.bean.UserLoginBean;
import com.bangqu.utils.Contact;
import com.bangqu.utils.SharedUtils;
import com.bangqu.yishi.R;
import com.longtu.base.util.StringUtils;
import com.loopj.android.http.RequestParams;

public class SplashActivity extends BaseActivity {

    private Message msg;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if (!SharedUtils.getWelcome(SplashActivity.this)) {
                        Jump(WelcomeActivity.class);
                        finish();
                    }else {
                        Jump(LoginActivity.class);
                        finish();
                    }
                    break;
                case 2:
                    Contact.userLoginBean= JSONObject.parseObject(msg.obj.toString(),UserLoginBean.class);
                    if (Contact.userLoginBean!=null){
                        if (Contact.userLoginBean.getStatus().equals("1")){
                            Jump(HomeActivity.class);
                        }else {
                            Jump(LoginActivity.class);
                        }
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initDatas() {

        if (!StringUtils.isEmpty(SharedUtils.getUserName(this))&&!StringUtils.isEmpty(SharedUtils.getUserPwd(this))){
            params=new RequestParams();
            params.put("user.username", SharedUtils.getUserName(this));
            params.put("user.password", SharedUtils.getUserPwd(this));
            pullpost("user/login",params);
        }else {
            handler.sendEmptyMessageDelayed(1,2000);
        }
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("user/login")){
            msg.what=2;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
