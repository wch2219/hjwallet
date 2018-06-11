package zz.hjzn.hjwallet.activitys;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import zz.hjzn.hjwallet.MyApplication;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.lisenter.PayMentEditetxtListener;
import zz.hjzn.hjwallet.model.PublicModel;
import zz.hjzn.hjwallet.utils.IntentTag;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.Parments;
import zz.hjzn.hjwallet.utils.RegularUils;
import zz.hjzn.hjwallet.utils.RequestCode;
import zz.hjzn.hjwallet.utils.SpUtiles;

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
    private String walltAddress;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_start_payment);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.payment_title);
        initTabBar(toolBar, false);
        walltAddress = getIntent().getStringExtra(IntentTag.ResultCode);
    }

    @Override
    protected void initListener() {
        etNum.addTextChangedListener(new PayMentEditetxtListener(ctx, btnAff));
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick(R.id.btn_aff)
    public void onViewClicked() {
        String num = etNum.getText().toString();
        if (TextUtils.isEmpty(num)) {
            vibrator.vibrate(100);
            Toast.makeText(ctx, "数量不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Integer.valueOf(num) == 0) {
            Toast.makeText(ctx, "输入错误", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put(Parments.SessionId, sp.getString(SpUtiles.sessionId,""));
        map.put(Parments.transferAmount,num);
        map.put(Parments.outAddress,walltAddress);

        mPreenter.fetch(map,false, NetUtils.PayGsc,"");


    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        System.out.print(s);
        PublicModel publicModel = gson.fromJson(s, PublicModel.class);
        if (publicModel.getErrCode() == RequestCode.SuccessCode) {
           finish();
        }
        Toast.makeText(ctx, publicModel.getErrDesc(), Toast.LENGTH_SHORT).show();
    }
}
