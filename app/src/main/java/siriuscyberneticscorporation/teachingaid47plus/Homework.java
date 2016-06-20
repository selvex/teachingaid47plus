package siriuscyberneticscorporation.teachingaid47plus;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SeLveX on 17.05.2016.
 */
public class Homework extends SugarRecord{
    private Student student;
    private Subject subject;
    private Date date;
    private Tags tag;
    private String note;
    @Ignore
    private SimpleDateFormat dateFormat;

    public enum Tags {

        NONE (0), EXCELLENT (1), OK (2), LATE (3), INCOMPLETE (4), MISSING (5);

        private final int value;
        private Tags(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public Homework() {
    }

    public Homework(Student student, Subject subject, String date, Tags tag, String note) throws ParseException {
        this.student = student;
        this.subject = subject;
        this.tag = tag;
        this.note = note;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.date = this.dateFormat.parse(date);
    }

    public Homework(Tags tag) {
        this.tag = tag;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    public Homework(Student student, Subject subject, String date, Tags tag) throws ParseException {
        this.student = student;
        this.subject = subject;
        this.tag = tag;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.date = this.dateFormat.parse(date);
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public Student getStudent() {

        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) throws ParseException {
        this.date = dateFormat.parse(date);
    }

    public Tags getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = Tags.values()[tag];
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
