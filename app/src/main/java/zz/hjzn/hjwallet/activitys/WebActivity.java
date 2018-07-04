package zz.hjzn.hjwallet.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.utils.LoadWEBUtiles;

public class WebActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        LoadWEBUtiles webUtiles = new LoadWEBUtiles(this,null);
//        webview.loadUrl("http://v.juhe.cn/wepiao/go?key=40c238959364045af29805645ab80ef5");
        webUtiles.setListViewData(getIntent().getStringExtra("url"),webview,null);
    }
}
