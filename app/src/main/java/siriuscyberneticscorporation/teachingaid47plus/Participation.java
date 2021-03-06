package siriuscyberneticscorporation.teachingaid47plus;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Johannes on 17.05.2016.
 */
public class Participation extends SugarRecord{
    private Student student;
    private int rating;
    private Date date;
    private String note;
    private Subject subject;
    @Ignore
    private SimpleDateFormat dateFormat;


    public Participation() {
    }

    public Participation(Student student, Subject subject, String date, String note, int rating) throws ParseException {
        this.student = student;
        this.rating = rating;
        this.note = note;
        this.subject = subject;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        this.date = this.dateFormat.parse(date);



    }

    public Participation(Student student, Subject subject, String date, int rating) throws ParseException {
        this.student = student;
        this.subject = subject;
        this.rating = rating;

        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.date = this.dateFormat.parse(date);
    }

    public Participation(int rating) {
        this.rating = rating;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    }

    public Participation(Student student, Subject subject, String date) throws ParseException {
        this.student = student;
        this.subject = subject;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
