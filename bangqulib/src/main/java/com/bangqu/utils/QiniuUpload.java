package com.bangqu.utils;

import android.content.Context;

import com.longtu.base.util.StringUtils;
import com.longtu.base.util.ToastUtils;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.KeyGenerator;
import com.qiniu.android.storage.Recorder;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by 唯图 on 2016/7/18.
 */
public class QiniuUpload {
    private static UploadManager uploadManager;

    public static void UploadImages(final String path, final String key, final String token, final Context context, final UpCompletionHandler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!StringUtils.isEmpty(token)) {
                    uploadManager = new UploadManager();
                    uploadManager.put(path, key, token,
                           handler, null);
                } else {
                    ToastUtils.show(context, "未知原因上传失败！");
                }
            }
        }).start();

    }

    public static void UploadFiles(final String path, final String key, final String token, final Context context, final UpCompletionHandler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Recorder recorder = new FileRecorder(path);
                //默认使用key的url_safe_base64编码字符串作为断点记录文件的文件名
                //避免记录文件冲突（特别是key指定为null时），也可自定义文件名(下方为默认实现)：
                    KeyGenerator keyGen = new KeyGenerator(){
                        public String gen(String key, File file){
                            // 不必使用url_safe_base64转换，uploadManager内部会处理
                            // 该返回值可替换为基于key、文件内容、上下文的其它信息生成的文件名
                            return key + "_._" + new StringBuffer(file.getAbsolutePath()).reverse();
                        }
                    };

                    // 重用uploadManager。一般地，只需要创建一个uploadManager对象
                    //UploadManager uploadManager = new UploadManager(recorder);  // 1
                   // UploadManager uploadManager = new UploadManager(recorder, keyGen); // 2
                    // 或在初始化时指定：
                    Configuration config = new Configuration.Builder()
                            // recorder分片上传时，已上传片记录器
                            // keyGen分片上传时，生成标识符，用于片记录器区分是哪个文件的上传记录
                            .recorder(recorder, keyGen)
                            .build();
                    uploadManager = new UploadManager(config);


                    if (!StringUtils.isEmpty(token)) {
                        uploadManager.put(path, key, token,
                                handler, null);
                    } else {
                        ToastUtils.show(context, "未知原因上传失败！");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
