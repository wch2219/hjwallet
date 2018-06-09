package zz.hjzn.hjwallet.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.activitys.MyReceiptCodeActivity;
import zz.hjzn.hjwallet.activitys.StartPaymentActivity;
import zz.hjzn.hjwallet.base.BaseFragment;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.IntentTag;
import zz.hjzn.hjwallet.utils.SetBanner;
import zz.hjzn.hjwallet.zxing.CaptureActivity;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.btn_flick)
    Button btnFlick;
    @BindView(R.id.btn_receiptcode)
    Button btnReceiptcode;
    Unbinder unbinder;
    @BindView(R.id.banner)
    Banner banner;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        List<Integer> banners = new ArrayList<>();
        banners.add(R.drawable.head);
        banners.add(R.drawable.head);
        banners.add(R.drawable.head);
        banners.add(R.drawable.head);
        SetBanner.startBanner(ctx,banner,banners);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }



    @OnClick({R.id.btn_flick, R.id.btn_receiptcode})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_flick:
                intent = new Intent(getActivity(), StartPaymentActivity.class);
                startActivityForResult(intent, 5);
                break;
            case R.id.btn_receiptcode:
                startActivity(new Intent(ctx, MyReceiptCodeActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5 && resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();
            String datas = bundle.getString("result");
            Log.d("wch",   datas);
            Intent intent = new Intent(ctx,StartPaymentActivity.class);
            intent.putExtra(IntentTag.ResultCode,datas);
            startActivity(intent);
        }
    }



}
