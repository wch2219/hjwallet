package zz.hjzn.hjwallet.model;

import java.util.List;

public class BiollDescModel {

    /**
     * success : true
     * errCode : 10000
     * errDesc : 查询成功
     * result : [{"recordId":1914,"bookId":0,"bookCode":"2","userId":2461,"orderId":0,"changeValue":-10,"beforeChangeBalance":1150,"changeAction":5,"changeTime":1528705349000,"opId":0,"transationId":"6287aaa2c44841af84a16eda8aa5eadff8b95f6b0cbceda0ce3655cb4b1d22e5","remark":null,"changeValueStr":"-10","beforeChangeBalanceStr":"1150","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":"2018-06-11 16:22:29"},{"recordId":1672,"bookId":0,"bookCode":"2","userId":2461,"orderId":0,"changeValue":-1,"beforeChangeBalance":6,"changeAction":5,"changeTime":1525096560000,"opId":0,"transationId":"308fff59c3bcd5202c4085a7282dbda75ea38bfe5616196d923c99a8127a5519","remark":null,"changeValueStr":"-1","beforeChangeBalanceStr":"6","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":"2018-04-30 21:56:00"},{"recordId":1648,"bookId":0,"bookCode":"2","userId":2461,"orderId":0,"changeValue":-6,"beforeChangeBalance":6,"changeAction":5,"changeTime":1524630870000,"opId":0,"transationId":"3762f5cab0978dd4f577e790e5a55017c94666e8ae913c1f3d209896c1305880","remark":null,"changeValueStr":"-6","beforeChangeBalanceStr":"6","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":"2018-04-25 12:34:30"},{"recordId":1647,"bookId":0,"bookCode":"2","userId":2461,"orderId":0,"changeValue":-5,"beforeChangeBalance":7,"changeAction":5,"changeTime":1524625331000,"opId":0,"transationId":"c8b0918fc02144c6053eb4c1163cc34035f63c3484eb7fa4a2d97e008a1fb1c3","remark":null,"changeValueStr":"-5","beforeChangeBalanceStr":"7","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":"2018-04-25 11:02:11"},{"recordId":1920,"bookId":null,"bookCode":"2","userId":2461,"orderId":null,"changeValue":-1,"beforeChangeBalance":1141,"changeAction":5,"changeTime":null,"opId":null,"transationId":"4de91b4775f675d714b8b6f8eebe6085cf76e9ab84a9f277a707a42b8822ddde","remark":null,"changeValueStr":"-1","beforeChangeBalanceStr":"1141","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":""},{"recordId":1919,"bookId":null,"bookCode":"2","userId":2461,"orderId":null,"changeValue":-1,"beforeChangeBalance":1142,"changeAction":5,"changeTime":null,"opId":null,"transationId":"79959d60c89d4e66db159c2e018bfa34c4646358b8073f8a5bdc29273128ff69","remark":null,"changeValueStr":"-1","beforeChangeBalanceStr":"1142","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":""},{"recordId":1918,"bookId":null,"bookCode":"2","userId":2461,"orderId":null,"changeValue":-1,"beforeChangeBalance":1143,"changeAction":5,"changeTime":null,"opId":null,"transationId":"3e630de938fdbc56761e921aa5d2c7b579100e79c7a1a94e5bd15556d72d1b2d","remark":null,"changeValueStr":"-1","beforeChangeBalanceStr":"1143","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":""},{"recordId":1917,"bookId":null,"bookCode":"2","userId":2461,"orderId":null,"changeValue":-1,"beforeChangeBalance":1144,"changeAction":5,"changeTime":null,"opId":null,"transationId":"46b6728848c4ec61a2a426b1d74faf80acf54a9a9acbc235ea9afd20af710860","remark":null,"changeValueStr":"-1","beforeChangeBalanceStr":"1144","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":""},{"recordId":1916,"bookId":null,"bookCode":"2","userId":2461,"orderId":null,"changeValue":-1,"beforeChangeBalance":1145,"changeAction":5,"changeTime":null,"opId":null,"transationId":"c8444213cd7a24a841a99b15754e4c5b9689d37ed0c33a71f7eae2874ae128a6","remark":null,"changeValueStr":"-1","beforeChangeBalanceStr":"1145","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":""},{"recordId":1927,"bookId":null,"bookCode":"2","userId":2461,"orderId":null,"changeValue":-1,"beforeChangeBalance":1140,"changeAction":5,"changeTime":null,"opId":null,"transationId":"84a38138bab7b75674748ac21f72812e4218022a0547868d4e7f91f95a599c49","remark":null,"changeValueStr":"-1","beforeChangeBalanceStr":"1140","changeActionStr":"提现","orderType":0,"orderCode":null,"createTime":null,"orderName":null,"totalPrice":0,"billType":0,"billSubtype":0,"beginTime":null,"endTime":null,"busiType":0,"actionType":0,"changeTimeStr":""}]
     */

