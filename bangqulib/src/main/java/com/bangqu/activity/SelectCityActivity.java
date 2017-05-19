package com.bangqu.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.bangqu.adapter.CityAdapter;
import com.bangqu.base.activity.BaseActivity;
import com.bangqu.bean.CityListBean;
import com.bangqu.bean.DistrictListBean;
import com.bangqu.bean.ProvinceBean;
import com.bangqu.lib.R;
import com.bangqu.utils.Contact;
import com.loopj.android.http.RequestParams;

public class SelectCityActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView lvCitys;

    private Message msg;

    private CityAdapter cityAdapter;
    private int grade=0;
    private CityListBean cityListBean;
    private DistrictListBean districtListBean;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Contact.provinceBean= JSONObject.parseObject(msg.obj.toString(), ProvinceBean.class);
                    if (Contact.provinceBean!=null&&Contact.provinceBean.getStatus().equals("1")){
                        cityAdapter=new CityAdapter(Contact.provinceBean.getProvinces(),SelectCityActivity.this);
                        grade=0;
                        cityAdapter.setGrade(grade);
                        lvCitys.setAdapter(cityAdapter);
                    }
                    break;
                case 2:
                    cityListBean= JSONObject.parseObject(msg.obj.toString(),CityListBean.class);
                    if (cityListBean!=null&&cityListBean.getStatus().equals("1")){
                        cityAdapter=new CityAdapter(cityListBean.getCities(),SelectCityActivity.this);
                        cityAdapter.setGrade(grade);
                        lvCitys.setAdapter(cityAdapter);
                    }
                    break;
                case 3:
                    districtListBean= JSONObject.parseObject(msg.obj.toString(),DistrictListBean.class);
                    if (districtListBean!=null&&districtListBean.getStatus().equals("1")){
                        cityAdapter=new CityAdapter(districtListBean.getDistricts(),SelectCityActivity.this);
                        cityAdapter.setGrade(grade);
                        lvCitys.setAdapter(cityAdapter);
                    }
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_select_city);
    }

    @Override
    public void initViews() {
        lvCitys=(ListView)findViewById(R.id.lvCitys);
        ivBack=(ImageView)findViewById(R.id.ivBack);

    }

    @Override
    public void initDatas() {
        setTitle("选择城市");
        if(Contact.provinceBean==null) {
            otherPost("http://api.xianding.daoqun.com/","province/list", null);
        }else {
            cityAdapter=new CityAdapter(Contact.provinceBean.getProvinces(),SelectCityActivity.this);
            grade=0;
            cityAdapter.setGrade(grade);
            lvCitys.setAdapter(cityAdapter);
        }

    }

    @Override
    public void setDatas() {

    }

    @Override
    public void setListener() {
        lvCitys.setOnItemClickListener(this);
        rlBack.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.rlBack){
                back();
        }
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("province/list")){
            msg.what=1;
        }else if (requestname.equals("city/list")){
            msg.what=2;
        }else if (requestname.equals("district/list")){
            msg.what=3;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }

    private void back(){
        if (grade==2){
            grade=1;
            if (cityListBean!=null&&cityListBean.getStatus().equals("1")){
                cityAdapter=new CityAdapter(cityListBean.getCities(),SelectCityActivity.this);
                cityAdapter.setGrade(grade);
                lvCitys.setAdapter(cityAdapter);
            }
        }else if (grade==1){
            if (Contact.provinceBean!=null&&Contact.provinceBean.getStatus().equals("1")){
                cityAdapter=new CityAdapter(Contact.provinceBean.getProvinces(),SelectCityActivity.this);
                grade=0;
                cityAdapter.setGrade(grade);
                lvCitys.setAdapter(cityAdapter);
            }
        }else {
            finish();
        }
    }

    private int provincepos=0;
    private int citypos=0;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (grade == 0) {
            provincepos=position;
            grade=1;
            params=new RequestParams();
            params.put("query.provinceId",Contact.provinceBean.getProvinces().get(position).getId());
            otherPost("http://api.xianding.daoqun.com/","city/list", params);
        }else if (grade==1){
            citypos=position;
            grade=2;
            params=new RequestParams();
            params.put("query.cityId",cityListBean.getCities().get(position).getId());
            otherPost("http://api.xianding.daoqun.com/","district/list", params);
        }else if (grade==2){
            intent=new Intent();
            intent.putExtra("province",Contact.provinceBean.getProvinces().get(provincepos).getId());
            intent.putExtra("city",cityListBean.getCities().get(citypos).getId());
            intent.putExtra("district",districtListBean.getDistricts().get(position).getId());
            intent.putExtra("cityname",Contact.provinceBean.getProvinces().get(provincepos).getName()+
                    cityListBean.getCities().get(citypos).getName()+districtListBean.getDistricts().get(position).getName());
            intent.putExtra("districtname",districtListBean.getDistricts().get(position).getName());
            setResult(AREA,intent);
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            back();
        }
        return false;
    }
}
