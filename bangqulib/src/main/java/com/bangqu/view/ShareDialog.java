package com.bangqu.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bangqu.bean.ShareBean;
import com.bangqu.lib.R;
import com.bangqu.utils.Contact;
import com.bangqu.utils.ImageUtils;
import com.longtu.base.util.ToastUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by 唯图 on 2016/9/4.
 */
public class ShareDialog extends Dialog implements View.OnClickListener,IUiListener {

    private View view;
    /**
     * QQ
     */
    public Tencent tencent;
    public Bundle param;
    /***
     * 微信
     */
    public static IWXAPI api;

    private LinearLayout  llShareQQ, llShareQzone, llShareWeixin, llShareWeixincircle;
    private Button btnCancel;

    private Context context;

    private ShareBean shareBean;

    public void setShare(ShareBean shareBean){
        this.shareBean=shareBean;
    }


    public ShareDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
        view= LayoutInflater.from(context).inflate(R.layout.share_window,null);
        setContentView(view);
        llShareQQ=(LinearLayout)view.findViewById(R.id.llShareQQ);
        llShareQzone =(LinearLayout)view.findViewById(R.id.llShareQzone);
        llShareWeixin=(LinearLayout)view.findViewById(R.id.llShareWeixin);
        llShareWeixincircle =(LinearLayout)view.findViewById(R.id.llShareWeixincircle);
        btnCancel=(Button) view.findViewById(R.id.btnCancel);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        /***
         * QQ
         */
        tencent = Tencent.createInstance("1106166424", context);
        /**
         * 微信
         */
        api= WXAPIFactory.createWXAPI(context, "wxce3287b52eccea84",true);

        api.registerApp("wxce3287b52eccea84");


        btnCancel.setOnClickListener(this);
        llShareQQ.setOnClickListener(this);
        llShareQzone.setOnClickListener(this);
        llShareWeixin.setOnClickListener(this);
        llShareWeixincircle.setOnClickListener(this);
    }

    public ShareDialog(Context context) {
        this(context,  R.style.loadingDialog);
    }

    @Override
    public void onClick(View v) {
      if(v.getId()== R.id.btnCancel) {
          if (isShowing()) {
              dismiss();
          }
      }else if (v.getId()== R.id.llShareQQ) {
          param = new Bundle();
          param.putString(QQShare.SHARE_TO_QQ_TITLE, shareBean.title);// 标题
          param.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                  shareBean.url);// 连接地址
          param.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareBean.SUMMARY); // 内容
          param.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareBean.IMAGE_URL); // 网络图片
          param.putString(QQShare.SHARE_TO_QQ_APP_NAME, shareBean.APP_NAME);
          param.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
//                param.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
          if (tencent != null) {
              tencent.shareToQQ((Activity) context, param, this);
          } else {
              tencent = Tencent.createInstance("1106166424", context);
              tencent.shareToQQ((Activity) context, param, this);
          }
          dismiss();
      }else if (v.getId()== R.id.llShareQzone) {
          param = new Bundle();
          param.putString(QQShare.SHARE_TO_QQ_TITLE, shareBean.title);// 标题
          param.putString(QQShare.SHARE_TO_QQ_TARGET_URL,
                  shareBean.url);// 连接地址
          param.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareBean.SUMMARY); // 内容
          param.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareBean.IMAGE_URL); // 网络图片
          param.putString(QQShare.SHARE_TO_QQ_APP_NAME, shareBean.APP_NAME);
          param.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
          param.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
          if (tencent != null) {
              tencent.shareToQQ((Activity) context, param, this);
          } else {
              tencent = Tencent.createInstance("1106166424", context);
              tencent.shareToQQ((Activity) context, param, this);
          }
          dismiss();
      }else if (v.getId()== R.id.llShareWeixin) {
          if (Contact.isWeixinAvilible(context)) {
              new Thread(new Runnable() {
                  @Override
                  public void run() {
                      try {
                          if (Contact.isWeixinAvilible(context)) {
                              WXWebpageObject webpage = new WXWebpageObject();
                              webpage.webpageUrl = shareBean.url;
                              WXMediaMessage msg = new WXMediaMessage(webpage);
                              msg.title = shareBean.title;
                              msg.description = shareBean.SUMMARY;

                              Bitmap bmp = ImageLoader.getInstance().loadImageSync(shareBean.IMAGE_URL);
                              Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                              bmp.recycle();
                              thumbBmp = createBitmapThumbnail(thumbBmp);
                              msg.thumbData = ImageUtils.bmpToByteArray(thumbBmp, true);

                              SendMessageToWX.Req req = new SendMessageToWX.Req();
                              req.transaction = buildTransaction("webpage");
                              req.message = msg;
                              req.scene = SendMessageToWX.Req.WXSceneSession;
                              api.sendReq(req);
                          } else {
                              ToastUtils.show(context, "请安装微信客户端");
                          }
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  }
              }).start();
          }else {
              ToastUtils.show(context,"请先安装微信客户端");
          }
          dismiss();
      }else if (v.getId()== R.id.llShareWeixincircle) {
          if (Contact.isWeixinAvilible(context)){
          new Thread(new Runnable() {
              @Override
              public void run() {
                  try {
                      if (Contact.isWeixinAvilible(context)) {
                          WXWebpageObject webpage = new WXWebpageObject();
                          webpage.webpageUrl = shareBean.url;
                          WXMediaMessage msg = new WXMediaMessage(webpage);
                          msg.title = shareBean.title;
                          msg.description = shareBean.SUMMARY;

                          Bitmap bmp = ImageLoader.getInstance().loadImageSync(shareBean.IMAGE_URL);
                          Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                          bmp.recycle();
                          thumbBmp = createBitmapThumbnail(thumbBmp);
                          msg.thumbData = ImageUtils.bmpToByteArray(thumbBmp, true);

                          SendMessageToWX.Req req = new SendMessageToWX.Req();
                          req.transaction = buildTransaction("webpage");
                          req.message = msg;
                          req.scene = SendMessageToWX.Req.WXSceneTimeline;
                          api.sendReq(req);
                      }else {
                          ToastUtils.show(context,"请安装微信客户端");
                      }
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
          }).start();
          }else {
              ToastUtils.show(context,"请先安装微信客户端");
          }
          dismiss();
      }
    }

    public Bitmap createBitmapThumbnail(Bitmap bitMap) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想要的大小
        int newWidth = 99;
        int newHeight = 99;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height,
                matrix, true);
        return newBitMap;
    }

    private Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>33) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }


    @Override
    public void onComplete(Object values) {
        if (values == null) {
            ToastUtils.show(context, "分享失败！");
            return;
        }
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(values.toString());
            if (jsonObject.has("ret")) {
                if (jsonObject.getString("ret").equals("0")) {
                    ToastUtils.show(context, "分享成功！");
                } else {
                    ToastUtils.show(context, "分享失败！");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {
            ToastUtils.show(context, "取消分享！");
    }
}
