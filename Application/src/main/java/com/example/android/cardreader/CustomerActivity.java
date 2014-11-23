package com.example.android.cardreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
                EditText txt_fname = (EditText) findViewById(R.id.tv_customer_fname);
                EditText txt_lname = (EditText) findViewById(R.id.tv_customer_lname);
                EditText txt_email = (EditText) findViewById(R.id.tv_customer_email);
                EditText txt_mobile = (EditText) findViewById(R.id.tv_customer_Mobile);
                if(txt_fname.toString().isEmpty()){
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Deposit money")
                            .setMessage("Deposit Money?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String str = "Yes pressed";
                                    //Toast.makeText(AmountActivity.this, str, 100).show();
                                    //Yes button clicked, do something
                                    Intent intent1 = new Intent(CustomerActivity.this, AmountActivity.class);
                                    startActivity(intent1);

                                }
                            })
                            .setNegativeButton("No", null)						//Do nothing on no
                            .show();

                    //this.finish();
                }
                break;

			//case R.id.btn_customer_cancel :
			//	this.finish();
			//	break;
		}
	}
}
