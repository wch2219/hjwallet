package zz.hjzn.hjwallet.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.UploadPicUtiles;
import zz.hjzn.hjwallet.weight.CircleImageView;

/**
 * 个人信息
 */
public class PersionCenterActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.ll_nickname)
    LinearLayout llNickname;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.ll_gender)
    LinearLayout llGender;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.ll_birth)
    LinearLayout llBirth;
    private PopupWindow popupWindow;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_persion_center);
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.persion_info);
        initTabBar(toolBar, true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick({R.id.ll_head, R.id.ll_nickname, R.id.ll_gender, R.id.ll_birth})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                UploadPicUtiles.showDia(ctx);
                break;
            case R.id.ll_nickname:
                break;
            case R.id.ll_gender:
                break;
            case R.id.ll_birth:
                showPopu();
                break;
        }
    }

    private void showPopu() {
        View view = LayoutInflater.from(ctx).inflate(R.layout.popu_birthdate, null);
        ViewHolder popuVh = new ViewHolder(view);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(tvBirth);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 1:
                    UploadPicUtiles.startZoomPic((Activity) ctx, data, 150, 150, 1, 1);
                    break;
                case 2:
                    UploadPicUtiles.startZoomPic((Activity) ctx, data, 150, 150, 1, 1);
                    break;
                case 5:
                    File filePath = UploadPicUtiles.getFilePath(data, ctx);
                    wch(filePath + "");
//                    UpHead(filePath);
                    break;
            }
        }
    }

    private class ViewHolder implements View.OnClickListener {
        public View rootView;
        public TextView tv_close;
        public TextView tv_aff;
        public DatePicker dp_selete;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_close = (TextView) rootView.findViewById(R.id.tv_close);
            this.tv_aff = (TextView) rootView.findViewById(R.id.tv_aff);
            this.dp_selete = (DatePicker) rootView.findViewById(R.id.dp_selete);
            this.tv_aff.setOnClickListener(this);
            this.tv_close.setOnClickListener(this);
        }

        @SuppressLint("NewApi")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_close:
                    popupWindow.dismiss();
                    break;
                case R.id.tv_aff:
                    int year = dp_selete.getYear();
                    int month = dp_selete.getMonth();
                    int dayOfMonth = dp_selete.getDayOfMonth();
                    wch(year+":"+(month+1)+":"+dayOfMonth);
                    popupWindow.dismiss();
                    break;

            }
        }
    }
}
