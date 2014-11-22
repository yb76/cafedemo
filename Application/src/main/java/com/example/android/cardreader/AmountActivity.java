package com.example.android.cardreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AmountActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setContentView(R.layout.activity_amount);
	}
	
	public void onClick(View v) {
        TextView t_dollar = (TextView)findViewById(R.id.text_amount_dollar);
        TextView t_cent = (TextView)findViewById(R.id.text_amount_cent);
        String tval_d = t_dollar.getText().toString();
        String tval_c = t_cent.getText().toString();

        long lval_d = 0;
        if(tval_d.isEmpty()) lval_d = 0;  else lval_d = Long.parseLong(tval_d);
        long lval_c = Long.parseLong(tval_c.substring(1));
        long lval_total = lval_d * 100 + lval_c;

        String s_dollar = null;
        String s_cent = null;

        if(v.getId()==R.id.btn_amount_ok && lval_total > 0) {
            Intent intent = new Intent(this, TapActivity.class);
            startActivity(intent);
        }
        else if(v.getId() ==R.id.btn_amount_del ) {
            lval_total = lval_total / 10;
            lval_d = Math.round(lval_total / 100);
            lval_c = lval_total - lval_d * 100;
            s_dollar = String.format("%d", lval_d);
            s_cent = "." + String.format("%02d", lval_c);
            t_dollar.setText(s_dollar);
            t_cent.setText(s_cent);

        }
        else if (lval_total<= 9999999) {
            switch (v.getId()) {
                case R.id.btn_amount_number0:
                    lval_total = lval_total * 10;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);
                    break;

                case R.id.btn_amount_number1:
                    lval_total = lval_total * 10 + 1;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);
                    break;

                case R.id.btn_amount_number2:
                    lval_total = lval_total * 10 + 2;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);
                    break;

                case R.id.btn_amount_number3:
                    lval_total = lval_total * 10 + 3;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);
                    break;

                case R.id.btn_amount_number4:
                    lval_total = lval_total * 10 + 4;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);

                    break;

                case R.id.btn_amount_number5:
                    lval_total = lval_total * 10 + 5;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);
                    break;

                case R.id.btn_amount_number6:
                    lval_total = lval_total * 10 + 6;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);

                    break;

                case R.id.btn_amount_number7:
                    lval_total = lval_total * 10 + 7;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);

                    break;

                case R.id.btn_amount_number8:
                    lval_total = lval_total * 10 + 8;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);

                    break;

                case R.id.btn_amount_number9:
                    lval_total = lval_total * 10 + 9;
                    lval_d = Math.round(lval_total / 100);
                    lval_c = lval_total - lval_d * 100;
                    s_dollar = String.format("%d", lval_d);
                    s_cent = "." + String.format("%02d", lval_c);
                    t_dollar.setText(s_dollar);
                    t_cent.setText(s_cent);

                    break;


                /*case R.id.btn_amount_cancel:
                    Intent intent1 = new Intent(this, IDLEActivity.class);
                    startActivity(intent1);
                    break;
                    */
            }
        }
	}
	
	public void showToast(String str) {
		Toast.makeText(this, str, 100).show();
	}
}
