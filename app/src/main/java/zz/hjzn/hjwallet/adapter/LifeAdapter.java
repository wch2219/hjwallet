package zz.hjzn.hjwallet.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseRecrviewAdapter;
import zz.hjzn.hjwallet.utils.SpUtiles;

import static android.content.Context.MODE_PRIVATE;

public class LifeAdapter extends BaseRecrviewAdapter {
    private int[] pics = {R.mipmap.icon_maoyan, R.mipmap.icon_news, R.mipmap.icon_huilv, R.mipmap.icon_zhonhe, R.mipmap.icon_qiche, R.mipmap.icon_huoche, R.mipmap.icon_kuaidi, R.mipmap.icon_gengduo};
    private String[] strs = {"猫眼电影", "新闻头条", "汇率换算", "中创结算", "火车/机票", "车票", "快递", "更多"};

    public LifeAdapter(Context ctx) {
        super(ctx);
        SharedPreferences sp = ctx.getSharedPreferences(SpUtiles.SP_Mode,MODE_PRIVATE);
        String string = sp.getString(SpUtiles.LoginSource, "");
        if (!"zhongchuangyunhe".equals(string)) {
            pics = new int[]{R.mipmap.icon_maoyan, R.mipmap.icon_news, R.mipmap.icon_huilv, R.mipmap.icon_qiche, R.mipmap.icon_huoche, R.mipmap.icon_kuaidi, R.mipmap.icon_gengduo};
            strs = new String[]{"猫眼电影", "新闻头条", "汇率换算", "火车/机票", "车票", "快递", "更多"};
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_home, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder)holder).iv_pic.setImageResource(pics[position]);
        ((ViewHolder)holder).tv_name.setText(strs[position]);
        ((ViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.setOnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pics.length;
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView iv_pic;
        public TextView tv_name;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
        }
    }
}
