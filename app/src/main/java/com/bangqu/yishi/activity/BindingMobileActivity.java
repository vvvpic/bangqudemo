package com.bangqu.yishi.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bangqu.base.activity.BaseActivity;
import com.bangqu.bean.UserLoginBean;
import com.bangqu.utils.Contact;
import com.bangqu.utils.SharedUtils;
import com.bangqu.yishi.R;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

/**
 * 绑定手机
 */
public class BindingMobileActivity extends BaseActivity {

    private Button btnBinding;
    private TextView tvAgree;
    private TextView tvAgreement;
    private EditText etPhone;
    private boolean selectbool=true;
    private String username,platform;

    private Message msg;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Contact.userLoginBean= JSONObject.parseObject(msg.obj.toString(), UserLoginBean.class);
                    if (Contact.userLoginBean!=null){
                        ToastUtils.show(BindingMobileActivity.this, Contact.userLoginBean.getMsg());
                        if (Contact.userLoginBean.getStatus().equals("1")){
                            SharedUtils.setUserNamePwd(BindingMobileActivity.this,etPhone.getText().toString(),"".toString(),null);
                            intent=new Intent(BindingMobileActivity.this,HomeActivity.class);
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
        setContentView(R.layout.activity_binding_mobile);
    }

    @Override
    public void initViews() {
        btnBinding = (Button) findViewById(R.id.btnBinding);
        tvAgree = (TextView) findViewById(R.id.tvAgree);
        tvAgreement = (TextView) findViewById(R.id.tvAgreement);
        etPhone=(EditText)findViewById(R.id.etPhone);
    }

    @Override
    public void initDatas() {
        setTitle("绑定手机");
        platform=getIntent().getStringExtra("platform");
        username=getIntent().getStringExtra("username");
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnBinding.setOnClickListener(this);
        tvAgree.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvAgree:
                if (selectbool){
                    Agree(R.mipmap.icon_select);
                } else {
                    Agree(R.mipmap.icon_selected);
                }
                selectbool=!selectbool;
                break;
            case R.id.btnBinding:
                if (StringUtils.isEmpty(etPhone.getText().toString())){
                    ToastUtils.show(BindingMobileActivity.this,"请输入手机号");
                    return;
                }

                if (!selectbool){
                    ToastUtils.show(this,"请同意智盈外汇用户协议");
                    return;
                }

                params=new RequestParams();
                params.put("thirdParty.username",username);
                params.put("thirdParty.platform",platform);
                params.put("user.username",etPhone.getText().toString());
                post("user/mobile",params);
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
        if (requestname.equals("user/mobile")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
