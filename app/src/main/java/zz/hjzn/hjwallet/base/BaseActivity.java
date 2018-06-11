package zz.hjzn.hjwallet.base;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;

import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yinglan.keyboard.HideUtil;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.utils.LogUtils;
import zz.hjzn.hjwallet.utils.MyProgressLoading;
import zz.hjzn.hjwallet.utils.SpUtiles;


/**
 * 父类
 */
public abstract class BaseActivity<V,T extends Presenter<V>> extends AppCompatActivity implements ViewInterface {
    public T mPreenter;
    public Context ctx;
    public MyProgressLoading loading;
    public SharedPreferences sp;
    public Vibrator vibrator=null;
    public ImmersionBar immersionBar;
    public Unbinder unbinder;
    public Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreenter = createPresenter();
        HideUtil.init(this);
        ctx = this;
        gson = new Gson();
//        setImmer(R.color.trans);
        vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        loading = new MyProgressLoading(ctx, R.style.DialogStyle);
        sp = getSharedPreferences(SpUtiles.SP_Mode,MODE_PRIVATE);
        initView();
        unbinder = ButterKnife.bind(this);
        initData();
        initListener();
    }
    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();
    protected abstract T createPresenter();
    @Override
    public void showProgress() {
        if (loading != null) {
            loading.show();
        }
    }
    @Override
    public void dissProgress() {
        if (loading != null) {
            loading.dismiss();
        }
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showInterError() {

    }

    @Override
    public void showEntryView() {

    }

    @Override
    public void showData(String s) throws IOException {
        if (loading != null) {
            loading.dismiss();
        }
    }

    @Override
    public void onError() {
        if (loading != null) {
            loading.dismiss();
            Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void initTabBar(Toolbar toolbar,boolean isWhite){

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (isWhite) {
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_fanhui);
        }else{
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_jiantoubaise);
        }
        // immersionBar.statusBarDarkFont(true);
    }

    /**
     * 下拉刷新基本设置
     * @param refreshLayout
     * @param isLoadmore 是否启用下拉刷新
     */
    public void initrefresh (SmartRefreshLayout refreshLayout, boolean isLoadmore){
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        if (isLoadmore) {

            refreshLayout.setEnableLoadmore(true);//是否启用上拉加载功能
        }else{
            refreshLayout.setEnableLoadmore(false);//是否启用上拉加载功能
        }
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableAutoLoadmore(true);//是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setRefreshHeader(new WaterDropHeader(ctx));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new FalsifyFooter(ctx));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000);
                downOnRefresh();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore();
                loadmore();
            }
        });
    }

    /**
     * 下拉刷新
     */
    public void downOnRefresh(){

    }

    /**
     * 上拉加载更多
     */
    public void loadmore(){

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                HideUtil.hideSoftKeyboard(this);
                break;
        }

        return true;
    }
    public void wch(Object o){
        LogUtils.LogI(o);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    // 写一个广播的内部类，当收到动作时，结束activity
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this); // 这句话必须要写要不会报错，不写虽然能关闭，会报一堆错
            ((Activity) context).finish();
        }
    };

    public void register() {
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("base_finish_activity");
        registerReceiver(this.broadcastReceiver, filter); // 注册
    }

    public void close() {
        Intent intent = new Intent();
        intent.setAction("base_finish_activity"); // 说明动作
        sendBroadcast(intent);// 该函数用于发送广播
        finish();
    }

    public void setImmer(int color){
        ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
//                .statusBarColor(R.color.colorPrimary)     //状态栏颜色，不写默认透明色
                .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
                .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
                .statusBarAlpha(1.0f)  //状态栏透明度，不写默认0.0f
                .navigationBarAlpha(1.0f)  //导航栏透明度，不写默认0.0F
                .barAlpha(1.0f)  //状态栏和导航栏透明度，不写默认0.0f
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .fullScreen(false)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .setViewSupportTransformColor(toolbar) //设置支持view变色，支持一个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .addViewSupportTransformColor(toolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .statusBarView(view)  //解决状态栏和布局重叠问题
//                .fitsSystemWindows(false)    //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .statusBarColorTransform(color)  //状态栏变色后的颜色
//                .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
//                .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
//                .removeSupportView()  //移除通过setViewSupportTransformColor()方法指定的view
//                .removeSupportView(toolbar)  //移除指定view支持
//                .removeSupportAllView() //移除全部view支持
                .init();  //必须调用方可沉浸式
    }
}
