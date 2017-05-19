package com.bangqu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 豚趣 on 2017/4/25.
 */
public class TeacherSearchBean implements Serializable {

    /**
     * status : 1
     * msg : 获取成功
     * teachers : [{"id":1,"addTime":"2017-03-31 23:00:33","updateTime":"2017-03-31 23:00:35","no":"1","name":"曹洋","description":"我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。","price":2,"point":0,"monthTurnover":1,"favoriteSize":1,"orderSize":0,"customerSize":0,"state":1,"user":{"id":2,"version":27,"addTime":"2017-03-08 07:13:14","updateTime":"2017-03-08 07:13:19","username":"18068306278","nickname":null,"age":25,"photo":"http://7xv6yr.com2.z0.glb.qiniucdn.com/FueMejLTai8lkYefvyKyPCmmLjU1","realname":"曹洋","male":true,"intro":null,"clientId":"","deviceToken":"","easemobId":""}},{"id":2,"addTime":"2017-04-12 05:55:40","updateTime":"2017-04-12 05:55:43","no":"2","name":"小学生孙涛","description":"我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。我爱小学生，我讨厌小学生。","price":1,"point":0,"monthTurnover":1,"favoriteSize":1,"orderSize":0,"customerSize":0,"state":1,"user":{"id":3,"version":0,"addTime":"2017-04-06 15:13:36","updateTime":"2017-04-06 15:13:36","username":"13771199261","nickname":null,"age":0,"photo":"","realname":"","male":null,"intro":null,"clientId":"","deviceToken":"","easemobId":"cf1d44de1538b4eccf475927cb2a2f14"}}]
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
     * price : 2.0
     * point : 0.0
     * monthTurnover : 1.0
     * favoriteSize : 1
     * orderSize : 0.0
     * customerSize : 0.0
     * state : 1
     * user : {"id":2,"version":27,"addTime":"2017-03-08 07:13:14","updateTime":"2017-03-08 07:13:19","username":"18068306278","nickname":null,"age":25,"photo":"http://7xv6yr.com2.z0.glb.qiniucdn.com/FueMejLTai8lkYefvyKyPCmmLjU1","realname":"曹洋","male":true,"intro":null,"clientId":"","deviceToken":"","easemobId":""}
     */

    private List<TeachersBean> teachers;

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

    public List<TeachersBean> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeachersBean> teachers) {
        this.teachers = teachers;
    }

    public static class TeachersBean implements Serializable{
        private int id;
        private String addTime;
        private String updateTime;
        private String no;
        private String name;
        private String description;
        private double price;
        private double point;
        private double monthTurnover;
        private int favoriteSize;
        private double orderSize;
        private double customerSize;
        private int state;
        /**
         * id : 2
         * version : 27
         * addTime : 2017-03-08 07:13:14
         * updateTime : 2017-03-08 07:13:19
         * username : 18068306278
         * nickname : null
         * age : 25
         * photo : http://7xv6yr.com2.z0.glb.qiniucdn.com/FueMejLTai8lkYefvyKyPCmmLjU1
         * realname : 曹洋
         * male : true
         * intro : null
         * clientId :
         * deviceToken :
         * easemobId :
         */

        private UserBean user;

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

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPoint() {
            return point;
        }

        public void setPoint(double point) {
            this.point = point;
        }

        public double getMonthTurnover() {
            return monthTurnover;
        }

        public void setMonthTurnover(double monthTurnover) {
            this.monthTurnover = monthTurnover;
        }

        public int getFavoriteSize() {
            return favoriteSize;
        }

        public void setFavoriteSize(int favoriteSize) {
            this.favoriteSize = favoriteSize;
        }

        public double getOrderSize() {
            return orderSize;
        }

        public void setOrderSize(double orderSize) {
            this.orderSize = orderSize;
        }

        public double getCustomerSize() {
            return customerSize;
        }

        public void setCustomerSize(double customerSize) {
            this.customerSize = customerSize;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Serializable{
            private int id;
            private int version;
            private String addTime;
            private String updateTime;
            private String username;
            private Object nickname;
            private int age;
            private String photo;
            private String realname;
            private boolean male;
            private Object intro;
            private String clientId;
            private String deviceToken;
            private String easemobId;

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

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
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

            public boolean isMale() {
                return male;
            }

            public void setMale(boolean male) {
                this.male = male;
            }

            public Object getIntro() {
                return intro;
            }

            public void setIntro(Object intro) {
                this.intro = intro;
            }

            public String getClientId() {
                return clientId;
            }

            public void setClientId(String clientId) {
                this.clientId = clientId;
            }

            public String getDeviceToken() {
                return deviceToken;
            }

            public void setDeviceToken(String deviceToken) {
                this.deviceToken = deviceToken;
            }

            public String getEasemobId() {
                return easemobId;
            }

            public void setEasemobId(String easemobId) {
                this.easemobId = easemobId;
            }
        }
    }
}
