package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;


import com.robotium.solo.Solo;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.MainLogin;
import siriuscyberneticscorporation.teachingaid47plus.R;


/**
 * Created by SeLveX on 22.04.2016.
 */
public class MainLoginTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public MainLoginTest()
    {
        super(MainLogin.class);

    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());


    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLogin()
    {
        EditText password = (EditText) mySolo.getCurrentActivity().findViewById(R.id.password);
        mySolo.enterText(password, "1234");
        mySolo.clickOnButton("Sign in");

    }
}