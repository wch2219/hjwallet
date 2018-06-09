package zz.hjzn.hjwallet.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.adapter.BillDescAdapter;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;

/**
 * 账单明细
 */
public class BillDescActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.easylayout)
    SmartRefreshLayout easylayout;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_bill_desc);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.bill_date);
        initTabBar(toolBar,false);
        initrefresh(easylayout,true);
        lvList.setAdapter(new BillDescAdapter(ctx));
    }


    @Override
    public void downOnRefresh() {
        super.downOnRefresh();
    }

    @Override
    public void loadmore() {
        super.loadmore();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

}
