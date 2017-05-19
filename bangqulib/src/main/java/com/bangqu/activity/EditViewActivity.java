package com.bangqu.activity;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bangqu.base.activity.BaseActivity;
import com.bangqu.lib.R;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;

public class EditViewActivity extends BaseActivity {

    private EditText etContent;

    private TextView tvRight;
    private String title;
    private int code;
    private int number;
    private String content;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_edit_view);

    }

    @Override
    public void initViews() {
        etContent=(EditText)findViewById(R.id.etContent);
        tvRight=(TextView)findViewById(R.id.tvRight);
    }

    @Override
    public void initDatas() {
        title=getIntent().getStringExtra("title");
        content=getIntent().getStringExtra("content");
        code=getIntent().getIntExtra("code",0);
        number=getIntent().getIntExtra("number",0);
        setTitle("填写"+title);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        etContent.setHint("请输入"+title);

        if (!StringUtils.isEmpty(content)){
            etContent.setText(content);
            etContent.setSelection(content.length());
        }

        if (number==1){
            etContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        tvRight.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
      if (v.getId()==R.id.tvRight){
          if (StringUtils.isEmpty(etContent.getText().toString())){
              ToastUtils.show(this,"请输入"+title);
              return;
          }
          intent=new Intent();
          intent.putExtra("content",etContent.getText().toString());
          setResult(code,intent);
          finish();
      }
    }
}
