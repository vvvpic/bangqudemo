package com.bangqu.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bangqu.adapter.SelectAdapter;
import com.bangqu.base.activity.BaseActivity;
import com.bangqu.lib.R;
import com.longtu.base.util.StringUtils;

import java.util.List;

public class SelectActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lvSelects;

    private SelectAdapter selectAdapter;

    private List<String> listSelects;

    private String title;
    private int code;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_select2);
    }

    @Override
    public void initViews() {
        lvSelects=(ListView)findViewById(R.id.lvSelects);
    }

    @Override
    public void initDatas() {
        title=getIntent().getStringExtra("title");
        code=getIntent().getIntExtra("code",0);
        listSelects=getIntent().getStringArrayListExtra("selects");

        if (!StringUtils.isEmpty(title)) {
            setTitle(title);
        }

        selectAdapter=new SelectAdapter(listSelects,this);
        lvSelects.setAdapter(selectAdapter);
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        lvSelects.setOnItemClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        intent=new Intent();
        intent.putExtra("position",position);
        setResult(code,intent);
        finish();
    }
}
