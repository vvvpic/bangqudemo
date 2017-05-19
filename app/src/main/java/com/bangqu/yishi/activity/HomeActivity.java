package com.bangqu.yishi.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.bangqu.base.activity.BaseActivity;
import com.bangqu.bean.ShareBean;
import com.bangqu.view.ShareDialog;
import com.bangqu.yishi.R;
import com.bangqu.yishi.fragment.MsgFragment;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

public class HomeActivity extends BaseActivity {

    private Button btnMsg;
    private Button btnCustomer;
    private Button btnManager;
    private Button btnMy;
    private RelativeLayout rlContent;

    private Fragment[] fragments;
    private Button[] Tab;
    private MsgFragment msgFragment;
    private int currentTabIndex;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initViews() {
        btnMsg = (Button) findViewById(R.id.btnMsg);
        btnCustomer = (Button) findViewById(R.id.btnCustomer);
        btnManager = (Button) findViewById(R.id.btnManager);
        btnMy = (Button) findViewById(R.id.btnMy);
        rlContent = (RelativeLayout) findViewById(R.id.rlContent);
    }

    @Override
    public void initDatas() {
        Tab=new Button[]{btnMsg,btnCustomer,btnManager,btnMy};
        msgFragment=new MsgFragment();
        fragments = new Fragment[] {msgFragment, msgFragment, msgFragment,msgFragment};
        setReplace(0);
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        btnMsg.setOnClickListener(this);
        btnCustomer.setOnClickListener(this);
        btnManager.setOnClickListener(this);
        btnMy.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMsg:
                setReplace(0);
                break;
            case R.id.btnCustomer:
                setReplace(1);
                break;
            case R.id.btnManager:
                setReplace(2);
                break;
            case R.id.btnMy:
                setReplace(3);
                break;
        }
    }

    @Override
    public void setReplace(int index) {
        transaction=fragmentManager.beginTransaction();
        if (!fragments[index].isAdded()){
            transaction.add(R.id.rlContent, fragments[index]);
        }

        if (currentTabIndex>-1){
            transaction.hide(fragments[currentTabIndex]);
            Tab[currentTabIndex].setSelected(false);
            Tab[currentTabIndex].setTextColor(getResources().getColor(R.color.tar_n));
        }

        transaction.show(fragments[index]).commit();
        Tab[index].setSelected(true);
        Tab[index].setTextColor(getResources().getColor(R.color.tar_p));
        currentTabIndex=index;
    }

}
