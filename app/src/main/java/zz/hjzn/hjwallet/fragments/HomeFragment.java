package zz.hjzn.hjwallet.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zz.hjzn.hjwallet.NewsActivity;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.activitys.EntryOtherActivity;
import zz.hjzn.hjwallet.activitys.MyReceiptCodeActivity;
import zz.hjzn.hjwallet.activitys.ParitiesActivity;
import zz.hjzn.hjwallet.activitys.ShopActivity;
import zz.hjzn.hjwallet.activitys.StartActivity;
import zz.hjzn.hjwallet.activitys.StartPaymentActivity;
import zz.hjzn.hjwallet.activitys.WebActivity;
import zz.hjzn.hjwallet.activitys.XiaohuaActivity;
import zz.hjzn.hjwallet.adapter.LifeAdapter;
import zz.hjzn.hjwallet.base.BaseFragment;
import zz.hjzn.hjwallet.base.BaseRecrviewAdapter;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.Amap.AmapUtils;
import zz.hjzn.hjwallet.utils.IntentTag;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.SpUtiles;
import zz.hjzn.hjwallet.zxing.CaptureActivity;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements BaseRecrviewAdapter.OnItemClickListener {


    @BindView(R.id.ll_saoma)
    LinearLayout llSaoma;
    @BindView(R.id.ll_fukuan)
    LinearLayout llFukuan;
    @BindView(R.id.ll_shoukuan)
    LinearLayout llShoukuan;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.rl_xiaohua)
    RelativeLayout rl_xiaohua;
    @BindView(R.id.iv_xingzuo)
    ImageView ivXingzuo;
    @BindView(R.id.tv_address)
    TextView tv_address;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        GridLayoutManager manager = new GridLayoutManager(ctx, 4);
        rvList.setLayoutManager(manager);
        LifeAdapter lifeAdapter = new LifeAdapter(ctx);
        lifeAdapter.setOnItemClickListener(this);
        rvList.setAdapter(lifeAdapter);
    }

    @Override
    protected void initData() {
        AmapUtils.initialization(ctx);
        AmapUtils.setLocationListener(new AmapUtils.LocationListener() {
            @Override
            public void getResultAdd(String city, double latitude, double longitude) {
                tv_address.setText(city);
            }
        });
    }

    @Override
    protected void initListener() {

    }
    @Override
    public void setOnClick(int position) {
        switch (position) {
            case 0://手机充值
                startActivity(new Intent(ctx, WebActivity.class).putExtra("url", NetUtils.H5Video));
                break;
            case 1://新闻头条
                startActivity(new Intent(ctx, NewsActivity.class));
                break;
            case 2://汇率
                startActivity(new Intent(ctx, ParitiesActivity.class));
                break;
            case 3://商城
                String string = sp.getString(SpUtiles.LoginSource, "");
                if ("zhongchuangyunhe".equals(string)) {
                    startActivity(new Intent(ctx, ShopActivity.class));
                }else{
                    Toast.makeText(ctx, "暂未开放", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(ctx, "暂未开放", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5 && resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();
            String datas = bundle.getString("result");
            Log.d("wch", datas);
            if (!TextUtils.isEmpty(datas) && datas.length() == 34) {

                Intent intent = new Intent(ctx, StartPaymentActivity.class);

                intent.putExtra(IntentTag.ResultCode, datas);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ctx, EntryOtherActivity.class);
                intent.putExtra(IntentTag.ResultCode, datas);
                startActivity(intent);
            }
        }
    }

    @OnClick({R.id.ll_saoma, R.id.ll_fukuan, R.id.ll_shoukuan, R.id.rl_xiaohua, R.id.iv_xingzuo})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ll_saoma:
                intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 5);
                break;
            case R.id.ll_fukuan:
                break;
            case R.id.ll_shoukuan:
                startActivity(new Intent(ctx, MyReceiptCodeActivity.class));
                break;
            case R.id.rl_xiaohua:
                startActivity(new Intent(ctx, XiaohuaActivity.class));
                break;
            case R.id.iv_xingzuo:
                startActivity(new Intent(ctx, StartActivity.class));
                break;
        }
    }
}
