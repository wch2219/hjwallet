package zz.hjzn.hjwallet;


import android.app.Application;

import com.lzy.okgo.OkGo;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
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
