package zz.hjzn.hjwallet.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.weight.PswInputView;


public class PayWindowUtils {

    public static ViewHolder vh;
    private static AlertDialog show;

    public static void show(final Activity ctx) {

        View view = LayoutInflater.from(ctx).inflate(R.layout.dia_paywindow, null);
        vh = new ViewHolder(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setView(view);
        builder.setCancelable(false);
//        vh.tv_forgetpaypwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                show.dismiss();
//                ctx.startActivity(new Intent(ctx, ChangePayPwdActivity.class));
//            }
//        });
        vh.pay_pwd.setInputCallBack(new PswInputView.InputCallBack() {
            @Override
            public void onInputFinish(String result) {
                resultCall.result(result);
                show.dismiss();
            }
        });
        show = builder.show();
    }

   private static class ViewHolder {
        public View rootView;
        public PswInputView pay_pwd;
        public TextView tv_forgetpaypwd;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.pay_pwd = (PswInputView) rootView.findViewById(R.id.pay_pwd);
            this.tv_forgetpaypwd = (TextView) rootView.findViewById(R.id.tv_forgetpaypwd);
        }

    }
    public interface ResultCallBack{
        void result(String result);
    }
    private static ResultCallBack resultCall;

    public static void setResultCallBack(ResultCallBack resultCallBack) {
        resultCall = resultCallBack;
    }
}
