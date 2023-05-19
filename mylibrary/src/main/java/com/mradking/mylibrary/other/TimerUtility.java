package com.mradking.mylibrary.other;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimerUtility {
    private CountDownTimer countDownTimer;

    public TimerUtility(long timerDuration, long interval, TextView textView, TimerListener listener) {
        countDownTimer = new CountDownTimer(timerDuration, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                textView.setText("Wait Seconds : " + secondsRemaining);
            }

            @Override
            public void onFinish() {
                textView.setText("Setup is Done Start Soon...");
                if (listener != null) {
                    listener.onTimerFinish();
                }
            }
        };
    }

    public void startTimer() {
        countDownTimer.start();
    }

    public void cancelTimer() {
        countDownTimer.cancel();
    }

    public interface TimerListener {
        void onTimerFinish();
    }
}
