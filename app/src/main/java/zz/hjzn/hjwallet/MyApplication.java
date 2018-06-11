package zz.hjzn.hjwallet;


import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.lzy.okgo.OkGo;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import zz.hjzn.hjwallet.model.PersionInfoModel;
import zz.hjzn.hjwallet.service.MyReceiver;

public class MyApplication extends Application {

    public static String EXTRAS = "";
    public static PersionInfoModel persionInfoModel;

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JPushInterface.setLatestNotificationNumber(this, 3);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        MyReceiver mReceiver = new MyReceiver();
        registerReceiver(mReceiver, intentFilter);

//        String user_id = sp.getString(com.dlwx.wisdomschool.utiles.SpUtiles.Userid, "");
//        if (user_id != null) {
            Set<String> tags = new HashSet<>();
            tags.add("123456");
            JPushInterface.setTags(getApplicationContext(), 1, tags);
//        }
    }
    //    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        // you must install multiDex whatever tinker is installed!
//        MultiDex.install(base);
//        // 安装tinker
//        Beta.installTinker();
//    }
}
