package zz.hjzn.hjwallet.activitys;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.IntentTag;

/**
 * 扫描为其他的信息
 */
public class EntryOtherActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_entry_other);
    }

    @Override
    protected void initData() {
        String result = getIntent().getStringExtra(IntentTag.ResultCode);
        tvText.setText(result);
        tvTitle.setText(R.string.resultTitel);
        initTabBar(toolBar,true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

}
