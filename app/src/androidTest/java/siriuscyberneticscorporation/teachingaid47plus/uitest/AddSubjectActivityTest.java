package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.AddClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.AddSubjectActivity;
import siriuscyberneticscorporation.teachingaid47plus.ExistingClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;

/**
 * Created by Bettina on 04.05.2016.
 */
public class AddSubjectActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public AddSubjectActivityTest()
    {
        super(AddSubjectActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testWalkTrough() {
        mySolo.clickOnButton("done");
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);
        mySolo.goBack();
        EditText subject = (EditText) mySolo.getCurrentActivity().findViewById(R.id.subject_edittext);
        mySolo.enterText(subject, "geography");
        mySolo.clickOnButton("done");
        mySolo.clickOnView(mySolo.getView(R.id.action_add_subject));
        mySolo.assertCurrentActivity("wrong activity", AddSubjectActivity.class);
    }

}