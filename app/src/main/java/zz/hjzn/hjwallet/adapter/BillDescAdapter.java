package zz.hjzn.hjwallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseFastAdapter;
import zz.hjzn.hjwallet.model.BiollDescModel;

/**
 * 账单明细
 */
public class BillDescAdapter extends BaseFastAdapter {
    private List<BiollDescModel.ResultBean> result;

    public BillDescAdapter(Context ctx,List<BiollDescModel.ResultBean> result) {
        super(ctx);
        this.result = result;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_billdesc, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        BiollDescModel.ResultBean resultBean = result.get(position);
        vh.tv_time.setText(resultBean.getChangeTimeStr());
        vh.tv_num.setText(resultBean.getChangeValue()+" Gsc");

        return convertView;
    }

   private class ViewHolder {
        public View rootView;
        public TextView tv_name;
        public TextView tv_time;
        public TextView tv_num;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_num = (TextView) rootView.findViewById(R.id.tv_num);
        }

    }
}
