package zz.hjzn.hjwallet.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternIndicatorView;
import com.github.ihsg.patternlocker.PatternLockerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.SpUtiles;

/**
 * 设置锁屏密码
 */
public class SetLockScreenPwdActivity extends BaseActivity implements OnPatternChangeListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.pattern_indicator_view)
    PatternIndicatorView patternIndicatorView;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.pattern_lock_view)
    PatternLockerView patternLockView;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_right)
    TextView tvRight;
    private String lockpwd;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_set_lock_screen_pwd);
    }

    @Override
    protected void initData() {
        tvTitle.setText("设置手势密码");
        initTabBar(toolBar, true);
    }

    @Override
    protected void initListener() {
        patternLockView.setOnPatternChangedListener(this);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onStart(PatternLockerView patternLockerView) {

    }

    @Override
    public void onChange(PatternLockerView patternLockerView, List<Integer> list) {

    }

    @Override
    public void onComplete(PatternLockerView patternLockerView, List<Integer> list) {
        String pwds = null;
        if (list.size() < 4) {//小于4重新设置
            vibrator.vibrate(100);
            patternLockerView.updateStatus(true);
            tvType.setText(R.string.lockpwd_error1);
            tvType.setTextColor(getResources().getColor(R.color.red));
            return;
        }

        for (Integer integer : list) {
            pwds = pwds + integer;
        }
        if (TextUtils.isEmpty(lockpwd)) {//第一次设置
            lockpwd = pwds;
            tvType.setText(R.string.lockpwd_twohint);
            tvType.setTextColor(getResources().getColor(R.color.gary_text));
            patternIndicatorView.updateState(list, false);
        } else if (lockpwd.equals(pwds)) {//第二次设置  与上次相同
            sp.edit().putString(SpUtiles.LockScreenPwd, lockpwd).commit();//保存锁屏密码到本地

        } else {//与第一次不同
            vibrator.vibrate(100);
            tvType.setText(R.string.lockpwd_error2);
            tvType.setTextColor(getResources().getColor(R.color.red));
            patternLockerView.updateStatus(true);

        }
        wch(pwds);
    }
    @Override
    public void onClear(PatternLockerView patternLockerView) {

    }
    @OnClick(R.id.tv_reset)
    public void onViewClicked() {
        tvType.setText(R.string.lockpwd_onehint);
        lockpwd = null;
        tvType.setTextColor(getResources().getColor(R.color.gary_text));
        patternIndicatorView.updateState(new ArrayList<Integer>(), false);

    }

}
