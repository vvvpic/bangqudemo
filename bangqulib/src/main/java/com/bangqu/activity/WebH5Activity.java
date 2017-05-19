package com.bangqu.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSONObject;
import com.bangqu.base.activity.BaseActivity;
import com.bangqu.bean.BankMineBean;
import com.bangqu.bean.TeacherSearchBean;
import com.bangqu.lib.R;
import com.bangqu.utils.Contact;
import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * H5页面统一加载
 */
public class WebH5Activity extends BaseActivity {
    private WebView wvh5;
    private String url = "";
    private String title = "";
    private ProgressBar pbCash;
    private String right;
    private int bank=0;
    private TeacherSearchBean.TeachersBean teacher;

    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private ValueCallback<Uri> mUploadMessage;// 表单的数据信息
    private Uri imageUri;
    private final static int FILECHOOSER_RESULTCODE = 1;// 表单的结果回调</span>

    private Message msg;
    private BankMineBean bankMineBean;
    private boolean bankbool=false;
    public boolean salesbool=false;
    private String editsuffix;
    private Button btnSubmit;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    bankMineBean= JSONObject.parseObject(msg.obj.toString(),BankMineBean.class);
                    if (bankMineBean!=null&&bankMineBean.getStatus().equals("1")){
                        if (bankMineBean.getBank()!=null&&!StringUtils.isEmpty(bankMineBean.getBank().getBankName())){
                            bankbool=true;
                        }else {
                            bankbool=false;
                        }
                    }else {
                        bankbool=false;
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.tvRight){

        }else if (v.getId()==R.id.btnSubmit){

        }
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_web);
    }

    @Override
    public void initViews() {
        wvh5 = (WebView) findViewById(R.id.Wvh5);
        pbCash = (ProgressBar) findViewById(R.id.pbCash);
        pbCash.setVisibility(View.VISIBLE);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
    }


    @Override
    public void initDatas() {

        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        right=getIntent().getStringExtra("right");
        bank=getIntent().getIntExtra("bank",0);
        teacher= (TeacherSearchBean.TeachersBean) getIntent().getSerializableExtra("teacher");

        if (!StringUtils.isEmpty(right)){
            setRightTeTVisibility(View.VISIBLE);
            setRightText(right);
            if (right.equals("提现")){
                params=new RequestParams();
                if (Contact.userLoginBean!=null){
                    params.put("accessToken", Contact.userLoginBean.getAccessToken().getAccessToken());
                }

                pullpost("bank/mine",params);
            }

            if (teacher!=null){
                btnSubmit.setVisibility(View.VISIBLE);
                btnSubmit.setText("购买");
            }

        }


        pbCash.setMax(100);


        //支持javascript
        wvh5.getSettings().setJavaScriptEnabled(true);
        wvh5.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);// 支持通过JS打开新窗口
        // 设置可以支持缩放
        wvh5.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        wvh5.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        wvh5.getSettings().setUseWideViewPort(true);
        wvh5.getSettings().setAllowFileAccess(true);
        wvh5.getSettings().setLoadsImagesAutomatically(true);// 支持自动加载图片
        //自适应屏幕
        wvh5.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvh5.getSettings().setLoadWithOverviewMode(true);

        wvh5.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);

        wvh5.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                Log.e("progress==>", progress + "");
                pbCash.setProgress(progress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url,
                                     String message, JsResult result) {
                result.confirm();

                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url,
                                       String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url,
                                            String message, JsResult result) {
                return super.onJsBeforeUnload(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url,
                                      String message, String defaultValue,
                                      JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onShowFileChooser(WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             FileChooserParams fileChooserParams) {
                mUploadCallbackAboveL=filePathCallback;
                take();
                return true;
            }
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage=uploadMsg;
                take();
            }
            public void openFileChooser(ValueCallback<Uri> uploadMsg,String acceptType) {
                mUploadMessage=uploadMsg;
                take();
            }
            public void openFileChooser(ValueCallback<Uri> uploadMsg,String acceptType, String capture) {
                mUploadMessage=uploadMsg;
                take();
            }
        });

        wvh5.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.equals("mod:index")){
                    finish();
                    return false;
                }

                if (url.indexOf("http://api.xianding.daoqun.com/sale/sales.jsp")>-1){
                    salesbool=true;
                    finish();
                    return true;
                }

                if (url.indexOf("http://api.xianding.daoqun.com/userShop/index.jsp")>-1
                        ||url.indexOf("http://api.xianding.daoqun.com/customer/index.jsp")>-1){
                    finish();
                    return true;
                }

                intent = new Intent(WebH5Activity.this, WebH5Activity.class);
                if (url.indexOf("http://api.xianding.daoqun.com/customer")>-1){
                    intent.putExtra("title", "客户信息");
                    intent.putExtra("right","编辑客户");
                }/*else if (url.indexOf("http://api.xianding.daoqun.com/sale/edit/editGift.jsp")>-1){
                    intent.putExtra("title", "编辑");
                }*/else {
                    intent.putExtra("title", title);
                }

                if (url.indexOf("http://api.xianding.daoqun.com/shop/apply.jsp")>-1){
                    intent.putExtra("url", "http://api.xianding.daoqun.com/shop/apply.jsp?accessToken=" + Contact.userLoginBean.getAccessToken().getAccessToken()
                            +"&lng="+Contact.Longitude+"&lat="+Contact.Latitude);
                }else {
                    intent.putExtra("url", url);
                }
