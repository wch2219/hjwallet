package zz.hjzn.hjwallet.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import zz.hjzn.hjwallet.model.GscPriceBen;
import zz.hjzn.hjwallet.model.PublicModel;
import zz.hjzn.hjwallet.model.ZhongchuangBean;
import zz.hjzn.hjwallet.utils.ButtonUtils;
import zz.hjzn.hjwallet.utils.LogUtils;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.RequestCode;
import zz.hjzn.hjwallet.utils.SpUtiles;

/**
 * 商城
 */
public class ShopActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.btn_aff)
    Button btnAff;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.et_othacc)
    EditText etOthacc;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_shifang)
    TextView tvShifang;
    private int HttpType;//
    private float gscPrice;
    private double total;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_shop);
    }

    @Override
    protected void initData() {
        tvTitle.setText("中创云合");
        initTabBar(toolBar, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInfo();
    }

    private void getInfo() {
        HttpType = 1;
        String account = sp.getString(SpUtiles.Account, "");
        Map<String, String> map = new HashMap<>();
        map.put("loginAccount", account);
        mPreenter.fetch(map, false, NetUtils.zhongchuangGetInfo, "");
    }

    @Override
    protected void initListener() {
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String money = s.toString().trim();
                if (tvPrice.getText().toString().trim().equals("0")) {
                    Toast.makeText(ctx, "请重新请求币价格", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(money)) {
                    float v = Float.parseFloat(money);
                    if (v <= 0) {
                        etMoney.setText("0");
                    }
                    float num = (float) (v / (gscPrice));
//                    if (num>Float.valueOf(tvTotal.getText().toString().trim())) {
//                        tvNum.setText(total+"");
//                        etMoney.setText(total*6.5+"");
//                    }
                    wch(num);
                    // num =   (float)(Math.round(num*100))/100;
                    tvNum.setText(num + "");
                } else {
                    tvNum.setText("0");
                }
            }
        });
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick(R.id.btn_aff)
    public void onViewClicked() {
        if (tvPrice.getText().toString().trim().equals("0")) {
            Toast.makeText(ctx, "请重新请求币价格", Toast.LENGTH_SHORT).show();
            etMoney.setText("");
            return;
        }
        if (ButtonUtils.isFastDoubleClick(R.id.btn_aff, 3000)) {
            return;
        }
        String otheracc = etOthacc.getText().toString().trim();
        if (TextUtils.isEmpty(otheracc)) {
            vibrator.vibrate(100);
            Toast.makeText(ctx, "对方账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String money = etMoney.getText().toString().trim();
        if (TextUtils.isEmpty(money) && money.equals("0")) {
            vibrator.vibrate(100);
            Toast.makeText(ctx, "输入正确的金额", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpType = 3;

        Map<String, String> map = new HashMap<>();
        map.put("loginAccount", sp.getString(SpUtiles.Account, ""));
        map.put("toAccount", otheracc);
        map.put("gscNum", tvNum.getText().toString().trim());
        mPreenter.fetch(map, false, NetUtils.ZhongchuangRollout, "");
    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        if (HttpType == 1) {

            ZhongchuangBean zhongchuangBean = gson.fromJson(s, ZhongchuangBean.class);
            if (zhongchuangBean.getErrCode() == RequestCode.SuccessCode) {
                total = zhongchuangBean.getResult().getHoldGscNum();
                tvTotal.setText( total+ "");
                tvShifang.setText(zhongchuangBean.getResult().getGscNum()+"");
//                getPrice();
            } else {
                Toast.makeText(ctx, zhongchuangBean.getErrDesc(), Toast.LENGTH_SHORT).show();
            }
        } else if (HttpType == 2) {
            GscPriceBen gscPriceBen = gson.fromJson(s, GscPriceBen.class);
            String data = gscPriceBen.getMsg().getDqj();

            gscPrice = (float) (Float.valueOf(data) * 6.5);
            tvPrice.setText(gscPrice + " 元");
        } else {
            PublicModel publicModel = gson.fromJson(s, PublicModel.class);
            if (publicModel.isSuccess()) {
                etOthacc.setText("");
                etMoney.setText("");
                getInfo();
            }
            Toast.makeText(ctx, publicModel.getErrDesc(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError() {
        switch (HttpType) {
            case 1:
                getInfo();
                break;
            case 2:
                getPrice();
                break;

        }
    }

    private void getPrice() {
        HttpType = 2;
        mPreenter.fetch(new HashMap<String, String>(), true, NetUtils.BiPrice, "");
    }
}
