package zz.hjzn.hjwallet.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.adapter.BillDescAdapter;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.BiollDescModel;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.Parments;
import zz.hjzn.hjwallet.utils.RequestCode;
import zz.hjzn.hjwallet.utils.SpUtiles;

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

        Map<String,String> map = new HashMap<>();
        map.put(Parments.SessionId,sp.getString(SpUtiles.sessionId,""));
        mPreenter.fetch(map,true, NetUtils.BillDesc,"");
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

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        BiollDescModel biollDescModel = gson.fromJson(s, BiollDescModel.class);
        if (biollDescModel.getErrCode() == RequestCode.SuccessCode) {
            List<BiollDescModel.ResultBean> result = biollDescModel.getResult();
            lvList.setAdapter(new BillDescAdapter(ctx,result));
        }else{
            Toast.makeText(ctx, biollDescModel.getErrDesc(), Toast.LENGTH_SHORT).show();
        }
    }
}
