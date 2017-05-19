package com.bangqu.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bangqu.adapter.CalendarAdapter;
import com.bangqu.lib.R;
import com.longtu.base.util.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 豚趣 on 2016/12/19.
 */
public class CalendarView extends RelativeLayout implements View.OnClickListener{

    private ImageView  prevMonth ,nextMonth ;
    private TextView tvCurrentMonth;
    private ViewFlipper flipper;

    private String currentYear = "";
    private String currentMonth = "";
    private String currentDay = "";
    private static int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");

    private View view;

    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";

    private GridView gridView = null;

    private GestureDetector gestureDetector = null;

    private CalendarAdapter calV = null;

    private Context context;

    /** 每次添加gridview到viewflipper中时给的标记 */
    private int gvFlag = 0;

    public CalendarView(Context context) {
        this(context,null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        view= LayoutInflater.from(context).inflate(R.layout.calendarview,null);
        prevMonth =(ImageView)view.findViewById(R.id.prevMonth);
        nextMonth  =(ImageView)view.findViewById(R.id.nextMonth);
        tvCurrentMonth  =(TextView) view.findViewById(R.id.tvCurrentMonth);
        flipper =(ViewFlipper) view.findViewById(R.id.flipper);
        gestureDetector = new GestureDetector(context, new MyGestureListener());
        flipper.removeAllViews();
        initDate();
        calV = new CalendarAdapter(context, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        addGridView(context);
        gridView.setAdapter(calV);
        flipper.addView(gridView, 0);
        setListener();
        addView(view);
    }

    private void addGridView(Context context) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        // 取得屏幕的宽度和高度
        WindowManager windowManager = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);;
        Display display = windowManager.getDefaultDisplay();
        int Width = display.getWidth();
        int Height = display.getHeight();

        gridView = new GridView(context);
        gridView.setNumColumns(7);
        gridView.setColumnWidth(40);
        // gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        if (Width == 720 && Height == 1280) {
            gridView.setColumnWidth(40);
        }
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        // 去除gridView边框
        gridView.setVerticalSpacing(1);
        gridView.setHorizontalSpacing(1);
        gridView.setOnTouchListener(new OnTouchListener() {
            // 将gridview中的触摸事件回传给gestureDetector

            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub
                // 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
                calV.setCurrentSlected(position);
                int startPosition = calV.getStartPositon();
                int endPosition = calV.getEndPosition();
                if (startPosition <= position + 7 && position <= endPosition - 7) {
                    String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0]; // 这一天的阳历
                    // String scheduleLunarDay =
                    // calV.getDateByClickItem(position).split("\\.")[1];
                    // //这一天的阴历
                    String scheduleYear = calV.getShowYear();
                    String scheduleMonth = calV.getShowMonth();
                    Log.e("Date==>", scheduleYear + "-" + scheduleMonth + "-" + scheduleDay);
                    if (Integer.valueOf(scheduleMonth)<10){
                        scheduleMonth="0"+scheduleMonth;
                    }
                    if (Integer.valueOf(scheduleDay)<10){
                        scheduleDay="0"+scheduleDay;
                    }
                    if (dateListener!=null){
                        dateListener.getDate(scheduleYear + "-" + scheduleMonth + "-" + scheduleDay);
                    }
                    // Toast.makeText(CalendarActivity.this, "点击了该条目",
                    // Toast.LENGTH_SHORT).show();
                }else if (startPosition > position + 7){
                    String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0]; // 这一天的阳历
                    // String scheduleLunarDay =
                    // calV.getDateByClickItem(position).split("\\.")[1];
                    // //这一天的阴历
                    String scheduleYear = calV.getShowYear();
                    String scheduleMonth = calV.getShowMonth();
                    Log.e("Date==>", scheduleYear + "-" + scheduleMonth + "-" + scheduleDay);
                    if (Integer.valueOf(scheduleMonth)-1>=1){
                        scheduleMonth=(Integer.valueOf(scheduleMonth)-1)+"";
                    }else {
                        scheduleMonth="12";
                        scheduleYear=(Integer.valueOf(scheduleYear)-1)+"";
                    }
                    if (Integer.valueOf(scheduleMonth)<10){
                        scheduleMonth="0"+scheduleMonth;
                    }
                    if (Integer.valueOf(scheduleDay)<10){
                        scheduleDay="0"+scheduleDay;
                    }
                    if (dateListener!=null){
                        dateListener.getDate(scheduleYear + "-" + scheduleMonth + "-" + scheduleDay);
                    }
                }else {
                    String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0]; // 这一天的阳历
                    // String scheduleLunarDay =
                    // calV.getDateByClickItem(position).split("\\.")[1];
                    // //这一天的阴历
                    String scheduleYear = calV.getShowYear();
                    String scheduleMonth = calV.getShowMonth();
                    Log.e("Date==>", scheduleYear + "-" + scheduleMonth + "-" + scheduleDay);
                    if (Integer.valueOf(scheduleMonth)+1<=12){
                        scheduleMonth=(Integer.valueOf(scheduleMonth)+1)+"";
                    }else {
                        scheduleMonth="1";
                        scheduleYear=(Integer.valueOf(scheduleYear)+1)+"";
                    }
                    if (Integer.valueOf(scheduleMonth)<10){
                        scheduleMonth="0"+scheduleMonth;
                    }
                    if (Integer.valueOf(scheduleDay)<10){
                        scheduleDay="0"+scheduleDay;
                    }
                    if (dateListener!=null){
                        dateListener.getDate(scheduleYear + "-" + scheduleMonth + "-" + scheduleDay);
                    }
                }
            }
        });
        gridView.setLayoutParams(params);
    }

    private void setListener() {
        prevMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);
    }

    private void initDate(){
        Date date = new Date();
        currentDate = sdf.format(date); // 当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
        tvCurrentMonth.setText(year_c+"年"+month_c+"月");
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId()==R.id.nextMonth){
            enterNextMonth(gvFlag);
        }else  if (v.getId()==R.id.prevMonth){
            enterPrevMonth(gvFlag);
        }
    }

    /**
     * 移动到下一个月
     *
     * @param gvFlag
     */
    private void enterNextMonth(int gvFlag) {
        addGridView(context); // 添加一个gridView
        jumpMonth++; // 下一个月

        calV = new CalendarAdapter(context, this.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        gridView.setAdapter(calV);
        addTextToTopTextView(tvCurrentMonth); // 移动到下一月后，将当月显示在头标题中
        gvFlag++;
        flipper.addView(gridView, gvFlag);
        flipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_left_out));
        flipper.showNext();
        flipper.removeViewAt(0);
    }

    /**
     * 添加头部的年份 闰哪月等信息
     *
     * @param view
     */
    public void addTextToTopTextView(TextView view) {
        StringBuffer textDate = new StringBuffer();
        // draw = getResources().getDrawable(R.drawable.top_day);
        // view.setBackgroundDrawable(draw);
        textDate.append(calV.getShowYear()).append("年").append(calV.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
    }


    /**
     * 移动到上一个月
     *
     * @param gvFlag
     */
    private void enterPrevMonth(int gvFlag) {
        addGridView(context); // 添加一个gridView
        jumpMonth--; // 上一个月

        calV = new CalendarAdapter(context, this.getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        gridView.setAdapter(calV);
        gvFlag++;
        addTextToTopTextView(tvCurrentMonth); // 移动到上一月后，将当月显示在头标题中
        flipper.addView(gridView, gvFlag);

        flipper.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_out));
        flipper.showPrevious();
        flipper.removeViewAt(0);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            int gvFlag = 0; // 每次添加gridview到viewflipper中时给的标记
            if (e1.getX() - e2.getX() > 120) {
                // 像左滑动
                enterNextMonth(gvFlag);
                return true;
            } else if (e1.getX() - e2.getX() < -120) {
                // 向右滑动
                enterPrevMonth(gvFlag);
                return true;
            }
            return false;
        }
    }

    private DateListener dateListener;

    public void setDateListener(DateListener dateListener){
        this.dateListener=dateListener;
    }

    public interface DateListener{
        void getDate(String date);
    }

}
