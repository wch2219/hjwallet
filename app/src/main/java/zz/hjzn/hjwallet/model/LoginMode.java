package zz.hjzn.hjwallet.model;

public class LoginMode {

    /**
     * success : true
     * errCode : 10000
     * errDesc : 成功
     * result : {"bindMobile":"18637051978","sessionId":"DA312CB837174FEE080614C9F47B39B4.GBT_APP","secret":"0a97970fe90e69093792baad7140f3a6","email":null}
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
         * bindMobile : 18637051978
         * sessionId : DA312CB837174FEE080614C9F47B39B4.GBT_APP
         * secret : 0a97970fe90e69093792baad7140f3a6
         * email : null
         */

        private String bindMobile;
        private String sessionId;
        private String secret;
        private Object email;

        public String getBindMobile() {
            return bindMobile;
        }

        public void setBindMobile(String bindMobile) {
            this.bindMobile = bindMobile;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }
    }
}
