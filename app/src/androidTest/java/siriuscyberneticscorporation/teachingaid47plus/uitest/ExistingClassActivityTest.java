package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.ExistingClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.Subject;

/**
 * Created by Bettina on 05.05.2016.
 */
public class ExistingClassActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public ExistingClassActivityTest()
    {
        super(ExistingClassActivity.class);
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
        mySolo.assertCurrentActivity("wrong activity", ExistingClassActivity.class);

        View view1 = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(view1);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 1));

        View view2 = mySolo.getView(Spinner.class, 1);
        mySolo.clickOnView(view2);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 0));

        mySolo.clickOnButton("Done");
    }

    public void testAssignSubject()
    {
        View dropdown_class = mySolo.getView(Spinner.class, 0);
        View dropdown_subject = mySolo.getView(Spinner.class, 1);
        mySolo.clickOnView(dropdown_class);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 3));
        mySolo.clickOnView(dropdown_subject);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 0));

        mySolo.clickOnButton("Done");
        mySolo.sleep(300);
        Subject test = Subject.find(Subject.class, "name = ?", "tttt").get(0);
        assertEquals(test.getSchoolClass().getName(), "56e");
        mySolo.goBackToActivity("ExistingClassActivity");
    }

}