package zz.hjzn.hjwallet.activitys;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;

/**
 * 支付成功
 */
public class PaySuccessActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_time)
    TextView tvTime;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pay_success);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.Success);
        initTabBar(toolBar,false);

    }

    @Override
    protected void initListener() {
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText(millisUntilFinished/1000+"");
            }

            @Override
            public void onFinish() {
                    finish();
            }
        }.start();

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }
}
