package com.bangqu.api;

import com.bangqu.bean.ClassfyBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 豚趣 on 2017/3/23.
 */
public interface ApiAskService<T> {
    //获取TabLayout上的数据
    @GET("api/lore/classify")
    Call<T> getClassfyData();
}
