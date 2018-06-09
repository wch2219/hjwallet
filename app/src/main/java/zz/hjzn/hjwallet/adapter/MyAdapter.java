package zz.hjzn.hjwallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseFastAdapter;

/**
 * 我的
 */
public class MyAdapter extends BaseFastAdapter {
    private int[] names = {R.string.persion_info, R.string.bill_date, R.string.about_we, R.string.version_up, R.string.sett};
    private int[] pics = {R.mipmap.icon_persion, R.mipmap.icon_bill, R.mipmap.icon_about, R.mipmap.icon_edittion, R.mipmap.icon_versionup};

    public MyAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_my, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_name.setText(names[position]);
        vh.iv_pic.setImageResource(pics[position]);
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
