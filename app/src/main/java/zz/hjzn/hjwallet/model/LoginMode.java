package zz.hjzn.hjwallet.model;

public class LoginMode {


    /**
     * success : true
     * errCode : 10000
     * errDesc : 成功
     * result : {"bindMobile":null,"sessionId":"E967B78F76CB96B91F54187B66B4D8B4.GBT_APP","secret":"173638366e84122aaf822f4253610aee","regType":3,"email":null,"loginSource":"zhongchuangyunhe"}
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
         * bindMobile : null
         * sessionId : E967B78F76CB96B91F54187B66B4D8B4.GBT_APP
         * secret : 173638366e84122aaf822f4253610aee
         * regType : 3
         * email : null
         * loginSource : zhongchuangyunhe
         */

        private Object bindMobile;
        private String sessionId;
        private String secret;
        private int regType;
        private Object email;
        private String loginSource;

        public Object getBindMobile() {
            return bindMobile;
        }

        public void setBindMobile(Object bindMobile) {
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

        public int getRegType() {
            return regType;
        }

        public void setRegType(int regType) {
            this.regType = regType;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getLoginSource() {
            return loginSource;
        }

        public void setLoginSource(String loginSource) {
            this.loginSource = loginSource;
        }
    }
}
