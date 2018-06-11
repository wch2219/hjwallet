package zz.hjzn.hjwallet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zz.hjzn.hjwallet.MyApplication;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.activitys.BillDescActivity;
import zz.hjzn.hjwallet.activitys.PersionCenterActivity;
import zz.hjzn.hjwallet.activitys.SettActivity;
import zz.hjzn.hjwallet.adapter.MyAdapter;
import zz.hjzn.hjwallet.base.BaseFragment;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.PersionInfoModel;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.Parments;
import zz.hjzn.hjwallet.utils.RequestCode;
import zz.hjzn.hjwallet.utils.SpUtiles;
import zz.hjzn.hjwallet.weight.CircleImageView;

/**
 * 我的
 */
public class MyFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_gscnum)
    TextView tvGscnum;
    @BindView(R.id.lv_list)
    ListView lvList;
    Unbinder unbinder;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.easylayout)
    SmartRefreshLayout easylayout;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        lvList.setAdapter(new MyAdapter(ctx));
        initrefresh(easylayout,true);

    }

    @Override
    public void onResume() {
        super.onResume();
       getPerInfo();
    }

    private void getPerInfo() {
        String sessionId = sp.getString(SpUtiles.sessionId, "");//获取会话Id
        Map<String, String> map = new HashMap<>();
        map.put(Parments.SessionId, sessionId);
        mPreenter.fetch(map, true, NetUtils.GetPersionInfo, NetUtils.GetPersionInfo + Parments.SessionId);
    }

    @Override
    public void downOnRefresh() {
        getPerInfo();
    }

    @Override
    public void loadmore() {
        getPerInfo();
    }

    @Override
    protected void initListener() {
        lvList.setOnItemClickListener(this);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position) {
            case 0://个人信息
                startActivity(new Intent(ctx, PersionCenterActivity.class));
                break;
            case 1://账单明细
                startActivity(new Intent(ctx, BillDescActivity.class));
                break;
            case 2://关于我们

                break;
            case 3://版本检查

                break;
            case 4://设置
                startActivity(new Intent(ctx, SettActivity.class));
                break;
        }
    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        Gson gson = new Gson();
        PersionInfoModel persionInfoModel = gson.fromJson(s, PersionInfoModel.class);
        if (persionInfoModel.getErrCode() == RequestCode.SuccessCode) {
            MyApplication.persionInfoModel = persionInfoModel;
            PersionInfoModel.ResultBean result = persionInfoModel.getResult();
            tvGscnum.setText(String.valueOf(result.getBookBalance()));
            tvNickname.setText(result.getWalletAddress());
        } else {
            Toast.makeText(ctx, persionInfoModel.getErrDesc(), Toast.LENGTH_SHORT).show();
        }
    }

}
