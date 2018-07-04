package zz.hjzn.hjwallet.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.PersionInfoModel;
import zz.hjzn.hjwallet.utils.IntentTag;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.Parments;
import zz.hjzn.hjwallet.utils.RequestCode;
import zz.hjzn.hjwallet.zxing.CaptureActivity;

/**
 * 手动输入二维码
 */
public class InPutAddressActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String address;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_in_put_address);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.inputAddressTitle);
        initTabBar(toolBar, false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        address = etAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address) && address.length() != 34) {
            vibrator.vibrate(100);
            Toast.makeText(ctx, "请输入正确的地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isEmpty(address) && address.length() == 34) {

            getUserInfo(address);


        }
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo(String result) {
        Map<String, String> map = new HashMap<>();
        map.put(Parments.walletAddress, result);
        mPreenter.fetch(map, true, NetUtils.AddressGetUserInfo, "");
    }
    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        wch(s);
        PersionInfoModel persionInfoModel = gson.fromJson(s, PersionInfoModel.class);
        if (persionInfoModel.getErrCode() == RequestCode.SuccessCode) {
            Intent intent = new Intent(ctx, StartPaymentActivity.class);
            intent.putExtra(IntentTag.ResultCode, persionInfoModel);
            intent.putExtra(IntentTag.walletAddress, address);
            startActivity(intent);
        } else {
            Intent intent = new Intent(ctx, EntryOtherActivity.class);
            intent.putExtra(IntentTag.ResultCode, address);
            startActivity(intent);
            finish();
        }

    }

}
