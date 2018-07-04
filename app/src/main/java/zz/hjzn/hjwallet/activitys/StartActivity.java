package zz.hjzn.hjwallet.activitys;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.adapter.StartsAdapter;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.StartModel;
import zz.hjzn.hjwallet.utils.NetUtils;

/**
 * 星座运势
 */
public class StartActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_selete)
    LinearLayout llSelete;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_color)
    TextView tvColor;
    @BindView(R.id.tv_health)
    TextView tvHealth;
    @BindView(R.id.tv_love)
    TextView tvLove;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_luckey)
    TextView tvLuckey;
    @BindView(R.id.tv_qfriend)
    TextView tvQfriend;
    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.rb_1)
    RadioButton rb1;
    @BindView(R.id.rb_2)
    RadioButton rb2;
    @BindView(R.id.rb_3)
    RadioButton rb3;
    @BindView(R.id.rb_4)
    RadioButton rb4;
//    @BindView(R.id.rb_5)
//    RadioButton rb5;
    private int[] pics = {R.mipmap.icon_baiyang, R.mipmap.icon_jinniu, R.mipmap.icon_shuangzi, R.mipmap.icon_juxie, R.mipmap.icon_shizi,
            R.mipmap.icon_chunv, R.mipmap.icon_tianping, R.mipmap.icon_tianxie, R.mipmap.icon_sheshou, R.mipmap.icon_mojie, R.mipmap.icon_shuiping, R.mipmap.icon_shuangyu
    };
    private String[] strs = {"白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座",};

    @Override
    protected void initView() {
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void initData() {
        tvTitle.setText("星运走势");
        initTabBar(toolBar, true);
        tvName.setText(strs[0]);
        ivPic.setImageResource(pics[0]);
        getInfo();
    }


    @Override
    protected void initListener() {
        rgGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @OnClick(R.id.ll_selete)
    public void onViewClicked() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        View view = LayoutInflater.from(ctx).inflate(R.layout.dia_starts, null);
        ViewHolder vh = new ViewHolder(view);
        builder.setView(view);
        final AlertDialog show = builder.show();
        vh.tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        vh.lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvName.setText(strs[position]);
                ivPic.setImageResource(pics[position]);
               satrtName = strs[position];
                getInfo();
                show.dismiss();
            }
        });
        vh.lv_list.setAdapter(new StartsAdapter(ctx, strs, pics));
    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        StartModel startModel = gson.fromJson(s, StartModel.class);
        if (startModel.getError_code() == 0) {
            tvAll.setText(startModel.getAll());
            tvColor.setText(startModel.getColor());
            tvHealth.setText(startModel.getHealth());
            tvLove.setText(startModel.getLove());
            tvMoney.setText(startModel.getMoney());
            tvLuckey.setText(startModel.getNumber() + "");
            tvQfriend.setText(startModel.getQFriend());
            tvWork.setText(startModel.getWork());
            summary.setText("\u3000\u3000" + startModel.getSummary());
        } else {
            Toast.makeText(ctx, "请求失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void getInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "b3c39b67bb1d1abdba2e3f42f8ef498f");
        map.put("consName", satrtName);
        map.put("type", today);
        mPreenter.fetch(map, true, NetUtils.StartLuck, "");

    }

    private class ViewHolder {
        public View rootView;
        public ListView lv_list;
        public TextView tv_close;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.lv_list = (ListView) rootView.findViewById(R.id.lv_list);
            this.tv_close = rootView.findViewById(R.id.tv_close);
        }

    }

    private String today = "today";
    private String satrtName = "白羊座";
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_1:
                today = "today";
                tvDate.setText("---今日运势---");
                getInfo();
                break;
            case R.id.rb_2:
                today = "tomorrow";
                tvDate.setText("---明日运势---");
                getInfo();
                break;
            case R.id.rb_3:
                today = "week";
                tvDate.setText("---本周运势---");
                getInfo();
                break;
            case R.id.rb_4:
                today = "month";
                tvDate.setText("---本月运势---");
                getInfo();

                break;
//            case R.id.rb_5:
//                today = "year";
//                tvDate.setText("---今年运势---");
//                getInfo();
//                break;

        }
    }
}
