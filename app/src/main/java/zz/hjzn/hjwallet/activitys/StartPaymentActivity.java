package zz.hjzn.hjwallet.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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
import zz.hjzn.hjwallet.model.PersionInfoModel;
import zz.hjzn.hjwallet.model.PublicModel;
import zz.hjzn.hjwallet.utils.IntentTag;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.Parments;
import zz.hjzn.hjwallet.utils.PayWindowUtils;
import zz.hjzn.hjwallet.utils.RegularUils;
import zz.hjzn.hjwallet.utils.RequestCode;
import zz.hjzn.hjwallet.utils.SpUtiles;
import zz.hjzn.hjwallet.weight.CircleImageView;

/**
 * 付款
 */
public class StartPaymentActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.btn_aff)
    Button btnAff;
    private String walltAddress;
    private int HttpType;//0  支付密码校验  1  转账
    private String num;
    private PersionInfoModel persionInfoModel;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_start_payment);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.payment_title);
        initTabBar(toolBar, false);
        persionInfoModel = (PersionInfoModel) getIntent().getSerializableExtra(IntentTag.ResultCode);
       walltAddress = getIntent().getStringExtra(IntentTag.walletAddress);
        if (persionInfoModel != null) {
            PersionInfoModel.ResultBean result = persionInfoModel.getResult();
            if (!TextUtils.isEmpty(result.getRealName())) {
                tvName.setText(result.getRealName());
                if (result.getRealName().length() > 1) {

                    tvName1.setText("(*"+result.getRealName().substring(1)+")");
                }else{
                    tvName1.setText("(*"+result.getRealName()+")");
                }
            }else if (!TextUtils.isEmpty(result.getNickName())) {
                tvName.setText(result.getNickName());
                if (result.getNickName().length() > 0) {
                    tvName1.setText("(*"+result.getNickName().substring(1)+")");
                }else{
                    tvName1.setText("(*"+result.getNickName()+")");
                }
            }else{
                tvName1.setText("(**)");
                tvName.setText("(**)");
            }
            Glide.with(ctx).load(result.getPortraitImgUrl()).apply(new RequestOptions().error(R.mipmap.icon_head)).into(ivHead);

        }

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
        num = etNum.getText().toString();
        if (TextUtils.isEmpty(num)) {
            vibrator.vibrate(100);
            Toast.makeText(ctx, "数量不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Integer.valueOf(num) == 0) {
            Toast.makeText(ctx, "输入错误", Toast.LENGTH_SHORT).show();
            return;
        }

        PayWindowUtils.show((Activity) ctx);
        PayWindowUtils.setResultCallBack(new PayWindowUtils.ResultCallBack() {
            @Override
            public void result(String result) {
                HttpType = 0;
                Map<String, String> map = new HashMap<>();
                map.put(Parments.SessionId, sp.getString(SpUtiles.sessionId, ""));
                map.put("payPassword", result);
                mPreenter.fetch(map, true, NetUtils.CheckPayPwd, null);
            }
        });
    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        if (HttpType == 0) {
            PublicModel publicModel = gson.fromJson(s, PublicModel.class);
            if (publicModel.getErrCode() == RequestCode.SuccessCode) {
                HttpType = 1;
                Map<String, String> map = new HashMap<>();
                map.put(Parments.SessionId, sp.getString(SpUtiles.sessionId, ""));
                map.put(Parments.transferAmount, num);
                map.put(Parments.outAddress, walltAddress);
                mPreenter.fetch(map, false, NetUtils.PayGsc, "");
            }else{
                Toast.makeText(ctx, publicModel.getErrDesc(), Toast.LENGTH_SHORT).show();
            }
        } else {
            PublicModel publicModel = gson.fromJson(s, PublicModel.class);
            if (publicModel.getErrCode() == RequestCode.SuccessCode) {
               Intent intent = new Intent(ctx, PaySuccessActivity.class);
               startActivity(intent);
               finish();
            }
            Toast.makeText(ctx, publicModel.getErrDesc(), Toast.LENGTH_SHORT).show();
        }
    }
}
