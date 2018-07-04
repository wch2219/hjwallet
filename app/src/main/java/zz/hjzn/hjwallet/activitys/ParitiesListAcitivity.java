package zz.hjzn.hjwallet.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.BaseFastAdapter;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.ParitiesMode;

/**
 * 汇率列表
 */
public class ParitiesListAcitivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.lv_list)
    ListView lvList;
    private int[] pics = {R.mipmap.icon_meiguo, R.mipmap.icon_xianggang, R.mipmap.icon_riben, R.mipmap.icon_oumeng, R.mipmap.icon_english};

    @Override
    protected void initView() {
        setContentView(R.layout.activity_parities_list_acitivity);
    }

    @Override
    protected void initData() {
        tvTitle.setText("币种切换");
        initTabBar(toolBar,true);
        ParitiesMode paritiesMode = (ParitiesMode) getIntent().getSerializableExtra("resultlist");

        lvList.setAdapter(new MyAdapter(ctx, paritiesMode));
    }

    @Override
    protected void initListener() {
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("index",position);
                setResult(1,intent);
                finish();
            }
        });
    }

    @Override
    protected Presenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private class MyAdapter extends BaseFastAdapter {
        private ParitiesMode paritiesMode;

        public MyAdapter(Context ctx, ParitiesMode paritiesMode) {
            super(ctx);
            this.paritiesMode = paritiesMode;
        }

        @Override
        public int getCount() {
            return paritiesMode.getResult().getList().size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                convertView = LayoutInflater.from(ctx).inflate(R.layout.item_country, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            List<List<String>> list = paritiesMode.getResult().getList();
            vh.iv_pic.setImageResource(pics[position]);
            vh.tv_name.setText(list.get(position).get(0));
            return convertView;
        }

       private class ViewHolder {
            public View rootView;
            public ImageView iv_pic;
            public TextView tv_name;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
                this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            }

        }
    }
}
