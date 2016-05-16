package siriuscyberneticscorporation.teachingaid47plus;

import com.orm.SugarRecord;
import siriuscyberneticscorporation.teachingaid47plus.SchoolClass;

/**
 * Created by Johannes on 29.04.2016.
 */
public class Student extends SugarRecord{
        private String name;
        private String contactPersonName;
        private String contactPersonTelNumber;
        private String contactPersonEMail;
        private String address;
        private String note;
        private SchoolClass schoolClass;

    public Student(String name, String contactPersonName, String contactPersonTelNumber,
                   String contactPersonEMail, String address, String note, SchoolClass schoolClass)
    {
        this.name = name;
        this.contactPersonName = contactPersonName;
        this.contactPersonTelNumber = contactPersonTelNumber;
        this.contactPersonEMail = contactPersonEMail;
        this.address = address;
        this.note = note;
        this.schoolClass = schoolClass;
    }


    public String getName() {
        return name;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public String getContactPersonTelNumber() {
        return contactPersonTelNumber;
    }

    public String getContactPersonEMail() {
        return contactPersonEMail;
    }

    public String getAddress() {
        return address;
    }

    public String getNote() {
        return note;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public void setContactPersonTelNumber(String contactPersonTelNumber) {
        this.contactPersonTelNumber = contactPersonTelNumber;
    }

    public void setContactPersonEMail(String contactPersonEMail) {
        this.contactPersonEMail = contactPersonEMail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Student() {
    }
}
