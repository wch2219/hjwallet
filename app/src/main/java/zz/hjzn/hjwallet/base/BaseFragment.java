package zz.hjzn.hjwallet.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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


public abstract class BaseFragment<V,T extends Presenter<V>> extends Fragment implements ViewInterface {

    public Context ctx;
    public T mPreenter;
    public MyProgressLoading loading;
    public SharedPreferences sp;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ctx = getActivity();
        View view = inflater.inflate(getLayoutID(),null);
        mPreenter = createPresenter();
        loading = new MyProgressLoading(ctx, R.style.DialogStyle);
        sp = ctx.getSharedPreferences(SpUtiles.SP_Mode,Context.MODE_PRIVATE);
        HideUtil.init(getActivity());
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        initListener();
        return view;
    }
    /**
     * 获得布局id
     * @return
     */
    public abstract int getLayoutID();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();
    protected abstract T createPresenter();
    @Override
    public void showProgress() {
        if (loading != null&&!loading.isShowing()) {
            loading.show();
        }
    }

    @Override
    public void dissProgress() {
        if (loading != null&&loading.isShowing()) {
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

    }

    @Override
    public void onError() {

    }
    public void wch(Object o){
        LogUtils.LogI(o);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
}
