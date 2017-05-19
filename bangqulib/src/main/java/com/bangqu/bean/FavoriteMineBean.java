package com.bangqu.bean;

import java.io.Serializable;

/**
 * Created by 豚趣 on 2017/5/16.
 */
public class FavoriteMineBean implements Serializable {

    /**
     * status : 1
     * msg : 获取成功
     * favorite : {"id":11,"addTime":"2017-05-16 15:00:52","updateTime":"2017-05-16 15:22:31","enabled":true}
     */

    private String status;
    private String msg;
    /**
     * id : 11
     * addTime : 2017-05-16 15:00:52
     * updateTime : 2017-05-16 15:22:31
     * enabled : true
     */

    private FavoriteBean favorite;

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

    public FavoriteBean getFavorite() {
        return favorite;
    }

    public void setFavorite(FavoriteBean favorite) {
        this.favorite = favorite;
    }

    public static class FavoriteBean {
        private int id;
        private String addTime;
        private String updateTime;
        private boolean enabled;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
