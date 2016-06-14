package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.util.List;

import siriuscyberneticscorporation.teachingaid47plus.AddStudentsActivity;
import siriuscyberneticscorporation.teachingaid47plus.AddSubjectActivity;
import siriuscyberneticscorporation.teachingaid47plus.AssignSubjectActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.NewClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.SchoolClass;
import siriuscyberneticscorporation.teachingaid47plus.Student;

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

        EditText name = (EditText) mySolo.getCurrentActivity().findViewById(R.id.name_edittext);
        EditText contactPersonName = (EditText) mySolo.getCurrentActivity().findViewById(R.id.contactPersonName_edittext);
        EditText contactPersonTelNumber = (EditText) mySolo.getCurrentActivity().findViewById(R.id.contactPersonTelNumber_edittext);
        EditText contactPersonEMail = (EditText) mySolo.getCurrentActivity().findViewById(R.id.contactPersonEMail_edittext);
        EditText address = (EditText) mySolo.getCurrentActivity().findViewById(R.id.address_edittext);
        EditText note = (EditText) mySolo.getCurrentActivity().findViewById(R.id.note_edittext);

        mySolo.clickOnButton("done");
        mySolo.assertCurrentActivity("wrong activity", AssignSubjectActivity.class);
        mySolo.goBack();
        mySolo.enterText(name, "Der Schüler Gerber");
        mySolo.enterText(contactPersonName, "Kupfer");
        mySolo.enterText(contactPersonTelNumber, "144");
        mySolo.enterText(contactPersonEMail, "kupfer@gmx.at");
        mySolo.enterText(address, "Irgendwo");
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

    public void testXInput(){
        mySolo.sleep(200);
        List<Student> from_db = Student.find(Student.class, "name = ?", "Der Schüler Gerber");
        assertEquals(from_db.get(0).getContactPersonName(), "Kupfer");
    }

    public void testErrorMessage() {
        EditText name = (EditText) mySolo.getCurrentActivity().findViewById(R.id.name_edittext);
        mySolo.enterText(name, "Herbert");
        mySolo.sleep(200);
        mySolo.clickOnButton("done");
        mySolo.sleep(200);
        mySolo.clickOnView(mySolo.getView(android.R.id.button2));
        mySolo.assertCurrentActivity("wrong activity", AddStudentsActivity.class);
    }

    public void testZKeepDbClean()
    {
        getActivity().getBaseContext().deleteDatabase("sugar_db_55.db");
    }

    public void testHomeButton() {
        mySolo.clickOnView(getActivity().findViewById(R.id.action_home));
        mySolo.sleep(300);
        mySolo.clickOnView(mySolo.getView(android.R.id.button1));
        mySolo.sleep(300);
        mySolo.assertCurrentActivity("wrong activity", MainActivity.class);
        mySolo.goBack();
    }

}