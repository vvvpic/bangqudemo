package com.bangqu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 豚趣 on 2016/12/15.
 */
public class UserLoginBean implements Serializable {

    /**
     * status : 1
     * msg : 登录成功
     * teacher : {"id":1,"addTime":"2017-03-31 23:00:33","updateTime":"2017-03-31 23:00:35","no":"1","name":"曹洋","description":"我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。","price":2,"point":1,"monthTurnover":0,"favoriteSize":1,"orderSize":2,"customerSize":0,"state":1,"enabled":true}
     * user : {"id":2,"version":35,"addTime":"2017-03-08 07:13:14","updateTime":"2017-03-08 07:13:19","username":"18068306278","nickname":"曹洋","password":"$2a$10$6aLYTbA3A3Fr7ZnGgXY5P.S2W.RXcOU157oIgkL4p4YWcC.BLebMu","confirmPassword":null,"age":25,"photo":"http://oonc77hep.bkt.clouddn.com/2017/05/09/1494309619179","realname":"曹洋","email":null,"male":true,"birthday":"2017-02-01","constellation":1,"birthAttrib":1,"bloodType":1,"hobby":null,"marital":1,"intro":"啊呜啊呜啊呜，狂吃狂吃狂吃海喝醉后岂有此想法了吧啦啦噜啦啦噜","height":null,"weight":null,"ifVerify":null,"tags":null,"teacherOrderSize":0,"clockin":0,"alipay":null,"weixin":null,"province":"320000","city":"320200","district":"320205","clientId":null,"deviceToken":null,"easemobId":null,"roles":[],"authorities":[],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"roleList":[]}
     * accessToken : {"id":1,"addTime":"2017-03-08 07:31:17","updateTime":"2017-04-21 17:47:19","accessToken":"c1883bc434c8477564697a49887","expiresIn":2592000000,"refreshToken":"cfd07063e49a439ca75e03bfd5aaa"}
     */

    private String status;
    private String msg;
    /**
     * id : 1
     * addTime : 2017-03-31 23:00:33
     * updateTime : 2017-03-31 23:00:35
     * no : 1
     * name : 曹洋
     * description : 我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。
     * price : 2
     * point : 1
     * monthTurnover : 0
     * favoriteSize : 1
     * orderSize : 2
     * customerSize : 0
     * state : 1
     * enabled : true
     */


    private UserBean user;
    /**
     * id : 1
     * addTime : 2017-03-08 07:31:17
     * updateTime : 2017-04-21 17:47:19
     * accessToken : c1883bc434c8477564697a49887
     * expiresIn : 2592000000
     * refreshToken : cfd07063e49a439ca75e03bfd5aaa
     */

    private AccessTokenBean accessToken;
    public boolean type;

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public AccessTokenBean getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessTokenBean accessToken) {
        this.accessToken = accessToken;
    }

    public static class UserBean {
        private int id;
        private int version;
        private String addTime;
        private String updateTime;
        private String username;
        private String nickname;
        private String password;
        private Object confirmPassword;
        private int age;
        private String photo;
        private String realname;
        private Object email;
        private boolean male;
        private String birthday;
        private int constellation;
        private int birthAttrib;
        private int bloodType;
        private Object hobby;
        private int marital;
        private String intro;
        private Object height;
        private Object weight;
        private Object ifVerify;
        private Object tags;
        private int teacherOrderSize;
        private int clockin;
        private Object alipay;
        private Object weixin;
        private String province;
        private String city;
        private String district;
        private Object clientId;
        private Object deviceToken;
        private Object easemobId;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private List<?> roles;
        private List<?> authorities;
        private List<?> roleList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(Object confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public boolean isMale() {
            return male;
        }

        public void setMale(boolean male) {
            this.male = male;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getConstellation() {
            return constellation;
        }

        public void setConstellation(int constellation) {
            this.constellation = constellation;
        }

        public int getBirthAttrib() {
            return birthAttrib;
        }

        public void setBirthAttrib(int birthAttrib) {
            this.birthAttrib = birthAttrib;
        }

        public int getBloodType() {
            return bloodType;
        }

        public void setBloodType(int bloodType) {
            this.bloodType = bloodType;
        }

        public Object getHobby() {
            return hobby;
        }

        public void setHobby(Object hobby) {
            this.hobby = hobby;
        }

        public int getMarital() {
            return marital;
        }

        public void setMarital(int marital) {
            this.marital = marital;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public Object getHeight() {
            return height;
        }

        public void setHeight(Object height) {
            this.height = height;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Object getIfVerify() {
            return ifVerify;
        }

        public void setIfVerify(Object ifVerify) {
            this.ifVerify = ifVerify;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(Object tags) {
            this.tags = tags;
        }

        public int getTeacherOrderSize() {
            return teacherOrderSize;
        }

        public void setTeacherOrderSize(int teacherOrderSize) {
            this.teacherOrderSize = teacherOrderSize;
        }

        public int getClockin() {
            return clockin;
        }

        public void setClockin(int clockin) {
            this.clockin = clockin;
        }

        public Object getAlipay() {
            return alipay;
        }

        public void setAlipay(Object alipay) {
            this.alipay = alipay;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
            this.weixin = weixin;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public Object getClientId() {
            return clientId;
        }

        public void setClientId(Object clientId) {
            this.clientId = clientId;
        }

        public Object getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(Object deviceToken) {
            this.deviceToken = deviceToken;
        }

        public Object getEasemobId() {
            return easemobId;
        }

        public void setEasemobId(Object easemobId) {
            this.easemobId = easemobId;
        }

        public boolean isAccountNonExpired() {
            return accountNonExpired;
        }

        public void setAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
        }

        public boolean isAccountNonLocked() {
            return accountNonLocked;
        }

        public void setAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
        }

        public boolean isCredentialsNonExpired() {
            return credentialsNonExpired;
        }

        public void setCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }

        public List<?> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(List<?> authorities) {
            this.authorities = authorities;
        }

        public List<?> getRoleList() {
            return roleList;
        }

        public void setRoleList(List<?> roleList) {
            this.roleList = roleList;
        }
    }

    public static class AccessTokenBean {
        private int id;
        private String addTime;
        private String updateTime;
        private String accessToken;
        private long expiresIn;
        private String refreshToken;

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

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public long getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }
}