    private boolean success;
    private int errCode;
    private String errDesc;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * recordId : 1914
         * bookId : 0
         * bookCode : 2
         * userId : 2461
         * orderId : 0
         * changeValue : -10
         * beforeChangeBalance : 1150
         * changeAction : 5
         * changeTime : 1528705349000
         * opId : 0
         * transationId : 6287aaa2c44841af84a16eda8aa5eadff8b95f6b0cbceda0ce3655cb4b1d22e5
         * remark : null
         * changeValueStr : -10
         * beforeChangeBalanceStr : 1150
         * changeActionStr : 提现
         * orderType : 0
         * orderCode : null
         * createTime : null
         * orderName : null
         * totalPrice : 0
         * billType : 0
         * billSubtype : 0
         * beginTime : null
         * endTime : null
         * busiType : 0
         * actionType : 0
         * changeTimeStr : 2018-06-11 16:22:29
         */

        private int recordId;
        private int bookId;
        private String bookCode;
        private int userId;
        private int orderId;
        private int changeValue;
        private int beforeChangeBalance;
        private int changeAction;
        private long changeTime;
        private int opId;
        private String transationId;
        private Object remark;
        private String changeValueStr;
        private String beforeChangeBalanceStr;
        private String changeActionStr;
        private int orderType;
        private Object orderCode;
        private Object createTime;
        private Object orderName;
        private int totalPrice;
        private int billType;
        private int billSubtype;
        private Object beginTime;
        private Object endTime;
        private int busiType;
        private int actionType;
        private String changeTimeStr;

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public int getBookId() {
            return bookId;
        }

        public void setBookId(int bookId) {
            this.bookId = bookId;
        }

        public String getBookCode() {
            return bookCode;
        }

        public void setBookCode(String bookCode) {
            this.bookCode = bookCode;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getChangeValue() {
            return changeValue;
        }

        public void setChangeValue(int changeValue) {
            this.changeValue = changeValue;
        }

        public int getBeforeChangeBalance() {
            return beforeChangeBalance;
        }

        public void setBeforeChangeBalance(int beforeChangeBalance) {
            this.beforeChangeBalance = beforeChangeBalance;
        }

        public int getChangeAction() {
            return changeAction;
        }

        public void setChangeAction(int changeAction) {
            this.changeAction = changeAction;
        }

        public long getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(long changeTime) {
            this.changeTime = changeTime;
        }

        public int getOpId() {
            return opId;
        }

        public void setOpId(int opId) {
            this.opId = opId;
        }

        public String getTransationId() {
            return transationId;
        }

        public void setTransationId(String transationId) {
            this.transationId = transationId;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getChangeValueStr() {
            return changeValueStr;
        }

        public void setChangeValueStr(String changeValueStr) {
            this.changeValueStr = changeValueStr;
        }

        public String getBeforeChangeBalanceStr() {
            return beforeChangeBalanceStr;
        }

        public void setBeforeChangeBalanceStr(String beforeChangeBalanceStr) {
            this.beforeChangeBalanceStr = beforeChangeBalanceStr;
        }

        public String getChangeActionStr() {
            return changeActionStr;
        }

        public void setChangeActionStr(String changeActionStr) {
            this.changeActionStr = changeActionStr;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public Object getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(Object orderCode) {
            this.orderCode = orderCode;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getOrderName() {
            return orderName;
        }

        public void setOrderName(Object orderName) {
            this.orderName = orderName;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public int getBillType() {
            return billType;
        }

        public void setBillType(int billType) {
            this.billType = billType;
        }

        public int getBillSubtype() {
            return billSubtype;
        }

        public void setBillSubtype(int billSubtype) {
            this.billSubtype = billSubtype;
        }

        public Object getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(Object beginTime) {
            this.beginTime = beginTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public int getBusiType() {
            return busiType;
        }

        public void setBusiType(int busiType) {
            this.busiType = busiType;
        }

        public int getActionType() {
            return actionType;
        }

        public void setActionType(int actionType) {
            this.actionType = actionType;
        }

        public String getChangeTimeStr() {
            return changeTimeStr;
        }

        public void setChangeTimeStr(String changeTimeStr) {
            this.changeTimeStr = changeTimeStr;
        }
    }
}
