package siriuscyberneticscorporation.teachingaid47plus;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johannes on 29.04.2016.
 */
public class SchoolClass extends SugarRecord{
    private String name;
    private String classTeacher;
    private String note;

    /* Fehlt noch Fach*/

    public SchoolClass(String name, String classTeacher, String note) {
        this.name = name;
        this.classTeacher = classTeacher;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SchoolClass() {
    }
}
