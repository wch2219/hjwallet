package zz.hjzn.hjwallet.activitys;

import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.lisenter.PayMentEditetxtListener;

/**
 * 付款
 */
public class StartPaymentActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.btn_aff)
    Button btnAff;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_start_payment);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.payment_title);
        initTabBar(toolBar,false);
    }

    @Override
    protected void initListener() {
        etNum.addTextChangedListener(new PayMentEditetxtListener(ctx,btnAff));
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    @OnClick(R.id.btn_aff)
    public void onViewClicked() {
        Toast.makeText(ctx, "转账成功", Toast.LENGTH_SHORT).show();
    }
}
