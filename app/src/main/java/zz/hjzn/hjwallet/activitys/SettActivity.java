package zz.hjzn.hjwallet.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zz.hjzn.hjwallet.MainActivity;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.SpUtiles;

/**
 * 设置
 */
public class SettActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.ll_changepwd)
    LinearLayout llChangepwd;
    @BindView(R.id.ll_changepaypwd)
    LinearLayout llChangepaypwd;
    @BindView(R.id.ll_opengesture)
    LinearLayout llOpengesture;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.iv_swich)
    ImageView ivSwich;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_sett);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.sett);
        initTabBar(toolBar,true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        String lockpwd = sp.getString(SpUtiles.LockScreenPwd, "");
        if (TextUtils.isEmpty(lockpwd)) {
            ivSwich.setImageResource(R.mipmap.icon_shut);
        }else{
            ivSwich.setImageResource(R.mipmap.icon_open);
        }
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }



    @OnClick({R.id.ll_changepwd, R.id.ll_changepaypwd, R.id.ll_opengesture, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_changepwd://修改登录密码
                startActivity(new Intent(ctx,ForgetPwdActivity.class));
                break;
            case R.id.ll_changepaypwd://修改交易密码
                startActivity(new Intent(ctx,ChangePayPwdActivity.class));
                break;
            case R.id.ll_opengesture://开启手势解锁
                startActivity(new Intent(ctx,SetLockScreenPwdActivity.class));
                break;
            case R.id.btn_exit://退出登录
                sp.edit().putString(SpUtiles.sessionId,"").commit();
                finish();
                startActivity(new Intent(ctx,LoginActivity.class));
                close();
                break;
        }
    }
}
