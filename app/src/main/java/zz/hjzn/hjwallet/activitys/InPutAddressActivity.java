package zz.hjzn.hjwallet.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.IntentTag;

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
        String address = etAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address) && address.length() != 34) {
            vibrator.vibrate(100);
            Toast.makeText(ctx, "请输入正确的地址", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(ctx,StartPaymentActivity.class);
        intent.putExtra(IntentTag.ResultCode,address);
        startActivity(intent);
        finish();
    }
}
