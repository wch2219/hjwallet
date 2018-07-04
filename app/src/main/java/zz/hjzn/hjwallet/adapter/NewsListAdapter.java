package zz.hjzn.hjwallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseFastAdapter;
import zz.hjzn.hjwallet.model.NewsModel;

/**
 * 新闻
 */
public class NewsListAdapter extends BaseFastAdapter {
    private List<NewsModel.ResultBean.DataBean> data;
    public NewsListAdapter(Context ctx,List<NewsModel.ResultBean.DataBean> data) {
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
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_news, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        NewsModel.ResultBean.DataBean dataBean = data.get(position);
        vh.tv_title.setText(dataBean.getTitle());
        Glide.with(ctx).load(dataBean.getThumbnail_pic_s()).into(vh.iv_pic);
        vh.tv_author.setText(dataBean.getAuthor_name());
        vh.tv_date.setText(dataBean.getDate());
        return convertView;
    }

    private class ViewHolder {
        public View rootView;
        public TextView tv_title;
        public ImageView iv_pic;
        public TextView tv_author;
        public TextView tv_date;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_author = (TextView) rootView.findViewById(R.id.tv_author);
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
        }

    }
}
