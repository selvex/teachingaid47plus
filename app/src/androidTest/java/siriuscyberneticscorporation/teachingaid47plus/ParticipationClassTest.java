package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

/**
 * Created by Kevin on 29.04.2016.
 */
public class ParticipationClassTest extends TestCase {

    public void testClass2()
    {
        SchoolClass clas = new SchoolClass("16F", "Peter Lustig", "Lustig echt");
        Student paul = new Student("HERTA OMG", "Gjoern", "", "", "Graz", "Bad boy bad boy", clas);
        String date = "1.5.2016";
        Subject lustig_class = new Subject("Lustig Class", clas);
        clas.save();
        paul.save();
        lustig_class.save();
        Participation test = new Participation(paul, -3, date, lustig_class);
        test.save();
    }

    public void testGetter() {
        Student student = Student.find(Student.class, "name = ?", "HERTA OMG").get(0);
        Subject subject = Subject.find(Subject.class, "name = ?", "Lustig Class").get(0);

        List<Participation> from_db = Participation.find(Participation.class, "student = ? and subject = ?", student, subject);
        int counter = 0;
        for (Participation p : from_db)
        {
            if (counter > 0)
            {
                System.out.println("More than 1 participation found");
                break;
            }
            assertEquals("HERTA OMG", p.getStudent.getName());
            assertEquals("Lustig Class", p.getSubject.getName());
            counter++;
        }
    }

    public void testSetter() {

    }
}