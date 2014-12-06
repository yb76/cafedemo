package com.example.android.cardreader;

/**
 * Created by Default on 6/12/14.
 */
public class cardInfo {

    private static cardInfo instance = null;

    protected cardInfo() {
        // Exists only to defeat instantiation.
    }
    public static cardInfo getInstance() {
        if(instance == null) {
            instance = new cardInfo();
        }
        return instance;
    }

    public String firstname = "";
    public String lastname = "";
    public String email = "";
    public String mobile = "";
    public long deposit = 0;
    public long purchase = 0;
    public long bal = 0;
    public int step = 0;
    public int prev_step = 0;
    public int next_step = 0;
    public enum step_action {STEP_NONE, STEP_ISSUE,STEP_PURCHASE,STEP_DEPOSIT,
        STEP_TAPCARD,STEP_TAPCARD_READ,STEP_TAPCARD_WRITE_CUST,STEP_TAPCARD_WRITE_CUST_DEPOSIT,STEP_TAPCARD_WRITE_BAL,STEP_BALANCE };

    public void init_card()
    {
        firstname = "";
        lastname = "";
        email = "";
        mobile = "";
        deposit = 0;
        purchase = 0;
        bal = 0;
    }

    public void setCust( String in_fname,String in_lname,String in_email,String in_mobile)
    {
        firstname = new String(in_fname);
        lastname = new String(in_lname);
        email = new String(in_email);
        mobile = new String(in_mobile);
    }

    public void setBal( long in_bal)
    {
        bal = in_bal;
    }
    public long getBal()
    {
       return(bal);
    }
    public void setPurchase( long in_amount)
    {
        purchase = in_amount;
    }
    public long getPurchase()
    {
        return(purchase);
    }
    public void setDeposit( long in_amount)
    {
        deposit = in_amount;
    }
    public long getDeposit()
    {
        return(deposit);
    }

    public void setStep( int in_step)
    {
        step = in_step;
    }
    public int getStep()
    {
        return(step);
    }
    public void setpPrevStep( int in_step)
    {
        prev_step = in_step;
    }
    public int getPrevStep()
    {
        return(prev_step);
    }
    public void setNextStep( int in_step)
    {
        next_step = in_step;
    }
    public int getNextStep()
    {
        return(next_step);
    }
    public String getFirstname()
    {
        return(firstname);
    }
    public String getLastname()
    {
        return(lastname);
    }
    public String getEmail()
    {
        return(email);
    }
    public String getMobile()
    {
        return(mobile);
    }

}
