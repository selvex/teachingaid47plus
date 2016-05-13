package siriuscyberneticscorporation.teachingaid47plus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonDone;
    private Button buttonAdd;
    private EditText textName;
    private EditText textContactPersonName;
    private EditText textContactPersonTelNumber;
    private EditText textContactPersonEMail;
    private EditText textAddress;
    private EditText textNote;

    private String[] name = new String[50];
    private String[] contactPersonName = new String[50];
    private String[] contactPersonTelNumber = new String[50];
    private String[] contactPersonEMail = new String[50];
    private String[] address = new String[50];
    private String[] note = new String[50];

    int studentsIndex = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);

        buttonDone = (Button) findViewById(R.id.done_button);
        buttonAdd = (Button) findViewById(R.id.add_student_button);
        textName = (EditText) findViewById(R.id.name_edittext);
        textContactPersonName = (EditText) findViewById(R.id.contactPersonName_edittext);
        textContactPersonTelNumber = (EditText) findViewById(R.id.contactPersonTelNumber_edittext);
        textContactPersonEMail = (EditText) findViewById(R.id.contactPersonEMail_edittext);
        textAddress = (EditText) findViewById(R.id.address_edittext);
        textNote = (EditText) findViewById(R.id.note_edittext);

        buttonDone.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {
            Intent intent = new Intent(AddStudentsActivity.this, AssignSubjectActivity.class);
            studentsIndex = 0;
            name = new String[50];
            contactPersonName = new String[50];
            contactPersonTelNumber = new String[50];
            contactPersonEMail = new String[50];
            address = new String[50];
            note = new String[50];
            textName.setText("");
            textContactPersonName.setText("");
            textContactPersonTelNumber.setText("");
            textContactPersonEMail.setText("");
            textAddress.setText("");
            textNote.setText("");
            startActivity(intent);
        }
        else if (clickedButton.getId() == R.id.add_student_button) {
            name[studentsIndex] = textName.toString();
            contactPersonName[studentsIndex] = textContactPersonName.toString();
            contactPersonTelNumber[studentsIndex] = textContactPersonTelNumber.toString();
            contactPersonEMail[studentsIndex] = textContactPersonEMail.toString();
            address[studentsIndex] = textAddress.toString();
            note[studentsIndex] = textNote.toString();

            textName.setText("");
            textContactPersonName.setText("");
            textContactPersonTelNumber.setText("");
            textContactPersonEMail.setText("");
            textAddress.setText("");
            textNote.setText("");

            if (studentsIndex < 49) {
                studentsIndex = studentsIndex + 1;
            }
            else{
                textName.setText("Error!!!");
                textContactPersonName.setText("Please");
                textContactPersonTelNumber.setText("select");
                textContactPersonEMail.setText("less");
                textAddress.setText("Students");
                textNote.setText("(Maximum 50)");
                studentsIndex = 0;
            }
        }

    }
}
