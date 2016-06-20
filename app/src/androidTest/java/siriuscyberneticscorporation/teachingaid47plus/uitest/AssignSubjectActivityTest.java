package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.util.List;

import siriuscyberneticscorporation.teachingaid47plus.AssignSubjectActivity;
import siriuscyberneticscorporation.teachingaid47plus.ExistingClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.Subject;

/**
 * Created by Bettina on 05.05.2016.
 */
public class AssignSubjectActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public AssignSubjectActivityTest()
    {
        super(AssignSubjectActivity.class);
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

        EditText subject = (EditText) mySolo.getCurrentActivity().findViewById(R.id.subjects_editText);
        mySolo.enterText(subject, "Mathematik");
        assertEquals("Mathematik", subject.getText().toString());

    }

    public void testHomeButton() {
        mySolo.clickOnView(getActivity().findViewById(R.id.action_home));
        mySolo.sleep(300);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(300);
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);
        mySolo.goBack();
        mySolo.assertCurrentActivity("wrong activity", AssignSubjectActivity.class);
    }

}
