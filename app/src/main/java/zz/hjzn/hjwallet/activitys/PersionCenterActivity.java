package zz.hjzn.hjwallet.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.PublicModel;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.Parments;
import zz.hjzn.hjwallet.utils.RequestCode;
import zz.hjzn.hjwallet.utils.SpUtiles;
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
    private File filepathHead;

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
                showPopuName(1,"修改昵称");
                break;
            case R.id.ll_gender:
                showPopuName(2,"修改姓名");
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
                    filepathHead = UploadPicUtiles.getFilePath(data, ctx);
                    wch(filepathHead + "");
//                    Glide.with(ctx).load(filepathHead).into(ivHead);
                    upHead();
                    break;
            }
        }
    }


    private void showPopuName(final int Type,String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        View view = LayoutInflater.from(ctx).inflate(R.layout.dia_name, null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        final EditText etName = view.findViewById(R.id.et_name);
        Button btnClose = view.findViewById(R.id.btn_close);
        Button btnAff = view.findViewById(R.id.btn_aff);
        tvTitle.setText(title);
        final AlertDialog show = builder.show();
        final Map<String, String> map = new HashMap<>();
        map.put(Parments.SessionId, sp.getString(SpUtiles.sessionId, ""));
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });
        btnAff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    vibrator.vibrate(50);
                    Toast.makeText(PersionCenterActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                show.dismiss();
             //1 昵称,姓名，生日
                if (Type == 1) {
                    map.put(Parments.nickName, name);
                } else {
                    map.put(Parments.realName,name);
                }
            mPreenter.fetch(map,false,NetUtils.ChangePersion,"");
            }
        });


    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        PublicModel publicModel = gson.fromJson(s, PublicModel.class);
        if (publicModel.isSuccess()) {
            Toast.makeText(ctx, "更新成功", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(ctx, publicModel.getErrDesc(), Toast.LENGTH_SHORT).show();
        }
    }

    private void upHead() {
        showProgress();
        wch(sp.getString(SpUtiles.sessionId,""));
        OkGo.<String>post(NetUtils.ChangePersion)
                .params(Parments.SessionId, sp.getString(SpUtiles.sessionId, ""))
//                .params(Parments.portraitImgUrl, filepathHead)
                .upFile(filepathHead)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        dissProgress();
                        wch(response.body());
                        PublicModel publicModel = gson.fromJson(response.body(), PublicModel.class);
                        if (publicModel.isSuccess()) {
                            Glide.with(ctx).load(filepathHead).into(ivHead);
                            Toast.makeText(ctx, "修改成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ctx, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        dissProgress();
                        Toast.makeText(ctx, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
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
                    wch(year + ":" + (month + 1) + ":" + dayOfMonth);
                    popupWindow.dismiss();
                    break;

            }
        }
    }


}
