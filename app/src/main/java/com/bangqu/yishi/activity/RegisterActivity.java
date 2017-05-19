package com.bangqu.yishi.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bangqu.base.activity.BaseActivity;
import com.bangqu.bean.CommonBean;
import com.bangqu.bean.UserLoginBean;
import com.bangqu.utils.Contact;
import com.bangqu.utils.SharedUtils;
import com.bangqu.view.ClearEditText;
import com.bangqu.yishi.R;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

public class RegisterActivity extends BaseActivity {

    private ClearEditText etPhone;
    private Button btnNext;
    private TextView tvAgree, tvAgreement;
    private boolean selectbool=true;

    private Message msg;
    private CommonBean commonBean;

    private ClearEditText etCode ,etPwd, etInvitation;
    private TextView tvSend;
    private int  second=60;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    commonBean= JSONObject.parseObject(msg.obj.toString(),CommonBean.class);
                    if (commonBean!=null){
                        ToastUtils.show(RegisterActivity.this,commonBean.getMsg());
                        if (commonBean.getStatus().equals("1")){
                           /* intent=new Intent(RegisterActivity.this,SetPwdActivity.class);
                            intent.putExtra("phone",etPhone.getText().toString());
                            Jump(intent);
                            finish();*/
                            second=60;
                            tvSend.setEnabled(false);
                            sendEmptyMessageDelayed(2,1000);
                        }
                    }
                    break;
                case 2:
                    second--;
                    tvSend.setText(second+"s");
                    if (second>0){
                        sendEmptyMessageDelayed(2,1000);
                    }else {
                        tvSend.setText("获取验证码");
                        tvSend.setEnabled(true);
                    }
                    break;
                case 3:
                    Contact.userLoginBean= JSONObject.parseObject(msg.obj.toString(), UserLoginBean.class);
                    if (Contact.userLoginBean!=null){
                        ToastUtils.show(RegisterActivity.this, Contact.userLoginBean.getMsg());
                        if (Contact.userLoginBean.getStatus().equals("1")){
                            SharedUtils.setUserNamePwd(RegisterActivity.this,etPhone.getText().toString(),etPwd.getText().toString(),null);
                            intent=new Intent(RegisterActivity.this,HomeActivity.class);
                            /**
                             * 顶部跳转结束之前所有Activity
                             */
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            Jump(intent);
                        }
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initViews() {
        etPhone=(ClearEditText)findViewById(R.id.etPhone);
        btnNext =(Button) findViewById(R.id.btnNext);
        tvAgree =(TextView) findViewById(R.id.tvAgree);
        tvAgreement=(TextView) findViewById(R.id.tvAgreement);
        etCode=(ClearEditText)findViewById(R.id.etCode);
        etPwd =(ClearEditText)findViewById(R.id.etPwd);
        etInvitation=(ClearEditText)findViewById(R.id.etInvitation);
        tvSend=(TextView)findViewById(R.id.tvSend);
    }

    @Override
    public void initDatas() {
        setTitle("注册");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnNext.setOnClickListener(this);
        tvAgree.setOnClickListener(this);
        tvAgreement.setOnClickListener(this);
        tvSend.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSend:
                if (StringUtils.isEmpty(etPhone.getText().toString())){
                    ToastUtils.show(RegisterActivity.this,"请输入手机号");
                    return;
                }
                if (!Contact.isMobileNO(etPhone.getText().toString())){
                    ToastUtils.show(this,"手机格式化不正确");
                    return;
                }
                params=new RequestParams();
                params.put("mobile",etPhone.getText().toString());
                params.put("type","register");
                post("captcha/send",params);
                break;
            case R.id.tvAgree:
                if (selectbool){
                    Agree(R.mipmap.icon_select);
                } else {
                    Agree(R.mipmap.icon_selected);
                }
                selectbool=!selectbool;
                break;
            case R.id.btnNext:
                if (StringUtils.isEmpty(etPhone.getText().toString())){
                    ToastUtils.show(RegisterActivity.this,"请输入手机号");
                    return;
                }

                if (!Contact.isMobileNO(etPhone.getText().toString())){
                    ToastUtils.show(this,"手机格式化不正确");
                    return;
                }

                if (StringUtils.isEmpty(etCode.getText().toString())) {
                    ToastUtils.show(RegisterActivity.this, "请输入验证码");
                    return;
                }

                if (StringUtils.isEmpty(etPwd.getText().toString())) {
                    ToastUtils.show(RegisterActivity.this, "请输入密码");
                    return;
                }

                if (etPwd.getText().toString().length()<6){
                    ToastUtils.show(RegisterActivity.this, "密码必须6-20位");
                    return;
                }

                if (!selectbool){
                    ToastUtils.show(this,"请同意智盈外汇用户协议");
                    return;
                }


                params = new RequestParams();
                params.put("user.username", etPhone.getText().toString());
                params.put("user.password", etPwd.getText().toString());
                params.put("code", etCode.getText().toString());
                post("user/signup", params);
                break;
        }
    }

    private void Agree(int id){
        Drawable ico;
        ico = getResources().getDrawable(id);
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        ico.setBounds(0, 0, ico.getMinimumWidth(), ico.getMinimumHeight());
        tvAgree.setCompoundDrawables(ico, null, null, null); //设置左图标
    }


    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("captcha/send")){
            msg.what=1;
        }else if (requestname.equals("user/signup")){
            msg.what=3;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
