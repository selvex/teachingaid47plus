package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;


import com.robotium.solo.Solo;

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
        mySolo.enterText(password, "1234");
        mySolo.clickOnButton("Sign in");
        mySolo.goBack();
    }

    public void testWalkTrough() {
        EditText password = (EditText) mySolo.getCurrentActivity().findViewById(R.id.password);
        mySolo.enterText(password, "1234");
        mySolo.clickOnButton("Sign in");
        mySolo.clickOnMenuItem("settings");
        mySolo.assertCurrentActivity("wrong activity", SettingsActivity.class);
        mySolo.goBack();
        mySolo.clickOnMenuItem("add class");
        mySolo.goBack();
        mySolo.clickOnMenuItem("add subject");
        mySolo.goBack();
        mySolo.goBack();


    }
}