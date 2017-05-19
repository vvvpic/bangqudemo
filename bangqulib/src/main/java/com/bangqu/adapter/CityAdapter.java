package com.bangqu.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bangqu.bean.CityBean;
import com.bangqu.lib.R;
import com.longtu.base.util.StringUtils;

import java.util.List;

/**
 * Created by 唯图 on 2016/9/8.
 */
public class CityAdapter extends BaseAdapter {

    private List<CityBean> listcitys;
    private Context context;

    private int grade=0;

    public void setGrade( int grade){
        this.grade=grade;
    }

    public CityAdapter(List<CityBean> listcitys, Context context){
        this.listcitys=listcitys;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listcitys.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        City_item city_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.city_item,null);
            city_item=new City_item();
            city_item.tvCity=(TextView)convertView.findViewById(R.id.tvCity);
            convertView.setTag(city_item);
        }else {
            city_item= (City_item) convertView.getTag();
        }

        if (!StringUtils.isEmpty(listcitys.get(position).getName())){
            city_item.tvCity.setText(listcitys.get(position).getName());
        }

        Drawable drawable=context.getResources().getDrawable(R.mipmap.icon_station_narrowright);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        if(grade==0||grade==1){
            city_item.tvCity.setCompoundDrawables(null, null, drawable, null); //设置左图标
        }else {
            city_item.tvCity.setCompoundDrawables(null, null, null, null); //设置左图标
        }

        return convertView;
    }

    class City_item{
        TextView tvCity;
    }
}
