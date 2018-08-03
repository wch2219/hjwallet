package zz.hjzn.hjwallet.zxing;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zz.hjzn.hjwallet.BuildConfig;
import zz.hjzn.hjwallet.R;
import zz.hjzn.hjwallet.activitys.EntryOtherActivity;
import zz.hjzn.hjwallet.activitys.InPutAddressActivity;
import zz.hjzn.hjwallet.activitys.StartPaymentActivity;
import zz.hjzn.hjwallet.base.BaseActivity;
import zz.hjzn.hjwallet.base.Presenter;
import zz.hjzn.hjwallet.model.PersionInfoModel;
import zz.hjzn.hjwallet.utils.IntentTag;
import zz.hjzn.hjwallet.utils.NetUtils;
import zz.hjzn.hjwallet.utils.Parments;
import zz.hjzn.hjwallet.utils.RegularUils;
import zz.hjzn.hjwallet.utils.RequestCode;
import zz.hjzn.hjwallet.utils.UploadPicUtiles;
import zz.hjzn.hjwallet.zxing.camera.CameraManager;
import zz.hjzn.hjwallet.zxing.decoding.CaptureActivityHandler;
import zz.hjzn.hjwallet.zxing.decoding.InactivityTimer;


/**
 * Initial the camera
 *
 * @author Ryan.Tang
 */
public class CaptureActivity extends BaseActivity implements Callback {

    @BindView(R.id.preview_view)
    SurfaceView previewView;
    @BindView(R.id.viewfinder_view)
    ViewfinderView viewfinderView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.btn_seletepic)
    Button btn_seletepic;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private Camera camera;
    private boolean isOpen = true;
    private Camera.Parameters parameters;
    private String resultString;

    @Override
    protected void initData() {
        tvTitle.setText(R.string.CaptureCodeTitle);
        tvRight.setText(R.string.NoShibie);
        initTabBar(toolBar, false);
    }

    @Override
    protected Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.camera);
        CameraManager.init(getApplication());
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
//        initToolBar(toolbar);

    }


    @Override
    public void initListener() {

        //开启闪光灯
        imageView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                camera = CameraManager.getCamera();
                parameters = camera.getParameters();
                if (isOpen) {
                    //开启闪光灯
                    imageView2.setBackgroundResource(R.mipmap.icon_shanguang);
                    parameters.setFlashMode(parameters.FLASH_MODE_TORCH);
                    camera.setParameters(parameters);
                    isOpen = false;
                } else {
                    //关闭闪光灯
                    imageView2.setBackgroundResource(R.mipmap.icon_shanguangbu);
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(parameters);
                    isOpen = true;
                }


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);


        SurfaceHolder surfaceHolder = surfaceView.getHolder();

        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        resultString = result.getText();
        //FIXME
        if (resultString.equals("")) {
            Toast.makeText(CaptureActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
        } else {
            boolean http = RegularUils.isHttp(resultString);
//            if (http) {//是网址
//                Uri uri = Uri.parse(resultString);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
//            }else {
//			System.out.println("Result:"+resultString);
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putString("result", resultString);
//            resultIntent.putExtras(bundle);
//            this.setResult(RESULT_OK, resultIntent);

            if (!TextUtils.isEmpty(resultString) && resultString.length() == 34) {
                getUserInfo(resultString);
            } else {
                Intent intent = new Intent(ctx, EntryOtherActivity.class);
                intent.putExtra(IntentTag.ResultCode, resultString);
                startActivity(intent);
                CaptureActivity.this.finish();
            }
        }
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo(String result) {
        Map<String, String> map = new HashMap<>();
        map.put(Parments.walletAddress, result);
        mPreenter.fetch(map, true, NetUtils.AddressGetUserInfo, "");
    }

    @Override
    public void showData(String s) throws IOException {
        dissProgress();
        wch(s);
        PersionInfoModel persionInfoModel = gson.fromJson(s, PersionInfoModel.class);
        if (persionInfoModel.getErrCode() == RequestCode.SuccessCode) {
            Intent intent = new Intent(ctx, StartPaymentActivity.class);
            intent.putExtra(IntentTag.ResultCode, persionInfoModel);
            intent.putExtra(IntentTag.walletAddress, resultString);
            startActivity(intent);
        } else {
            Intent intent = new Intent(ctx, EntryOtherActivity.class);
            intent.putExtra(IntentTag.ResultCode, resultString);
            startActivity(intent);
            CaptureActivity.this.finish();
        }

    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        if (camera != null) {
            CameraManager.stopPreview();
        }
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };


    @OnClick({R.id.btn_seletepic, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_seletepic:
                UploadPicUtiles.openAlbum((Activity) ctx, BuildConfig.APPLICATION_ID);
                break;
            case R.id.tv_right:
                startActivity(new Intent(ctx, InPutAddressActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2 && data != null) {
            File filePath1 = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri uri = data.getData();
                Glide.with(ctx).asFile().load(data.getData()).into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        scanningImage(resource.getPath());

                    }
                });
            } else {
                filePath1 = UploadPicUtiles.getFilePath1(data, ctx);
                Glide.with(ctx).asFile().load(filePath1).into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        scanningImage(resource.getPath());
                    }
                });
            }
        }
    }

    private Bitmap scanBitmap;

    protected void scanningImage(String path) {

        // DecodeHintType 和EncodeHintType
        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8"); // 设置二维码内容的编码
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 先获取原大小
        scanBitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false; // 获取新的大小

        int sampleSize = (int) (options.outHeight / (float) 200);

        if (sampleSize <= 0)
            sampleSize = 1;
        options.inSampleSize = sampleSize;
        scanBitmap = BitmapFactory.decodeFile(path, options);
        int width = scanBitmap.getWidth();
        int height = scanBitmap.getHeight();
        int[] data = new int[width * height];
        scanBitmap.getPixels(data, 0, width, 0, 0, width, height);
        RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        new QrCodeAsyncTask().execute(bitmap);
    }

    class QrCodeAsyncTask extends AsyncTask<BinaryBitmap, Void, Result> {

        @Override
        protected Result doInBackground(BinaryBitmap... params) {
            QRCodeReader reader = new QRCodeReader();
            Result result = null;
            try {
                result = reader.decode(params[0]);
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (ChecksumException e) {
                e.printStackTrace();
            } catch (FormatException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);


            String resultString = result.getText();
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", resultString);
            resultIntent.putExtras(bundle);
            CaptureActivity.this.setResult(RESULT_OK, resultIntent);
            CaptureActivity.this.finish();
        }
    }
}