package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.robotium.solo.Solo;

import siriuscyberneticscorporation.teachingaid47plus.AddClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.ExistingClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;

/**
 * Created by Bettina on 04.05.2016.
 */
public class AddParticipationActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public AddParticipationActivityTest()
    {
        super(AddParticipationActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testWalkTrough() {

        View dropdown_rating = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdown_rating);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 2));
        EditText note = (EditText) mySolo.getCurrentActivity().findViewById(R.id.note_edittext);
        mySolo.enterText(note, "Sweet");
        mySolo.clickOnButton("done");
        mySolo.sleep(300);
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);
    }

    public void testDropdown()
    {
        View dropdown_rating = mySolo.getView(Spinner.class, 0);
        mySolo.clickOnView(dropdown_rating);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 0));
    }
}