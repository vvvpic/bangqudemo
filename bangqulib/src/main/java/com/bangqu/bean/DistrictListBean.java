package com.bangqu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/9/8.
 */
public class DistrictListBean implements Serializable {
    private String status;
    private String msg;
    private String version;
    /**
     * id : 110000
     * version : 0
     * name : 北京市
     * venueSize : 1
     */

    private List<CityBean> districts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<CityBean> getDistricts() {
        return districts;
    }

    public void setDistricts(List<CityBean> districts) {
        this.districts = districts;
    }
}
