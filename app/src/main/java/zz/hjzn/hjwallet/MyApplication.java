package zz.hjzn.hjwallet;


import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import com.lzy.okgo.OkGo;
import com.tencent.bugly.Bugly;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import zz.hjzn.hjwallet.model.PersionInfoModel;
import zz.hjzn.hjwallet.service.MyReceiver;
import zz.hjzn.hjwallet.utils.SpUtiles;

public class MyApplication extends Application {

    public static String EXTRAS = "";
    public static PersionInfoModel persionInfoModel;

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
        Bugly.init(getApplicationContext(), "0f876e9e3b", false);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JPushInterface.setLatestNotificationNumber(this, 3);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        MyReceiver mReceiver = new MyReceiver();
        registerReceiver(mReceiver, intentFilter);
        SharedPreferences sp = getSharedPreferences(SpUtiles.SP_Mode, Context.MODE_PRIVATE);
        String sessionId = sp.getString(SpUtiles.sessionId, "");
        if (sessionId != null) {
            Set<String> tags = new HashSet<>();
            tags.add(sessionId);
            JPushInterface.setTags(getApplicationContext(), 1, tags);

        }

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
