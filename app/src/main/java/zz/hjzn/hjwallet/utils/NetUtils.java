package zz.hjzn.hjwallet.utils;

/**
 * 接口
 */
public class NetUtils {
    private static final String BaseUrl = "http://47.97.124.61:8080/gscApi/";

    public static final String Login = BaseUrl+"d-app/API/userLogin";//登录
    public static final String GetAuth = BaseUrl + "d-app/API/getAuthCode";//获取验证码
    public static final String Register = BaseUrl + "d-app/API/userRegister";//注册
    public static final String GetPersionInfo = BaseUrl + "d-app/API/book/getUserBook";//余额
    public static final String PayGsc = BaseUrl + "d-app/ore/transferToOthers.do";//转账
    public static final String ForgetPwd = BaseUrl + "d-app/API/restPwd";
    public static final String CheckPayPwd = BaseUrl + "d-app/API/book/confirmPaypassword";//校验支付密码
    public static final String BillDesc = BaseUrl + "d-app/API/book/getBookRecordList";
}
