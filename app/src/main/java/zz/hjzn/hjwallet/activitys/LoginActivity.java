package zz.hjzn.hjwallet.activitys;

import android.content.Intent;
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
import zz.hjzn.hjwallet.MainActivity;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.LoginMode;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.Parments;
import zz.hjzn.hjwallet.utils.RegularUils;
import zz.hjzn.hjwallet.utils.RequestCode;
import zz.hjzn.hjwallet.utils.SpUtiles;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_aff)
    Button btnAff;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forgetpwd)
    TextView tvForgetpwd;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
//        etPwd.addTextChangedListener(new );
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick({R.id.tv_register, R.id.tv_forgetpwd, R.id.btn_aff})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register://注册
                startActivity(new Intent(ctx, RegisterActivity.class));
                break;
            case R.id.tv_forgetpwd://忘记密码
                startActivity(new Intent(ctx, ForgetPwdActivity.class));
                break;
            case R.id.btn_aff://登录
                String phone = etPhone.getText().toString().trim();
//                if (TextUtils.isEmpty(phone) && RegularUils.isMobileExact(phone)) {
//                    Toast.makeText(ctx, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                String pwd = etPwd.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(ctx, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, Object> map = new HashMap<>();
                map.put(Parments.loginAccount, phone);
                map.put(Parments.password, pwd);
                mPreenter.fetch(map, true, NetUtils.Login, "");
                break;
        }
    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        LoginMode loginMode = gson.fromJson(s, LoginMode.class);
        if (loginMode.getErrCode() == RequestCode.SuccessCode) {
            LoginMode.ResultBean result = loginMode.getResult();
            sp.edit().putString(SpUtiles.sessionId, result.getSessionId()).commit();
            sp.edit().putString(SpUtiles.Account,etPhone.getText().toString().trim()).commit();
            sp.edit().putString(SpUtiles.LoginSource,result.getLoginSource()).commit();
            wch(result.getSessionId());
            startActivity(new Intent(ctx, MainActivity.class));
            finish();
        } else {
            Toast.makeText(ctx, loginMode.getErrDesc(), Toast.LENGTH_SHORT).show();
        }
    }
}
