package siriuscyberneticscorporation.teachingaid47plus;

import java.util.List;

/**
 * Created by Johannes on 29.04.2016.
 */
public class SchoolClass {
    private String name;
    private String classTeacher;
    private List<Student> students;
    private String note;

    /* Fehlt noch Fach*/

    public SchoolClass(String name, String classTeacher, List<Student> students, String note) {
        this.name = name;
        this.classTeacher = classTeacher;
        this.students = students;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public boolean addStudent(Student student){
        boolean sucess = true;
        if(students.contains(student)){
            sucess = false;
        } else {
            students.add(student);
        }
        return sucess;
    }

    public boolean deleteStudent(Student student){
        boolean sucess = true;
        if(students.contains(student)){
            students.remove(student);
        } else {
            sucess = false;
        }
        return sucess;
    }

    public SchoolClass() {
    }
}
