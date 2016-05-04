package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

/**
 * Created by Johannes on 29.04.2016.
 */
public class SubjectClassTest extends TestCase {


    public void testSubjectClass(){
        Subject mathematic = new Subject();
    }

    public void testSubjectClass2(){

        SchoolClass to_enter = new SchoolClass("1Stern", "Peter Lustig", "der wird niemals alt");
        Subject mathematic = new Subject("Mathematic", to_enter);
    }

    public void testGetter(){
        SchoolClass to_enter = new SchoolClass("1Stern", "Peter Lustig", "der wird niemals alt");
        Subject mathematic = new Subject("Mathematic", to_enter);
        assertEquals(mathematic.getName(),"Mathematic");
    }

    public void testSetter(){
        Subject mathematic = new Subject();
        mathematic.setName("Math");
        assertEquals(mathematic.getName(),"Math");
    }
}