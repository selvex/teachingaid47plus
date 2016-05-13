package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.AddClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.ExistingClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainLoginActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.SettingsActivity;

/**
 * Created by Bettina on 04.05.2016.
 */
public class AddClassActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public AddClassActivityTest()
    {
        super(AddClassActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testWalkTrough() {
        mySolo.clickOnButton("new class");
        mySolo.goBack();
        mySolo.clickOnMenuItem("assign subject to class");
        mySolo.assertCurrentActivity("wrong activity", ExistingClassActivity.class);
        mySolo.goBack();
        mySolo.assertCurrentActivity("wrong activity", AddClassActivity.class);
    }
}