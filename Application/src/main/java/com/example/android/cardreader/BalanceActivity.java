package com.example.android.cardreader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.common.logger.Log;

import java.io.IOException;

public class BalanceActivity extends Activity {
    private ProgressBar mProgress = null;
    private int mProgressStatus = 1;
    private Handler mHandler1 = new Handler();

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//        mProgress = (ProgressBar) findViewById(R.id.progressBar1);
//        // Start lengthy operation in a background thread
//        new Thread(new Runnable() {
//            public void run() {
//                while (mProgressStatus < 100) {
//                    mProgressStatus = mProgressStatus+1;
//                    if (mProgressStatus > 99) mProgressStatus = 1;
//
//                    // Update the progress bar
//                    mHandler1.post(new Runnable() {
//                        public void run() {
//                            mProgress.setProgress(mProgressStatus);
//                        }
//                    });
//                }
//            }
//        }).start();
//


        initView();
        TextView t_bal = (TextView)findViewById(R.id.tv_balance_num);
        t_bal.setText( String.format("%.2f", cardInfo.getInstance().getBal()/100.0 ));

    }

	private void initView() {
		setContentView(R.layout.activity_balance);
        // Get the Drawable custom_progressbar

        //NetworkConnect nc = new NetworkConnect.execute("http://www.google.com"););
		//mHandler.sendEmptyMessageDelayed(0, 10000);
	}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.isTracking()
                && !event.isCanceled()) {
            Intent intent = new Intent(BalanceActivity.this, IDLEActivity.class);
            startActivity(intent);
            BalanceActivity.this.finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			Intent intent = new Intent(BalanceActivity.this, IDLEActivity.class);
			startActivity(intent);
			BalanceActivity.this.finish();
		};
	};



}
