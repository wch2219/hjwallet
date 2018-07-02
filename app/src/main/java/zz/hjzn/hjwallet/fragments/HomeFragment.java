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
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.activitys.EntryOtherActivity;
import zz.hjzn.hjwallet.activitys.MyReceiptCodeActivity;
import zz.hjzn.hjwallet.activitys.ShopActivity;
import zz.hjzn.hjwallet.activitys.StartPaymentActivity;
import zz.hjzn.hjwallet.adapter.LifeAdapter;
import zz.hjzn.hjwallet.base.BaseFragment;
import zz.hjzn.hjwallet.base.BaseRecrviewAdapter;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.IntentTag;
import zz.hjzn.hjwallet.zxing.CaptureActivity;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements BaseRecrviewAdapter.OnItemClickListener{


    @BindView(R.id.ll_saoma)
    LinearLayout llSaoma;
    @BindView(R.id.ll_fukuan)
    LinearLayout llFukuan;
    @BindView(R.id.ll_shoukuan)
    LinearLayout llShoukuan;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        GridLayoutManager manager = new GridLayoutManager(ctx,4);
        rvList.setLayoutManager(manager);
        LifeAdapter lifeAdapter = new LifeAdapter(ctx);
        lifeAdapter.setOnItemClickListener(this);
        rvList.setAdapter(lifeAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void setOnClick(int position) {
        switch (position){
                    case 3://商城
                        startActivity(new Intent(ctx,ShopActivity.class));
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

    @OnClick({R.id.ll_saoma, R.id.ll_fukuan, R.id.ll_shoukuan})
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
        }
    }
}
