package zz.hjzn.hjwallet.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;

/**
 * 商城
 */
public class ShopActivity extends BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_shop);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

}
