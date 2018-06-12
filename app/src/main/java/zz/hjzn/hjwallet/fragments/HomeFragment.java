package zz.hjzn.hjwallet.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import zz.hjzn.hjwallet.activitys.EntryOtherActivity;
import zz.hjzn.hjwallet.activitys.MyReceiptCodeActivity;
import zz.hjzn.hjwallet.activitys.PaySuccessActivity;
import zz.hjzn.hjwallet.activitys.StartPaymentActivity;
import zz.hjzn.hjwallet.base.BaseFragment;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.IntentTag;
import zz.hjzn.hjwallet.utils.RegularUils;
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
        banners.add(R.mipmap.bannder);
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
                intent = new Intent(getActivity(), CaptureActivity.class);

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
            if (!TextUtils.isEmpty(datas)&&datas.length() == 34) {

                Intent intent = new Intent(ctx,StartPaymentActivity.class);

                intent.putExtra(IntentTag.ResultCode,datas);
                startActivity(intent);
            }else{
                Intent intent = new Intent(ctx,EntryOtherActivity.class);
                intent.putExtra(IntentTag.ResultCode,datas);
                startActivity(intent);
            }
        }
    }

}
