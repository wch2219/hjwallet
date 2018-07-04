package zz.hjzn.hjwallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import zz.hjzn.hjwallet.activitys.WebActivity;
import zz.hjzn.hjwallet.adapter.NewsListAdapter;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.NewsModel;
import zz.hjzn.hjwallet.utils.NetUtils;

/**
 * 新闻头条
 */
public class NewsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.lv_list)
    ListView lvList;
    private List<NewsModel.ResultBean.DataBean> data;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_news);
    }

    @Override
    protected void initData() {
        tvTitle.setText("新闻头条");
        initTabBar(toolBar,true);
        getInfo();
    }


    @Override
    protected void initListener() {
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ctx, WebActivity.class).putExtra("url",data.get(position).getUrl()));
            }
        });
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    private void getInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key","3f159be39e0f58a66fa4e108d07a6e73");
        map.put("type","top");
        mPreenter.fetch(map,false, NetUtils.News,"");
    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        NewsModel newsModel = gson.fromJson(s, NewsModel.class);
        if (newsModel.getError_code() == 0) {
            NewsModel.ResultBean result = newsModel.getResult();
            data = result.getData();
            lvList.setAdapter(new NewsListAdapter(ctx, data));
        }
    }
}
