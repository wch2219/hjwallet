package zz.hjzn.hjwallet.model;

public class ZhongchuangBean {

    /**
     * success : true
     * errCode : 10000
     * errDesc : 查询成功
     * result : {"id":113,"userId":2884,"holdGscNum":0,"nickName":"郭建明"}
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
         * id : 113
         * userId : 2884
         * holdGscNum : 0.0
         * nickName : 郭建明
         */

        private int id;
        private int userId;
        private double holdGscNum;
        private String nickName;
        private double gscNum;

        public double getGscNum() {
            return gscNum;
        }

        public void setGscNum(double gscNum) {
            this.gscNum = gscNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getHoldGscNum() {
            return holdGscNum;
        }

        public void setHoldGscNum(double holdGscNum) {
            this.holdGscNum = holdGscNum;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
