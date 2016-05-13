package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import siriuscyberneticscorporation.teachingaid47plus.AddStudentsActivity;
import siriuscyberneticscorporation.teachingaid47plus.AssignSubjectActivity;
import siriuscyberneticscorporation.teachingaid47plus.NewClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;

/**
 * Created by Bettina on 05.05.2016.
 */
public class AddStudentsActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo mySolo;

    public AddStudentsActivityTest()
    {
        super(AddStudentsActivity.class);
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
        mySolo.assertCurrentActivity("wrong activity", AssignSubjectActivity.class);
        mySolo.goBack();

        EditText name = (EditText) mySolo.getCurrentActivity().findViewById(R.id.name_edittext);
        mySolo.enterText(name, "Der Schüler Gerber");

        EditText contactPersonName = (EditText) mySolo.getCurrentActivity().findViewById(R.id.contactPersonName_edittext);
        mySolo.enterText(contactPersonName, "Kupfer");

        EditText contactPersonTelNumber = (EditText) mySolo.getCurrentActivity().findViewById(R.id.contactPersonTelNumber_edittext);
        mySolo.enterText(contactPersonTelNumber, "144");

        EditText contactPersonEMail = (EditText) mySolo.getCurrentActivity().findViewById(R.id.contactPersonEMail_edittext);
        mySolo.enterText(contactPersonEMail, "kupfer@gmx.at");

        EditText address = (EditText) mySolo.getCurrentActivity().findViewById(R.id.address_edittext);
        mySolo.enterText(address, "Irgendwo");

        EditText note = (EditText) mySolo.getCurrentActivity().findViewById(R.id.note_edittext);
        mySolo.enterText(note, "Kanonenhütte?");

        assertEquals("Der Schüler Gerber", name.getText().toString());
        assertEquals("Kupfer", contactPersonName.getText().toString());
        assertEquals("144", contactPersonTelNumber.getText().toString());
        assertEquals("kupfer@gmx.at", contactPersonEMail.getText().toString());
        assertEquals("Irgendwo", address.getText().toString());
        assertEquals("Kanonenhütte?", note.getText().toString());

        mySolo.clickOnButton("Add Student");
        mySolo.sleep(200);
        assertEquals("",name.getText().toString());
    }

    public void testTooManyStudents(){
        for(int i = 0; i < 50; i++){
            mySolo.clickOnButton("Add Student");
        }
    }

}