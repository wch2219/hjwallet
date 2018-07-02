package zz.hjzn.hjwallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseRecrviewAdapter;

public class LifeAdapter extends BaseRecrviewAdapter {
    private int[] pics = {R.mipmap.icon_syphone, R.mipmap.icon_shenghuo, R.mipmap.icon_huilv, R.mipmap.icon_shop, R.mipmap.icon_qiche, R.mipmap.icon_huoche, R.mipmap.icon_kuaidi, R.mipmap.icon_gengduo};
    private String[] strs = {"手机充值", "生活缴费", "汇率换算", "商城", "火车/机票", "车票", "快递", "更多"};

    public LifeAdapter(Context ctx) {
        super(ctx);
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