//                intent.putExtra("url", url);

                Jump(intent);

                Log.e("url=>",url);

                if (url.equals("http://api.xianding.daoqun.com/shop/waitting.jsp")
                        ||url.equals("http://api.xianding.daoqun.com/bank/sucess.jsp")||url.indexOf("http://api.xianding.daoqun.com/shop/apply.jsp")>-1){
                    finish();
                }

                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pbCash.setVisibility(View.VISIBLE);
            }

            public void onPageFinished(WebView view, String url) {
                pbCash.setVisibility(View.GONE);
            }

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                pbCash.setVisibility(View.GONE);

            }
        });

        wvh5.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

            }
        });

        if (!StringUtils.isEmpty(url))
            wvh5.loadUrl(url);

        if (url.indexOf("http://api.xianding.daoqun.com/customer/view.jsp")>-1){
            editsuffix=url.substring("http://api.xianding.daoqun.com/customer/view.jsp".length());
            Log.e("editsuffix==>",editsuffix);
        }

        Log.e("url=>",url);
    }

    @Override
    public void setDatas() {
        if (!StringUtils.isEmpty(title)) {
            setTitle(title);
        }
    }

    private void Binding(){

    }

    @Override
    public void setListener() {
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void ResumeDatas() {
        if (Contact.verify){
            finish();
        }

        if (url.equals("http://api.xianding.daoqun.com/shop/waitting.jsp")){
            Contact.verify=true;
        }

        if (!StringUtils.isEmpty(right)&&right.equals("添加促销")){
            wvh5.reload();
        }

        if (!StringUtils.isEmpty(right)&&right.equals("添加客户")){
            wvh5.reload();
        }

        if (!StringUtils.isEmpty(right)&&right.equals("编辑客户")){
            wvh5.reload();
        }

        if (salesbool){
            salesbool=false;
            wvh5.reload();
        }
    }


    private void take(){
        File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
        // Create the storage directory if it does not exist
        if (! imageStorageDir.exists()){
            imageStorageDir.mkdirs();
        }
        File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        imageUri = Uri.fromFile(file);

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for(ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent i = new Intent(captureIntent);
            i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            i.setPackage(packageName);
            i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraIntents.add(i);

        }
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        Intent chooserIntent = Intent.createChooser(i,"Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        this.startActivityForResult(chooserIntent,  FILECHOOSER_RESULTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==FILECHOOSER_RESULTCODE)
        {
            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            }
            else  if (mUploadMessage != null) {

                if (result != null) {
                    String path = getPath(getApplicationContext(),
                            result);
                    Uri uri = Uri.fromFile(new File(path));
                    mUploadMessage
                            .onReceiveValue(uri);
                } else {
                    mUploadMessage.onReceiveValue(imageUri);
                }
                mUploadMessage = null;
            }
        }
    }

    @SuppressWarnings("null")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != FILECHOOSER_RESULTCODE
                || mUploadCallbackAboveL == null) {
            return;
        }

        Uri[] results = null;

        if (resultCode == Activity.RESULT_OK) {

            if (data == null) {

                results = new Uri[]{imageUri};
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();

                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        if(results!=null){
            mUploadCallbackAboveL.onReceiveValue(results);
            mUploadCallbackAboveL = null;
        }else{
            results = new Uri[]{imageUri};
            mUploadCallbackAboveL.onReceiveValue(results);
            mUploadCallbackAboveL = null;
        }

        return;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    @Override
    public void OnReceive(String requestname, String response) {
        msg=new Message();
        if (requestname.equals("bank/mine")){
            msg.what=1;
        }
        msg.obj=response;
        handler.sendMessage(msg);
    }
}
