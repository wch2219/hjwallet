package zz.hjzn.hjwallet.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.ParitiesMode;
import zz.hjzn.hjwallet.utils.NetUtils;

/**
 * 汇率
 */
public class ParitiesActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_cankao)
    TextView tvCankao;
    private ParitiesMode paritiesMode;
    private int index;
    private List<List<String>> resultList;
    private double ratio;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_parities);
    }

    @Override
    protected void initData() {
        tvTitle.setText("汇率换算");
        initTabBar(toolBar,true);
        getInfo();
    }

    /**
     * 获取汇率列表
     */
    private void getInfo() {
        mPreenter.fetch(new HashMap<String, String>(), true,NetUtils.Parities,null);
    }

    @Override
    protected void initListener() {
        etNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String trim = s.toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    tvNum.setText("");
                    return;
                }
                double mmm = Double.valueOf(trim)*ratio;
                tvNum.setText(mmm+"");
            }
        });
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }


    @OnClick(R.id.ll_other)
    public void onViewClicked() {
        Intent intent = new Intent(ctx, ParitiesListAcitivity.class);
        intent.putExtra("resultlist",paritiesMode);
        startActivityForResult(intent,1);
    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        paritiesMode = gson.fromJson(s, ParitiesMode.class);
        if (paritiesMode.getError_code() == 0) {
            ParitiesMode.ResultBean result = paritiesMode.getResult();
            resultList = result.getList();
           showUi(index);
        }
    }

    private void showUi(int index) {
        ratio = Double.valueOf(resultList.get(index).get(2))/100;
        tvName.setText(resultList.get(index).get(0));
        etNum.setHint(resultList.get(index).get(1));
        tvNum.setHint(resultList.get(index).get(2));
        tvCankao.setText(resultList.get(index).get(4));
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1&&data!=null) {
            index = data.getIntExtra("index", 0);
            tvNum.setText("");
            showUi(index);
        }
    }
}
