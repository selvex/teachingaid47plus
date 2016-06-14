package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class StudentInfoActivity extends AppCompatActivity implements View.OnClickListener{


    private Button buttonSave;
    private Button buttonDelete;

    private TextView plusTextView;
    private TextView minusTextView;
    private TextView testAverageTextView;

    private EditText StudentNameEditText;
    private EditText ContactPersonNameEditText;
    private EditText ContactPersonPhoneEditText;
    private EditText ContactPersonEmailEditText;
    private EditText StudentAddressEditText;
    private EditText StudentNoteEditText;

    Student studentToShow = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        buttonSave = (Button) findViewById(R.id.save_button);
        buttonDelete = (Button) findViewById(R.id.delete_button);

        StudentNameEditText = (EditText) findViewById(R.id.name_edittext);
        ContactPersonNameEditText = (EditText) findViewById(R.id.cpname_edittext);
        ContactPersonPhoneEditText = (EditText) findViewById(R.id.cpphone_edittext);
        ContactPersonEmailEditText = (EditText) findViewById(R.id.cpemail_edittext);
        StudentAddressEditText = (EditText) findViewById(R.id.adress_edittext);
        StudentNoteEditText = (EditText) findViewById(R.id.note_edittext);

        plusTextView = (TextView) findViewById(R.id.plus_textview);
        minusTextView = (TextView) findViewById(R.id.minus_textview);
        testAverageTextView = (TextView) findViewById(R.id.test_average_textview);


        buttonSave.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        long StudentID = getIntent().getLongExtra("default", 0);

        if(StudentID == 0) {
            return;
        }
        studentToShow = Student.findById(Student.class, StudentID);



        StudentNameEditText.setText(studentToShow.getName());
        ContactPersonNameEditText.setText(studentToShow.getContactPersonName());
        ContactPersonPhoneEditText.setText(studentToShow.getContactPersonTelNumber());
        ContactPersonEmailEditText.setText(studentToShow.getContactPersonEMail());
        StudentAddressEditText.setText(studentToShow.getAddress());
        StudentNoteEditText.setText(studentToShow.getNote());

        int sum_plus = 0;
        int sum_minus = 0;
        int average_points = 0;
        List<Participation> participations = Participation.find(Participation.class, "student = ?", String.valueOf(studentToShow.getId()));
        for (Participation p: participations) {
            if(p.getRating() > 0) {
                sum_plus = sum_plus + p.getRating();
            }
            else if(p.getRating() < 0){
                sum_minus = sum_minus - p.getRating();
            }
        }

        List<SchoolTest> tests = SchoolTest.find(SchoolTest.class, "student = ?", String.valueOf(studentToShow.getId()));
        for (SchoolTest s: tests) {
            average_points = average_points + s.getRating();
        }
        if(tests.size()>0) {
            average_points = (average_points / tests.size());
        }
        plusTextView.setText("Plus: " + sum_plus);
        minusTextView.setText("Minus: " + sum_minus);
        testAverageTextView.setText("Testpoints average: " + average_points);
    }

    public void onClick(View v) {

        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.save_button) {

            Intent intent = new Intent(StudentInfoActivity.this, MainActivity.class);
            if(StudentNameEditText.getText().toString().equals("")) {
                new AlertDialog.Builder(StudentInfoActivity.this)
                        .setTitle("Error - Empty name")
                        .setMessage("Please, at least fill out the field 'name'.")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else {
                Student newStudent = Student.find(Student.class, "name = ?", studentToShow.getName()).get(0);
                newStudent.setName(StudentNameEditText.getText().toString());
                newStudent.setContactPersonName(ContactPersonNameEditText.getText().toString());
                newStudent.setContactPersonTelNumber(ContactPersonPhoneEditText.getText().toString());
                newStudent.setContactPersonEMail(ContactPersonEmailEditText.getText().toString());
                newStudent.setAddress(StudentAddressEditText.getText().toString());
                newStudent.setNote(StudentNoteEditText.getText().toString());

                newStudent.save();
                startActivity(intent);
            }

        }

        if (clickedButton.getId() == R.id.delete_button) {

            Intent intent = new Intent(StudentInfoActivity.this, MainActivity.class);

            List<Participation> participations = Participation.find(Participation.class, "student = ?", String.valueOf(studentToShow.getId()));
            for (Participation p: participations) {
                p.delete();
            }
            List<SchoolTest> tests = SchoolTest.find(SchoolTest.class, "student = ?", String.valueOf(studentToShow.getId()));
            for (SchoolTest s: tests) {
                s.delete();
            }
            List<Homework> homeworks = Homework.find(Homework.class, "student = ?", String.valueOf(studentToShow.getId()));
            for (Homework h: homeworks) {
                h.delete();
            }
            Student newStudent = Student.find(Student.class, "name = ?", studentToShow.getName()).get(0);
            newStudent.delete();
            startActivity(intent);
        }
    }
}
