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
/**
 * Created by Default on 7/12/14.
 */
public class BalanceCustomerActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        TextView t_bal = (TextView)findViewById(R.id.tv_balance_num);
        t_bal.setText( String.format("%.2f", cardInfo.getInstance().getBal()/100.0 ));
        TextView t_fname = (TextView)findViewById(R.id.tv_bal_cust_fname);
        t_fname.setText( "First Name:  " + cardInfo.getInstance().getFirstname());
        TextView t_lname = (TextView)findViewById(R.id.tv_bal_cust_lname);
        t_lname.setText( "Last Name :  " + cardInfo.getInstance().getLastname());
        TextView t_email = (TextView)findViewById(R.id.tv_bal_cust_email);
        t_email.setText( "Email Addr:  " + cardInfo.getInstance().getEmail());
        TextView t_mobile = (TextView)findViewById(R.id.tv_bal_cust_mobile);
        t_mobile.setText( "Mobile   :  " + cardInfo.getInstance().getMobile());


    }

    private void initView() {
        setContentView(R.layout.activity_bal_cust);
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
            Intent intent = new Intent(BalanceCustomerActivity.this, IDLEActivity.class);
            startActivity(intent);
            BalanceCustomerActivity.this.finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Intent intent = new Intent(BalanceCustomerActivity.this, IDLEActivity.class);
            startActivity(intent);
            BalanceCustomerActivity.this.finish();
        };
    };




}
