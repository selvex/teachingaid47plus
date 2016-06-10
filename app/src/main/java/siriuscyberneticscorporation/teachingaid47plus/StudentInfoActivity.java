package siriuscyberneticscorporation.teachingaid47plus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StudentInfoActivity extends AppCompatActivity {



    private TextView StudentNameLabel;
    private TextView ContactPersonNameLabel;
    private TextView ContactPersonPhoneLabel;
    private TextView ContactPersonEmailLabel;
    private TextView AddressLabel;
    private TextView NoteLabel;

    private EditText StudentNameEditText;
    private EditText ContactPersonNameEditText;
    private EditText ContactPersonPhoneEditText;
    private EditText ContactPersonEmailEditText;
    private EditText StudentAddressEditText;
    private EditText StudentNoteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        System.out.println("OnCreate");

        StudentNameEditText = (EditText) findViewById(R.id.name_edittext);
        ContactPersonNameEditText = (EditText) findViewById(R.id.cpname_edittext);
        ContactPersonPhoneEditText = (EditText) findViewById(R.id.cpphone_edittext);
        ContactPersonEmailEditText = (EditText) findViewById(R.id.cpemail_edittext);
        StudentAddressEditText = (EditText) findViewById(R.id.adress_edittext);
        StudentNoteEditText = (EditText) findViewById(R.id.note_edittext);

        StudentNameLabel = (TextView) findViewById(R.id.student_name_label);
        ContactPersonNameLabel = (TextView) findViewById(R.id.contact_person_name_label);
        ContactPersonPhoneLabel = (TextView) findViewById(R.id.contact_person_phone_label);
        ContactPersonEmailLabel = (TextView) findViewById(R.id.contact_person_email_label);
        AddressLabel = (TextView) findViewById(R.id.address_lable);
        NoteLabel = (TextView) findViewById(R.id.note_label);
        System.out.println("sadfasdfasdfasdf");


        long StudentID = getIntent().getLongExtra("default", 0);

        if(StudentID == 0) {
            return;
        }
        System.out.println("Intentintent");
        Student studentToShow = Student.findById(Student.class, StudentID);



        StudentNameEditText.setText(studentToShow.getName());
        ContactPersonNameEditText.setText(studentToShow.getContactPersonName());
        ContactPersonPhoneEditText.setText(studentToShow.getContactPersonTelNumber());
        ContactPersonEmailEditText.setText(studentToShow.getContactPersonEMail());
        StudentAddressEditText.setText(studentToShow.getAddress());
        StudentNoteEditText.setText(studentToShow.getNote());
        System.out.println("ganz unten noch");


    }
}
