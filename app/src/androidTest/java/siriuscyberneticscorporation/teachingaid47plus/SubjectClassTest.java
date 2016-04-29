package siriuscyberneticscorporation.teachingaid47plus;

import junit.framework.TestCase;

/**
 * Created by Johannes on 29.04.2016.
 */
public class SubjectClassTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testSubjectClass(){
        Subject mathematic = new Subject();
    }

    public void testSubjectClass2(){
        Subject mathematic = new Subject("Mathematic");
    }

    public void testGetter(){
        Subject mathematic = new Subject("Mathematic");
        mathematic.getName();
    }

    public void testSetter(){
        Subject mathematic = new Subject();
        mathematic.setName("Math");
    }
}