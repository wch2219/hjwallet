package zz.hjzn.hjwallet.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.activitys.BillDescActivity;
import zz.hjzn.hjwallet.activitys.PersionCenterActivity;
import zz.hjzn.hjwallet.activitys.SettActivity;
import zz.hjzn.hjwallet.adapter.MyAdapter;
import zz.hjzn.hjwallet.base.BaseFragment;
import zz.hjzn.hjwallet.base.Presenter;
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
                startActivity(new Intent(ctx,BillDescActivity.class));
                break;
            case 2://关于我们

                break;
            case 3://版本检查

                break;
            case 4://设置
                startActivity(new Intent(ctx,SettActivity.class));
                break;

        }
    }
}
