package siriuscyberneticscorporation.teachingaid47plus;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SeLveX on 17.05.2016.
 */
public class SchoolTest extends SugarRecord {
    private Date testDate;
    private Student student;
    private int rating;
    private Subject subject;
    private String note;
    @Ignore
    private SimpleDateFormat dateFormat;


    public SchoolTest() {
    }

    public SchoolTest(int rating) {
        this.rating = rating;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    public SchoolTest(String testDate, Student student, Subject subject) throws ParseException {
        this.student = student;
        this.subject = subject;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.testDate = this.dateFormat.parse(testDate);
    }

    public SchoolTest(String testDate, Student student, int rating, Subject subject, String note) throws ParseException {
        this.student = student;
        this.rating = rating;
        this.subject = subject;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.testDate = this.dateFormat.parse(testDate);
        this.note = note;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) throws ParseException {
        try {
            this.testDate = dateFormat.parse(testDate);
        } catch (ParseException e) {
            e.printStackTrace();
            this.testDate = dateFormat.parse("1.1.1111");
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getNote() {return  note;}

    public void setNote(String note) {this.note = note;}

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }
}
