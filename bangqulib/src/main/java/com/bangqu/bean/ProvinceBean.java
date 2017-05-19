package com.bangqu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 唯图 on 2016/9/7.
 */
public class ProvinceBean implements Serializable {

    /**
     * status : 1
     * msg : 获取成功
     * version : 0
     * provinces : [{"id":110000,"version":0,"name":"北京市","venueSize":1},{"id":120000,"version":0,"name":"天津市","venueSize":1},{"id":130000,"version":0,"name":"河北省","venueSize":0},{"id":140000,"version":0,"name":"山西省","venueSize":0},{"id":150000,"version":0,"name":"内蒙古自治区","venueSize":0},{"id":210000,"version":0,"name":"辽宁省","venueSize":0},{"id":220000,"version":0,"name":"吉林省","venueSize":0},{"id":230000,"version":0,"name":"黑龙江省","venueSize":0},{"id":310000,"version":0,"name":"上海市","venueSize":0},{"id":320000,"version":0,"name":"江苏省","venueSize":1},{"id":330000,"version":0,"name":"浙江省","venueSize":0},{"id":340000,"version":0,"name":"安徽省","venueSize":0},{"id":350000,"version":0,"name":"福建省","venueSize":0},{"id":360000,"version":0,"name":"江西省","venueSize":0},{"id":370000,"version":0,"name":"山东省","venueSize":0},{"id":410000,"version":0,"name":"河南省","venueSize":0},{"id":420000,"version":0,"name":"湖北省","venueSize":0},{"id":430000,"version":0,"name":"湖南省","venueSize":0},{"id":440000,"version":0,"name":"广东省","venueSize":0},{"id":450000,"version":0,"name":"广西壮族自治区","venueSize":0},{"id":460000,"version":0,"name":"海南省","venueSize":0},{"id":500000,"version":0,"name":"重庆市","venueSize":0},{"id":510000,"version":0,"name":"四川省","venueSize":0},{"id":520000,"version":0,"name":"贵州省","venueSize":0},{"id":530000,"version":0,"name":"云南省","venueSize":0},{"id":540000,"version":0,"name":"西藏自治区","venueSize":0},{"id":610000,"version":0,"name":"陕西省","venueSize":0},{"id":620000,"version":0,"name":"甘肃省","venueSize":0},{"id":630000,"version":0,"name":"青海省","venueSize":0},{"id":640000,"version":0,"name":"宁夏回族自治区","venueSize":0},{"id":650000,"version":0,"name":"新疆维吾尔自治区","venueSize":0},{"id":710000,"version":0,"name":"台湾省","venueSize":0},{"id":810000,"version":0,"name":"香港特别行政区","venueSize":0},{"id":820000,"version":0,"name":"澳门特别行政区","venueSize":0}]
     */

    private String status;
    private String msg;
    private String version;
    /**
     * id : 110000
     * version : 0
     * name : 北京市
     * venueSize : 1
     */

    private List<CityBean> provinces;

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

    public List<CityBean> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<CityBean> provinces) {
        this.provinces = provinces;
    }

}
