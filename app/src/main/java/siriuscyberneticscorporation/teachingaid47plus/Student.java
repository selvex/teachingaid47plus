package siriuscyberneticscorporation.teachingaid47plus;

/**
 * Created by Johannes on 29.04.2016.
 */
public class Student{
        private String _name;
        private String _contactPersonName;
        private String _contactPersonTelNumber;
        private String _contactPersonEMail;
        private String _address;
        private String _note;

    public Student(String name, String contactPersonName,String contactPersonTelNumber,
                       String contactPersonEMail, String address, String note){

        _name = name;
        _contactPersonName = contactPersonName;
        _contactPersonTelNumber = contactPersonTelNumber;
        _contactPersonEMail = contactPersonEMail;
        _address = address;
        _note = note;
        }

    public String get_name() {
        return _name;
    }

    public String get_contactPersonName() {
        return _contactPersonName;
    }

    public String get_contactPersonTelNumber() {
        return _contactPersonTelNumber;
    }

    public String get_contactPersonEMail() {
        return _contactPersonEMail;
    }

    public String get_address() {
        return _address;
    }

    public String get_note() {
        return _note;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_contactPersonName(String _contactPersonName) {
        this._contactPersonName = _contactPersonName;
    }

    public void set_contactPersonTelNumber(String _contactPersonTelNumber) {
        this._contactPersonTelNumber = _contactPersonTelNumber;
    }

    public void set_contactPersonEMail(String _contactPersonEMail) {
        this._contactPersonEMail = _contactPersonEMail;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public void set_note(String _note) {
        this._note = _note;
    }

    public Student(){

    }

        }
