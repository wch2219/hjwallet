package zz.hjzn.hjwallet.activitys;

import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.lisenter.PayMentEditetxtListener;
import zz.hjzn.hjwallet.model.PublicModel;
import zz.hjzn.hjwallet.utils.ButtonUtils;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.Parments;
import zz.hjzn.hjwallet.utils.RegularUils;
import zz.hjzn.hjwallet.utils.RequestCode;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_authcode)
    EditText etAuthcode;
    @BindView(R.id.btn_getauth)
    Button btnGetauth;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_aff)
    Button btnAff;
    @BindView(R.id.et_affpwd)
    EditText etAffpwd;
    @BindView(R.id.et_paypwd)
    EditText etPaypwd;
    @BindView(R.id.et_affpaypwd)
    EditText etAffpaypwd;
    private int HttpType;//0短信验证  1  注册

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.clickregister);
        initTabBar(toolBar, true);
    }

    @Override
    protected void initListener() {
        etPhone.addTextChangedListener(new PayMentEditetxtListener(ctx, btnGetauth, true));
        etPwd.addTextChangedListener(new PayMentEditetxtListener(ctx, btnAff));
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private boolean ischek;

    @OnClick({R.id.btn_getauth, R.id.btn_aff})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getauth:
                boolean fastDoubleClick = ButtonUtils.isFastDoubleClick(R.id.btn_aff);
                if (!fastDoubleClick) {
                    if (!ischek) {
                        sendSms();
                    }
                }
                break;
            case R.id.btn_aff:
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone) | !RegularUils.isMobileExact(phone)) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "手机号格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                String auth = etAuthcode.getText().toString().trim();
                if (TextUtils.isEmpty(auth)) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String pwd = etPwd.getText().toString().trim();
                if (!RegularUils.pwd(pwd)) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "密码格式不正确，必须大于或等于8位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String affpwd = etAffpwd.getText().toString().trim();
                if (TextUtils.isEmpty(affpwd)) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "请输入确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!affpwd.equals(pwd)) {
                    Toast.makeText(ctx, "两次密码不同请重新输入", Toast.LENGTH_SHORT).show();
                    etAffpwd.setText("");
                    etPwd.setText("");
                }
                if (RegularUils.isNumeric(pwd)||RegularUils.isAllEnglishChar(pwd)) {
                    Toast.makeText(ctx, "密码不可为纯数字或纯英文", Toast.LENGTH_SHORT).show();
                    return;
                }
                String paypwd = etPaypwd.getText().toString().trim();
                if (TextUtils.isEmpty(paypwd) || paypwd.length() < 6) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "交易密码不能低于6位", Toast.LENGTH_SHORT).show();
                    return;
                }
                String affpaypwd = etAffpaypwd.getText().toString().trim();
                if (TextUtils.isEmpty(affpaypwd) || !affpaypwd.equals(paypwd)) {
                    vibrator.vibrate(100);
                    etPaypwd.setText("");
                    etAffpaypwd.setText("");
                    Toast.makeText(ctx, "两次交易密码输入不同，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpType = 1;
                Map<String, Object> map = new HashMap<>();
                map.put(Parments.bindMobile,phone);
                map.put(Parments.loginAccount,phone);
                map.put(Parments.passWord,pwd);
                map.put(Parments.payPassWord,paypwd);
                map.put(Parments.confirmPassWord,affpwd);
                map.put(Parments.confirmPayPassWord,affpaypwd);
                map.put(Parments.regType,"2");
                map.put(Parments.verifyCode,auth);
                mPreenter.fetch(map,false,NetUtils.Register,"");
                break;
        }
    }

    private void sendSms() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone) | !RegularUils.isMobileExact(phone)) {
            vibrator.vibrate(100);
            Toast.makeText(ctx, "手机号格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpType = 0;
        Map<String, Object> map = new HashMap<>();
        map.put(Parments.phone, phone);
        mPreenter.fetch(map, true, NetUtils.GetAuth, "");
    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        if (HttpType == 0) {
            PublicModel publicModel = gson.fromJson(s, PublicModel.class);
            if (publicModel.getErrCode() == RequestCode.SuccessCode) {
                CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        ischek = true;
                        btnGetauth.setText(millisUntilFinished / 1000 + "s后重新发送");
                        btnGetauth.setTextColor(getResources().getColor(R.color.white));
                        btnGetauth.setBackgroundResource(R.drawable.shape_payment_affblue);
                    }
                    @Override
                    public void onFinish() {
                        ischek = false;
                        btnGetauth.setText(R.string.getAuth);
                        btnGetauth.setTextColor(getResources().getColor(R.color.white));
                        btnGetauth.setBackgroundResource(R.drawable.shape_payment_affblue);
                    }
                }.start();
            } else {
                Toast.makeText(ctx, publicModel.getErrDesc(), Toast.LENGTH_SHORT).show();
            }
        }else{
            PublicModel publicModel = gson.fromJson(s, PublicModel.class);
            if (publicModel.getErrCode() == RequestCode.SuccessCode) {
                Toast.makeText(ctx, publicModel.getErrDesc(), Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(ctx, publicModel.getErrDesc(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
