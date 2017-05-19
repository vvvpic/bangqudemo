package com.bangqu.bean;

import java.io.Serializable;

/**
 * Created by 豚趣 on 2016/12/23.
 */
public class BankMineBean implements Serializable {

    /**
     * status : 1
     * msg : 获取成功
     * bank : {"id":3,"addTime":"2016-12-10 17:31:23","updateTime":"2016-12-10 17:31:23","bankIcon":"http://img.yinwan.bangqu.com/abc.png","bankName":"中国农业银行","cardNo":"1234567890123456789","accountName":"嘘嘘嘘","mobile":"13771199261"}
     */

    private String status;
    private String msg;
    /**
     * id : 3
     * addTime : 2016-12-10 17:31:23
     * updateTime : 2016-12-10 17:31:23
     * bankIcon : http://img.yinwan.bangqu.com/abc.png
     * bankName : 中国农业银行
     * cardNo : 1234567890123456789
     * accountName : 嘘嘘嘘
     * mobile : 13771199261
     */

    private BankBean bank;

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

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public static class BankBean implements Serializable {
        private int id;
        private String addTime;
        private String updateTime;
        private String bankIcon;
        private String bankName;
        private String cardNo;
        private String accountName;
        private String mobile;

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

        public String getBankIcon() {
            return bankIcon;
        }

        public void setBankIcon(String bankIcon) {
            this.bankIcon = bankIcon;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
