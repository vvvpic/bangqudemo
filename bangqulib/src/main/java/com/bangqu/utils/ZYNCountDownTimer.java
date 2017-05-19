package com.bangqu.utils;

/*
 * 倒计时功能
 */

import android.os.CountDownTimer;

public class ZYNCountDownTimer extends CountDownTimer {
	private OnCountDownTimerListener onCountDownTimerListener;

	public ZYNCountDownTimer(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	@Override
	public void onFinish() {
		onCountDownTimerListener.OnCountDownTimerFinish();

	}

	@Override
	public void onTick(long millisUntilFinished) {
		onCountDownTimerListener.OnCountDownTimer();
	}

	/*
	 * 给倒计时定义一个接口
	 */
	public interface OnCountDownTimerListener {
		// public void onLoadMore();
		public void OnCountDownTimerFinish();
		public void OnCountDownTimer();
	}

	public void setOnCountDownTimerListener(OnCountDownTimerListener l) {
		onCountDownTimerListener = l;
	}

}
