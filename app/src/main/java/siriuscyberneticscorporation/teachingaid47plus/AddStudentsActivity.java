package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddStudentsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonDone;
    private Button buttonAdd;
    private EditText textName;
    private EditText textContactPersonName;
    private EditText textContactPersonTelNumber;
    private EditText textContactPersonEMail;
    private EditText textAddress;
    private EditText textNote;
    private Intent prevIntent;
    private TextView counterStudents;


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
        counterStudents = (TextView) findViewById(R.id.student_counter_textView);

        buttonAdd.setBackgroundResource(R.drawable.mybutton);

        buttonDone.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        prevIntent = getIntent();
    }

    public void onClick(View v) {
        int studentCounter =0 ;
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {

            Intent intent = new Intent(AddStudentsActivity.this, AssignSubjectActivity.class);

            if(textName.getText().toString().isEmpty() && textContactPersonName.getText().toString().isEmpty() &&
               textContactPersonTelNumber.getText().toString().isEmpty() &&
               textContactPersonEMail.getText().toString().isEmpty() && textAddress.getText().toString().isEmpty() &&
               textNote.getText().toString().isEmpty()) {
                intent.putExtra("default", prevIntent.getLongExtra("default", 0));
                startActivity(intent);
            }
            else {

                new AlertDialog.Builder(AddStudentsActivity.this)
                        .setTitle("Attention")
                        .setMessage("Some fields contain information. If you still need that information, " +
                                "please click on 'Add Student', befor you click on 'Done'. Continue without saving?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(AddStudentsActivity.this, AssignSubjectActivity.class);
                                textName.setText("");
                                textContactPersonName.setText("");
                                textContactPersonTelNumber.setText("");
                                textContactPersonEMail.setText("");
                                textAddress.setText("");
                                textNote.setText("");
                                intent.putExtra("default", prevIntent.getLongExtra("default", 0));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        }
        else if (clickedButton.getId() == R.id.add_student_button) {

            if(textName.getText().toString().isEmpty()) {
                new AlertDialog.Builder(AddStudentsActivity.this)
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
                SchoolClass schoolClass = SchoolClass.findById(SchoolClass.class, prevIntent.getLongExtra("default", 0));
                Student student = new Student(textName.getText().toString(),textContactPersonName.getText().toString(),
                        textContactPersonTelNumber.getText().toString(), textContactPersonEMail.getText().toString(),
                        textAddress.getText().toString(), textNote.getText().toString(), schoolClass);
                student.save();
                studentCounter++;

                textName.setText("");
                textContactPersonName.setText("");
                textContactPersonTelNumber.setText("");
                textContactPersonEMail.setText("");
                textAddress.setText("");
                textNote.setText("");

                counterStudents.setText("Already added: " + studentCounter);
            }

        }
    }
}
