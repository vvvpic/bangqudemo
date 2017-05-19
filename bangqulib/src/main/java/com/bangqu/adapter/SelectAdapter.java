package com.bangqu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bangqu.lib.R;
import com.longtu.base.util.StringUtils;

import java.util.List;

/**
 * 商品类目适配
 */
public class SelectAdapter extends BaseAdapter {

    private List<String> listSelects;
    private Context context;

    public SelectAdapter(List<String> listSelects, Context context){
        this.listSelects=listSelects;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listSelects.size();
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
        Category_item category_item;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.selectitem,null);
            category_item=new Category_item();
            category_item.tvName=(TextView)convertView.findViewById(R.id.tvName);
            convertView.setTag(category_item);
        }else {
            category_item= (Category_item) convertView.getTag();
        }

        if (!StringUtils.isEmpty(listSelects.get(position))){
            category_item.tvName.setText(listSelects.get(position));
        }

        return convertView;
    }

    class Category_item{
        TextView tvName;
    }
}
