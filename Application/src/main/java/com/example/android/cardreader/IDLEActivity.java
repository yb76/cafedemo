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
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_idle_ic :
                intent = new Intent(this, CustomerActivity.class);
                cardInfo.getInstance().setNextStep(cardInfo.step_action.STEP_ISSUE.ordinal());
                startActivity(intent);
                break;

            case R.id.btn_idle_deposit :
                intent = new Intent(this, AmountActivity.class);
                cardInfo.getInstance().setNextStep(cardInfo.step_action.STEP_DEPOSIT.ordinal());
                startActivity(intent);
                break;

            case R.id.btn_idle_purchase :
                cardInfo.getInstance().setNextStep(cardInfo.step_action.STEP_PURCHASE.ordinal());
                intent = new Intent(this, AmountActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_idle_balance :
                cardInfo.getInstance().setNextStep(cardInfo.step_action.STEP_TAPCARD_READ.ordinal());
                intent = new Intent(this, TapcardActivity.class);
                startActivity(intent);
                break;

            default :
                break;
        }
    }
}
