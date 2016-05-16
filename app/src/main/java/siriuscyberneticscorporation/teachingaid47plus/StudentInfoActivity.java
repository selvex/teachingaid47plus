package siriuscyberneticscorporation.teachingaid47plus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StudentInfoActivity extends AppCompatActivity {

    private TextView StudentNameTextView;
    private TextView ContactPersonNameTextView;
    private TextView ContactPersonPhoneTextView;
    private TextView ContactPersonEmailTextView;
    private TextView AddressTextView;
    private TextView NoteTextView;

    private TextView StudentNameLabel;
    private TextView ContactPersonNameLabel;
    private TextView ContactPersonPhoneLabel;
    private TextView ContactPersonEmailLabel;
    private TextView AddressLabel;
    private TextView NoteLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        StudentNameTextView = (TextView) findViewById(R.id.student_name_textview);
        ContactPersonNameTextView = (TextView) findViewById(R.id.contact_person_name_textview);
        ContactPersonPhoneTextView = (TextView) findViewById(R.id.contact_person_phone_textview);
        ContactPersonEmailTextView = (TextView) findViewById(R.id.contact_person_email_textview);
        AddressTextView = (TextView) findViewById(R.id.address_textview);
        NoteTextView = (TextView) findViewById(R.id.note_textview);

        StudentNameLabel = (TextView) findViewById(R.id.student_name_label);
        ContactPersonNameLabel = (TextView) findViewById(R.id.contact_person_name_label);
        ContactPersonPhoneLabel = (TextView) findViewById(R.id.contact_person_phone_label);
        ContactPersonEmailLabel = (TextView) findViewById(R.id.contact_person_email_label);
        AddressLabel = (TextView) findViewById(R.id.address_lable);
        NoteLabel = (TextView) findViewById(R.id.note_label);

        long StudentID = getIntent().getLongExtra("default", 0);

        if(StudentID == 0) {
            return;
        }

        Student studentToShow = Student.findById(Student.class, StudentID);

        if(studentToShow.getContactPersonName() == null || studentToShow.getContactPersonName().isEmpty()) {
            ContactPersonNameTextView.setVisibility(View.GONE);
            ContactPersonNameLabel.setVisibility(View.GONE);
        }
        if(studentToShow.getContactPersonTelNumber() == null || studentToShow.getContactPersonTelNumber().isEmpty()) {
            ContactPersonPhoneTextView.setVisibility(View.GONE);
            ContactPersonPhoneLabel.setVisibility(View.GONE);
        }
        if(studentToShow.getContactPersonEMail() == null || studentToShow.getContactPersonEMail().isEmpty()) {
            ContactPersonEmailTextView.setVisibility(View.GONE);
            ContactPersonEmailLabel.setVisibility(View.GONE);
        }
        if(studentToShow.getAddress() == null || studentToShow.getAddress().isEmpty()) {
            AddressTextView.setVisibility(View.GONE);
            AddressLabel.setVisibility(View.GONE);
        }
        if (studentToShow.getNote() == null || studentToShow.getNote().isEmpty()) {
            NoteTextView.setVisibility(View.GONE);
            NoteLabel.setVisibility(View.GONE);
        }

        StudentNameTextView.setText(studentToShow.getName());
        ContactPersonNameTextView.setText(studentToShow.getContactPersonName());
        ContactPersonPhoneTextView.setText(studentToShow.getContactPersonTelNumber());
        ContactPersonEmailTextView.setText(studentToShow.getContactPersonEMail());
        AddressTextView.setText(studentToShow.getAddress());
        NoteTextView.setText(studentToShow.getNote());

    }
}
