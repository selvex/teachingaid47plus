package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;


import com.robotium.solo.Solo;

import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.SettingsActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainLoginActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;


/**
 * Created by SeLveX on 22.04.2016.
 */
public class MainLoginActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public MainLoginActivityTest()
    {
        super(MainLoginActivity.class);

    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());


    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLogin() {
        EditText password = (EditText) mySolo.getCurrentActivity().findViewById(R.id.password);
        mySolo.enterText(password, "");
        mySolo.clickOnButton("Sign in");
        mySolo.sleep(500);
        mySolo.goBack();
        mySolo.sleep(500);

    }

    public void testWalkTrough() {
        EditText password = (EditText) mySolo.getCurrentActivity().findViewById(R.id.password);
        mySolo.sleep(200);
        mySolo.enterText(password, "");
        mySolo.clickOnButton("Sign in");
        mySolo.sleep(200);
        mySolo.clickOnMenuItem("settings");
        mySolo.assertCurrentActivity("wrong activity", SettingsActivity.class);
        mySolo.sleep(200);
        mySolo.goBack();
        mySolo.sleep(200);
        mySolo.clickOnMenuItem("add class");
        mySolo.sleep(200);
        mySolo.goBack();
        mySolo.sleep(200);
        mySolo.clickOnMenuItem("add subject");
        mySolo.sleep(200);
        mySolo.goBack();
        mySolo.sleep(200);
        mySolo.goBack();
        mySolo.sleep(200);


    }
    public void testZChangePassword()
    {
        EditText password = (EditText) mySolo.getCurrentActivity().findViewById(R.id.password);
        mySolo.enterText(password, "");
        mySolo.clickOnButton("Sign in");
        mySolo.sleep(200);
        mySolo.clickOnMenuItem("settings");
        mySolo.assertCurrentActivity("wrong activity", SettingsActivity.class);
        mySolo.sleep(200);
        mySolo.clickOnButton("Change Password");
        mySolo.sleep(200);
        EditText new_pass = (EditText) mySolo.getView(R.id.new_password);
        EditText new_pass_rep = (EditText) mySolo.getView(R.id.new_password_repeat);
        mySolo.enterText(new_pass, "herrgott");
        mySolo.sleep(1000);
        mySolo.enterText(new_pass_rep, "herrgott");
        mySolo.sleep(1000);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(200);
        mySolo.goBack();
        mySolo.goBack();
        mySolo.clearEditText(password);
        mySolo.enterText(password, "herrgott");
        mySolo.sleep(200);
        mySolo.clickOnButton("Sign in");
        mySolo.sleep(300);
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);
    }
}