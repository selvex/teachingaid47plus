package siriuscyberneticscorporation.teachingaid47plus.uitest;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import siriuscyberneticscorporation.teachingaid47plus.ExistingClassActivity;
import siriuscyberneticscorporation.teachingaid47plus.MainActivity;
import siriuscyberneticscorporation.teachingaid47plus.R;
import siriuscyberneticscorporation.teachingaid47plus.SchoolClass;
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


    public void testWalkTrough()
    {
        View dropdown_class = mySolo.getView(Spinner.class, 0);
        View dropdown_subject = mySolo.getView(Spinner.class, 1);
        mySolo.clickOnView(dropdown_class);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 0));
        mySolo.clickOnView(dropdown_subject);
        mySolo.scrollToTop();
        mySolo.clickOnView(mySolo.getView(TextView.class, 0));

        mySolo.clickOnButton("done");
        mySolo.goBackToActivity("ExistingClassActivity");
    }

    public void testBasicAssignment() {
        SchoolClass new_class = new SchoolClass("Best Class", "Bernd", "God damnit");
        Subject new_sub = new Subject("Colleteral");
        new_class.save();
        new_sub.save();
        System.out.println(1);
        SchoolClass class_to_link = SchoolClass.find(SchoolClass.class, "name = ?", new_class.getName()).get(0);
        Subject subject_getting_linked = Subject.find(Subject.class, "name = ?", new_sub.getName()).get(0);
        System.out.println(2);

        subject_getting_linked.setSchoolClass(class_to_link);
        subject_getting_linked.save();
        System.out.println(3);


        SchoolClass class_from_db = SchoolClass.find(SchoolClass.class, "name = ?", "Best Class").get(0);
        System.out.println(4);

        System.out.println("Class name: " + class_from_db.getName() + " and id: " + class_from_db.getId());
        Subject from_db = Subject.find(Subject.class, "school_class = ?", String.valueOf(class_from_db.getId())).get(0);
        System.out.println(5);

        assertEquals(from_db.getSchoolClass().getClassTeacher(), class_from_db.getClassTeacher());

        class_from_db.delete();
        from_db.delete();
    }

    public void testExistingCombination(){
        
    }

    public void testZDeleteWrongTestEntrys()
    {
        List<Subject> subjects = Subject.find(Subject.class, "name = ?", "Colleteral");
        for(Subject s : subjects)
        {
            s.delete();
        }
        List<SchoolClass> classes = SchoolClass.find(SchoolClass.class, "name = ?", "Best Class");
        for(SchoolClass c : classes)
        {
            c.delete();
        }
    }

}