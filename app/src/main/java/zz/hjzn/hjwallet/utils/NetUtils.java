package zz.hjzn.hjwallet.utils;

/**
 * 接口
 */
public class NetUtils {
    private static final String BaseUrl = "http://47.97.124.61:8080/gscApi/";

    public static final String Login = BaseUrl+"d-app/API/userLogin";//登录
    public static String GetAuth = BaseUrl + "d-app/API/getAuthCode";//获取验证码
    public static String Register = BaseUrl + "d-app/API/userRegister";//注册
}
