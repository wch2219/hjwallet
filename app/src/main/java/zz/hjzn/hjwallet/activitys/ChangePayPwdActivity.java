package zz.hjzn.hjwallet.activitys;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
 * 修改交易密码
 */
public class ChangePayPwdActivity extends BaseActivity {
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
    @BindView(R.id.et_affpwd)
    EditText etAffpwd;
    @BindView(R.id.btn_aff)
    Button btnAff;
    private int HttpType;//0短信验证  1  注册
    @Override
    protected void initView() {
        setContentView(R.layout.activity_change_pay_pwd);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.forgetpayPwd);
        initTabBar(toolBar, true);
    }

    @Override
    protected void initListener() {
        etPhone.addTextChangedListener(new PayMentEditetxtListener(ctx,btnGetauth,true));
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
                String code = etAuthcode.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                String affpwd = etAffpwd.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(code)) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(affpwd)) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                wch(pwd+";"+affpwd);
                if (!pwd.equals(affpwd)) {
                    vibrator.vibrate(100);
                    Toast.makeText(ctx, "两次密码不一致，请重新设置", Toast.LENGTH_SHORT).show();
                    etPwd.setText("");
                    etAffpwd.setText("");
                    return;
                }
                HttpType = 1;
                Map<String,Object> map = new HashMap<>();
                map.put(Parments.loginAccount,phone);
                map.put(Parments.verifyCode,code);
                map.put("password",pwd);
                map.put("pwdType","2");
                map.put("confirmPassword",affpwd);
                mPreenter.fetch(map,true,NetUtils.ForgetPwd,"");
                break;
        }
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
                finish();
            }
            Toast.makeText(ctx, publicModel.getErrDesc(), Toast.LENGTH_SHORT).show();
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

}
