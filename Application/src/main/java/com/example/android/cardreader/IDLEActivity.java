package com.example.android.cardreader;

/**
 * Created by Default on 20/11/14.
 */
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class IDLEActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_idle);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_idle_ic ://发行卡、借记卡
                Intent intent = new Intent(this, CustomerActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_idle_deposit ://存款
                Intent intent1 = new Intent(this, AmountActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_idle_purchase ://购买
                Intent intent2 = new Intent(this, AmountActivity.class);
                startActivity(intent2);
                break;

            default :
                break;
        }
    }
}
