package zz.hjzn.hjwallet.utils;

public class RequestCode {
    public static final int SuccessCode = 10000;

    public static final int ErrorCode1 = 20000;//服务不可用

    public static final int ErrorCode2 = 20001;//鉴权失败

    public static final int ErrorCode3 = 20002;//访问权限过期

    public static final int ErrorCode4 = 20003;//请求频繁

    public static final int ErrorCode5 = 40001;//去缺少必须的参数

    public static final int ErrorCode6 = 40002;//非法参数

    public static final int ErrorCode7 = 40004;//业务处理失败

    public static final int ErrorCode8 = 40006;//权限不足
}
