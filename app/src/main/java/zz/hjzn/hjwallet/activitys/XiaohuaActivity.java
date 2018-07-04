package zz.hjzn.hjwallet.activitys;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.adapter.XiaohuaAdapter;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.XiaohuaModel;
import zz.hjzn.hjwallet.utils.NetUtils;

/**
 * 笑话
 */
public class XiaohuaActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.lv_list)
    ListView lvList;
    @BindView(R.id.easylayout)
    SmartRefreshLayout easylayout;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_xiaohua);

    }

    @Override
    protected void initData() {
        tvTitle.setText("开心一刻");
        initrefresh(easylayout,true);
        initTabBar(toolBar,true);
        getInfo();
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
        XiaohuaModel xiaohuaModel = gson.fromJson(s, XiaohuaModel.class);
        if (xiaohuaModel.getError_code() == 0) {
            List<XiaohuaModel.ResultBean.DataBean> data = xiaohuaModel.getResult().getData();
            lvList.setAdapter(new XiaohuaAdapter(ctx,data));
        }
    }
    private int page = 1;
    @Override
    public void downOnRefresh() {
        page=1;
        getInfo();

    }
    @Override
    public void loadmore() {
        page++;
        getInfo();
    }
    private void getInfo() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        Date date = null;
        try {
            date = simpleDateFormat.parse("2018-01-01 00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime()/1000;
        wch(ts);
        Map<String,String> map = new HashMap<>();
        map.put("sort","asc");
        map.put("key","bf85b841324d0ced93f9ef8d5b4bdeba");
        map.put("page",String.valueOf(page));
        map.put("time",String.valueOf(ts));
        mPreenter.fetch(map,true, NetUtils.Xiaohua,"");
    }
}
