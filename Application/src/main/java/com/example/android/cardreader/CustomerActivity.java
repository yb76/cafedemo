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
        cardInfo.getInstance().setNextStep(cardInfo.step_action.STEP_NONE.ordinal());
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

                    cardInfo.getInstance().setCust(txt_fname.getText().toString()
                            , txt_lname.getText().toString()
                            , txt_email.getText().toString()
                            , txt_mobile.getText().toString());

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Deposit money")
                            .setMessage("Deposit Money?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    cardInfo.getInstance().setNextStep(cardInfo.step_action.STEP_TAPCARD_WRITE_CUST_DEPOSIT.ordinal());
                                    Intent intent1 = new Intent(CustomerActivity.this, AmountActivity.class);
                                    startActivity(intent1);
                                    CustomerActivity.this.finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    cardInfo.getInstance().setNextStep(cardInfo.step_action.STEP_TAPCARD_WRITE_CUST.ordinal());
                                    Intent intent1 = new Intent(CustomerActivity.this, TapcardActivity.class);
                                    startActivity(intent1);
                                    CustomerActivity.this.finish();

                                }
                            })
                            .show();

/*
                    int nextstep = cardInfo.getInstance().getNextStep() ;
                    if(nextstep == cardInfo.step_action.STEP_DEPOSIT.ordinal()) {
                        Intent intent1 = new Intent(CustomerActivity.this, AmountActivity.class);
                        startActivity(intent1);
                    } else if(nextstep == cardInfo.step_action.STEP_TAPCARD.ordinal()){
                        Intent intent1 = new Intent(CustomerActivity.this, TapcardActivity.class);
                        startActivity(intent1);
                    } else {
                        //Intent intent1 = new Intent(CustomerActivity.this, TapcardActivity.class);
                        //startActivity(intent1);
                        //this.finish();

                    }
*/
                }
                break;

			//case R.id.btn_customer_cancel :
			//	this.finish();
			//	break;
		}

    }


}
