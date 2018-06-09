package zz.hjzn.hjwallet.lisenter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.utils.RegularUils;

public class PayMentEditetxtListener implements TextWatcher {
    private Button view;
    private Context ctx;
    private boolean isphone;
    private EditText etphone;
    private EditText etAuth;

    public PayMentEditetxtListener(Context ctx, Button view) {
        super();
        this.ctx = ctx;
        this.view = view;
        this.isphone = false;
    }

    public PayMentEditetxtListener(Context ctx, Button view, boolean isphone) {
        this.ctx = ctx;
        this.view = view;
        this.isphone = isphone;
    }

    public PayMentEditetxtListener(Context ctx, Button view, EditText phone, EditText auth) {
        super();
        this.ctx = ctx;
        this.view = view;
        this.isphone = false;
        this.etphone = phone;
        this.etAuth = auth;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String trim = s.toString().trim();
        if (isphone) {
            if (trim.length() == 11 && RegularUils.isMobileExact(trim)) {
                view.setBackgroundResource(R.drawable.shape_payment_affblue);
                view.setTextColor(ctx.getResources().getColor(R.color.white));
            } else {
                view.setBackgroundResource(R.drawable.shape_payment_affgary);
                view.setTextColor(ctx.getResources().getColor(R.color.gary_text));
            }
        } else {
            if (etphone != null) {
                String phone = etphone.getText().toString().trim();
                String auth = etAuth.getText().toString().trim();
                if (!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(auth)&&trim.length()>6) {
                    view.setBackgroundResource(R.drawable.shape_payment_affblue);
                    view.setTextColor(ctx.getResources().getColor(R.color.white));
                } else {
                    view.setBackgroundResource(R.drawable.shape_payment_affgary);
                    view.setTextColor(ctx.getResources().getColor(R.color.gary_text));
                }
            } else {

                if (trim.length() > 0) {
                    view.setBackgroundResource(R.drawable.shape_payment_affblue);
                    view.setTextColor(ctx.getResources().getColor(R.color.white));
                } else {
                    view.setBackgroundResource(R.drawable.shape_payment_affgary);
                    view.setTextColor(ctx.getResources().getColor(R.color.gary_text));
                }
            }
        }
    }
}
