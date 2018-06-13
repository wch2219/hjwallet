package zz.hjzn.hjwallet.activitys;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import zz.hjzn.hjwallet.MainActivity;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.SpUtiles;

/**
 * 启动页
 */
public class LunchActivity extends BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_lunch);
    }

    @Override
    protected void initData() {
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                String string = sp.getString(SpUtiles.sessionId, "");
                if (!TextUtils.isEmpty(string)) {
                    startActivity(new Intent(ctx,MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(ctx,LoginActivity.class));
                    finish();
                }
            }
        }.start();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

}
