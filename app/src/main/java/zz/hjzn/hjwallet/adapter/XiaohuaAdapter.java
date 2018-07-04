package zz.hjzn.hjwallet.adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseFastAdapter;
import zz.hjzn.hjwallet.model.XiaohuaModel;

/**
 * 笑话列表
 */
public class XiaohuaAdapter extends BaseFastAdapter {
    private List<XiaohuaModel.ResultBean.DataBean> data;
    public XiaohuaAdapter(Context ctx,List<XiaohuaModel.ResultBean.DataBean> data) {
        super(ctx);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_xiaohua, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        XiaohuaModel.ResultBean.DataBean dataBean = data.get(position);
        vh.tv_date.setText(dataBean.getUpdatetime());
       vh.tv_content.setText("\u3000\u3000"+Html.fromHtml(dataBean.getContent()));

        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public TextView tv_date;
        public TextView tv_content;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
            this.tv_content = (TextView) rootView.findViewById(R.id.tv_content);
        }
    }
}
