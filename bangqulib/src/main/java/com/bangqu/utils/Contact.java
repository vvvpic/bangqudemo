package com.bangqu.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;

import com.bangqu.bean.ProvinceBean;
import com.bangqu.bean.UserLoginBean;
import com.longtu.base.util.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用信息
 */
public class Contact {
    public static UserLoginBean userLoginBean;
    public final static String USERINFO = "userinfo";
    public static String Openid;

    public static Double Latitude = 0.0, Longitude = 0.0;
    public static boolean verify = false;
    public static SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static ProvinceBean provinceBean;

    public final static String BASE_URL = "http://api.xianding.daoqun.com/";

    /**
     * 邮箱验证
     *
     * @param mobiles
     * @return
     */
    public static boolean isEmailNO(String mobiles) {
        Pattern pattern = Pattern.compile(
                "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher m = pattern.matcher(mobiles);
        return m.matches();
    }

    public static String getPhotopath() {
        // 文件夹路径
        String pathUrl = Environment.getExternalStorageDirectory()+"/ubei/camera/";
        Calendar.getInstance(Locale.CHINA);
        Random random=new Random();
        String imageName  = (new DateFormat()).format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA))+"_"+random.nextInt(10000) + ".jpg";
        File file = new File(pathUrl);
        if (!file.exists()) {
            file.mkdirs();// 创建文件夹
        }
        return  pathUrl + imageName;
    }

    /**
     * 将图片联合路径处理成单个
     *
     * @param imgs
     * @return
     */
    public static List<String> getPhotos(String imgs) {
        List<String> listphotos = new ArrayList<String>();
        int index = imgs.indexOf(",");
        String imgurl;
        while (index > -1) {
            imgurl = imgs.substring(0, index);
            listphotos.add(imgurl);
            Log.e("imgUrl==>", imgurl);
            imgs = imgs.substring(index + 1);
            index = imgs.indexOf(",");
        }
        Log.e("imgs==>", imgs);
        if (!StringUtils.isEmpty(imgs)) {
            listphotos.add(imgs);
        }
        return listphotos;
    }

    /**
     * 判断是否是手机号
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((11[0-9])|(12[0-9])|(13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 获取文件
     *
     * @param path
     * @return
     */
    public static String getFileName(String path) {
        String suffix = path.substring(path.lastIndexOf("."));
        Log.e("suffix==>", suffix);
        return System.currentTimeMillis() + suffix;
    }

    public static void copy(String content, Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    public static String getTeacherTime(String startTime ,int months){
        int startmonth=Integer.valueOf(startTime.substring(6,7));
        Log.e("start=>",startmonth+"");
        if (startmonth+months>12){
            return (Integer.valueOf(startTime.substring(0,4))+1)+"-"+((startmonth+months)%12)+"-"+startTime.substring(9,10);
        }else {
            return startTime.substring(0,4)+"-"+((startmonth+months)%12)+"-"+startTime.substring(9,10);
        }
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

}
