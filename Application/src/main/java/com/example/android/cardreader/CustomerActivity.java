package com.example.android.cardreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class CustomerActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setContentView(R.layout.activity_customer);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_customer_save :
				this.finish();
				break;

			case R.id.btn_customer_cancel :
				this.finish();
				break;
		}
	}
}
