package zz.hjzn.hjwallet.activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zz.hjzn.hjwallet.MyApplication;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.utils.QRCode;

/**
 * 我的收款码
 */
public class MyReceiptCodeActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_savepic)
    TextView tvSavepic;
    private Bitmap bitmapcode;//保存的二维码图片

    @Override
    protected void initView() {
        setContentView(R.layout.activity_my_receipt_code);
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的收款码");
        tvRight.setText(R.string.receipt_history);
        initTabBar(toolBar, false);
        String walletAddress = MyApplication.persionInfoModel.getResult().getWalletAddress();
        bitmapcode = QRCode.createQRCodeWithLogo6(walletAddress,
                500, drawableToBitmap(getResources().getDrawable(R.mipmap.logo)));
        ivCode.setImageBitmap(bitmapcode);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    @OnClick({R.id.tv_right, R.id.tv_savepic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right:
                startActivity(new Intent(ctx,BillDescActivity.class));
                break;
            case R.id.tv_savepic:
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                saveBitmap(bitmapcode,format.format(new Date())+".JPEG");
                break;
        }
    }


    /*
     * 保存文件，文件名为当前日期
     */
    public void saveBitmap(Bitmap bitmap, String bitName){
        String fileName ;
        File file ;
        if(Build.BRAND .equals("Xiaomi") ){ // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera/"+bitName ;
        }else{  // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath()+"/DCIM/"+bitName ;
        }
        file = new File(fileName);

        if(file.exists()){
            file.delete();
        }
        FileOutputStream out;
        try{
            out = new FileOutputStream(file);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if(bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out))
            {
                out.flush();
                out.close();
                // 插入图库
                MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), bitName, null);
                Toast.makeText(ctx, "保存成功", Toast.LENGTH_SHORT).show();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Toast.makeText(ctx, "保存失败", Toast.LENGTH_SHORT).show();

        }
        // 发送广播，通知刷新图库的显示
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));

    }
}
