package zz.hjzn.hjwallet.base;

import android.util.Log;



import java.io.IOException;
import java.util.Map;

import zz.hjzn.hjwallet.interfac.InternetInterface;


public class Presenter<V>extends ViewPresenter {
    ViewInterface viewInterface;
    InterModelImpl mode = new InterModelImpl();

    public Presenter(ViewInterface viewInterface) {
        super();
        this.viewInterface = viewInterface;
    }
    //绑定view和mode
    public void fetch(Map<String,String> map, Boolean isget, String url, String cachKey){
        viewInterface.showProgress();

        if (mode != null) mode.loadData(new InternetInterface.LoadListener() {
            @Override
            public void complete(String s) {

                try {
                    viewInterface.showData(s);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i("erro",e.getMessage());
                }
            }
            @Override
            public void onError() {
                viewInterface.onError();
            }
        }, map, isget, url, cachKey);

    }
}
