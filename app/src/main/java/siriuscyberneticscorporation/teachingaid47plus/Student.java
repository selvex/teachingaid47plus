package siriuscyberneticscorporation.teachingaid47plus;

/**
 * Created by Johannes on 29.04.2016.
 */
public class Student{
        private String name;
        private String contactPersonName;
        private String contactPersonTelNumber;
        private String contactPersonEMail;
        private String address;
        private String note;

    public Student(String name, String contactPersonName, String contactPersonTelNumber,
                       String contactPersonEMail, String address, String note)
    {
        this.name = name;
        this.contactPersonName = contactPersonName;
        this.contactPersonTelNumber = contactPersonTelNumber;
        this.contactPersonEMail = contactPersonEMail;
        this.address = address;
        this.note = note;
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

    public Student() {
    }
}
