package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.SettingsActivity;


/**
 * Created by Bettina on 08.06.2016.
 */
public class SettingsActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public SettingsActivityTest()
    {
        super(SettingsActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testClickButtons() {
        mySolo.clickOnButton("Edit class");
        mySolo.goBack();
        mySolo.assertCurrentActivity("wrong activity", SettingsActivity.class);
        mySolo.clickOnButton("Change Password");

    }

    public void testHomeButton() {
        mySolo.clickOnView(getActivity().findViewById(R.id.action_home));
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);
        mySolo.goBack();
    }

}