package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

/**
 * Created by Johannes on 29.04.2016.
 */
public class HomeworkClassTest extends TestCase {


    public void testHomeworkClass(){
        Homework homework = new Homework();
    }

    public void testHomeworkClass2(){
    
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