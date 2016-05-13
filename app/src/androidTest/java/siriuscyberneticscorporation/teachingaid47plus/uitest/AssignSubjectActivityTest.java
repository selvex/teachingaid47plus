package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.AssignSubjectActivity;
import siriuscyberneticscorporation.teachingaid47plus.ExistingClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;

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

        mySolo.clickOnButton("Done");
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);
        mySolo.goBack();
        mySolo.assertCurrentActivity("wrong activity", AssignSubjectActivity.class);

        View view1 = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(view1);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 1));

        EditText subject = (EditText) mySolo.getCurrentActivity().findViewById(R.id.subjects_editText);
        mySolo.enterText(subject, "Mathematik");
        assertEquals("Mathematik", subject.getText().toString());

        mySolo.clickOnButton("Done");
    }
}
