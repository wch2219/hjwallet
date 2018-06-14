package zz.hjzn.hjwallet.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;

/**
 * 关于我们
 */
public class AboutWeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_about_we);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.about_we);
        initTabBar(toolBar,true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }
    @OnClick(R.id.tv_phone)
    public void onViewClicked() {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4001120258"));
        //跳转到拨号界面，同时传递电话号码
        startActivity(dialIntent);
    }
}
