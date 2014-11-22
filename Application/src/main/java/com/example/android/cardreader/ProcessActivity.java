package com.example.android.cardreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class ProcessActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setContentView(R.layout.activity_process);
		mHandler.sendEmptyMessageDelayed(0, 2000);
	}

	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			Intent intent = new Intent(ProcessActivity.this, BalanceActivity.class);
			startActivity(intent);
			ProcessActivity.this.finish();
		};
	};
}
