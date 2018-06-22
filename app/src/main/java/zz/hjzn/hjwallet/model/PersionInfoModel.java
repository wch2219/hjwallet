package zz.hjzn.hjwallet.model;

import java.io.Serializable;

public class PersionInfoModel implements Serializable{


    /**
     * success : true
     * errCode : 10000
     * errDesc : 查询成功
     * result : {"portraitImgUrl":"http://47.97.124.61:8080/d-app/API/showImg/13107c29996744b4a95228c2511ab150.png","realName":"哈哈哈哈","bookCode":"2","itemName":null,"itemType":0,"createTime":1526098716000,"nickName":"记录","freezBalance":0,"walletAddress":"XQtc3ALjnYAdV2z4edE6jZ9VLBKR6D4YR4","bookBalance":8.2999,"userId":2711,"bookId":3610}
     */

    private boolean success;
    private int errCode;
    private String errDesc;
    private ResultBean result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * portraitImgUrl : http://47.97.124.61:8080/d-app/API/showImg/13107c29996744b4a95228c2511ab150.png
         * realName : 哈哈哈哈
         * bookCode : 2
         * itemName : null
         * itemType : 0
         * createTime : 1526098716000
         * nickName : 记录
         * freezBalance : 0.0
         * walletAddress : XQtc3ALjnYAdV2z4edE6jZ9VLBKR6D4YR4
         * bookBalance : 8.2999
         * userId : 2711
         * bookId : 3610
         */

        private String portraitImgUrl;
        private String realName;
        private String bookCode;
        private Object itemName;
        private int itemType;
        private long createTime;
        private String nickName;
        private double freezBalance;
        private String walletAddress;
        private double bookBalance;
        private int userId;
        private int bookId;

        public String getPortraitImgUrl() {
            return portraitImgUrl;
        }

        public void setPortraitImgUrl(String portraitImgUrl) {
            this.portraitImgUrl = portraitImgUrl;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getBookCode() {
            return bookCode;
        }

        public void setBookCode(String bookCode) {
            this.bookCode = bookCode;
        }

        public Object getItemName() {
            return itemName;
        }

        public void setItemName(Object itemName) {
            this.itemName = itemName;
        }

        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public double getFreezBalance() {
            return freezBalance;
        }

        public void setFreezBalance(double freezBalance) {
            this.freezBalance = freezBalance;
        }

        public String getWalletAddress() {
            return walletAddress;
        }

        public void setWalletAddress(String walletAddress) {
            this.walletAddress = walletAddress;
        }

        public double getBookBalance() {
            return bookBalance;
        }

        public void setBookBalance(double bookBalance) {
            this.bookBalance = bookBalance;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }
    }
}
