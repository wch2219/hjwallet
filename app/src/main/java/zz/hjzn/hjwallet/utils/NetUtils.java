package zz.hjzn.hjwallet.utils;

/**
 * 接口
 */
public class NetUtils {
    private static final String BaseUrl = "http://47.97.124.61:8080/gscApi/";
//    private static final String BaseUrl = "http://192.168.101.40:8081/gscApi/";
    public static final String Login = BaseUrl+"d-app/API/userLogin";//登录
    public static final String GetAuth = BaseUrl + "d-app/API/getAuthCode";//获取验证码
    public static final String Register = BaseUrl + "d-app/API/userRegister";//注册
    public static final String GetPersionInfo = BaseUrl + "d-app/API/book/getUserBook";//余额
    public static final String PayGsc = BaseUrl + "d-app/ore/transferToOthers.do";//转账
    public static final String ForgetPwd = BaseUrl + "d-app/API/restPwd";
    public static final String CheckPayPwd = BaseUrl + "d-app/API/book/confirmPaypassword";//校验支付密码
    public static final String BillDesc = BaseUrl + "d-app/API/book/getBookRecordList";
    public static final String ChangePersion = BaseUrl + "d-app/API/user/update";//更改个人信息
    public static final String AddressGetUserInfo = BaseUrl + "d-app/API/book/getUserInfo";//根据地址获取个人信息
//    中创云合
    public static final String zhongchuangGetInfo = BaseUrl + "d-app/API/user/getUserExt";//获取持有币
    public static final String BiPrice = "http://www.btcb9.com/getDateByID.htm?scid=35";//币价格
    public static final String ZhongchuangRollout = BaseUrl + "/d-app/API/user/rollout";
    public static final String Parities = "http://op.juhe.cn/onebox/exchange/query?key=fd1bc25f9419c77876936995fd020952";//汇率兑换
    public static final String H5Video = "http://v.juhe.cn/wepiao/go?key=40c238959364045af29805645ab80ef5";//猫眼电影
    public static final String News = "http://v.juhe.cn/toutiao/index";//新闻头条
    public static final String Xiaohua = "http://v.juhe.cn/joke/content/list.php";//笑话
    public static String StartLuck = "http://web.juhe.cn:8080/constellation/getAll";//十二星座
}
