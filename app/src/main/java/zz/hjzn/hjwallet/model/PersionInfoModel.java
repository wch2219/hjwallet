package zz.hjzn.hjwallet.model;

public class PersionInfoModel {

    /**
     * success : true
     * errCode : 10000
     * errDesc : 查询成功
     * result : {"bookCode":"2","itemName":null,"itemType":0,"createTime":1526098716000,"freezBalance":0,"walletAddress":"XQtc3ALjnYAdV2z4edE6jZ9VLBKR6D4YR4","bookBalance":0,"userId":2711,"bookId":3610}
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

    public static class ResultBean {
        /**
         * bookCode : 2
         * itemName : null
         * itemType : 0
         * createTime : 1526098716000
         * freezBalance : 0.0
         * walletAddress : XQtc3ALjnYAdV2z4edE6jZ9VLBKR6D4YR4
         * bookBalance : 0.0
         * userId : 2711
         * bookId : 3610
         */

        private String bookCode;
        private Object itemName;
        private int itemType;
        private long createTime;
        private double freezBalance;
        private String walletAddress;
        private double bookBalance;
        private int userId;
        private int bookId;

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
