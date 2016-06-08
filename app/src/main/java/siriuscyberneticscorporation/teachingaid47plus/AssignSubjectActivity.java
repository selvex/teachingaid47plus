package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class AssignSubjectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    private Button buttonDone;
    private Spinner subjectsDropdown;
    private EditText textSubject;
    private Intent prevIntent;
    private TextView heading;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_subject);

        buttonDone = (Button) findViewById(R.id.done_button);
        subjectsDropdown = (Spinner) findViewById(R.id.subjects_spinner);
        textSubject = (EditText) findViewById(R.id.subjects_editText);
        heading = (TextView) findViewById(R.id.heading_lable);

        prevIntent = getIntent();

        String subjectName;

        buttonDone.setOnClickListener(this);
        subjectsDropdown.setOnItemSelectedListener(this);

        Iterator<Subject> subjects = Subject.findAll(Subject.class);
        ArrayList<String> subjectArray = new ArrayList<String>();
        subjectArray.add("-----");
        while(subjects.hasNext()) {
            subjectName = subjects.next().getName();
            if (!subjectArray.contains(subjectName)) {
                subjectArray.add(subjectName);
            }
        }
        fillSubjectsDropdown(subjectArray);
        long classID = prevIntent.getLongExtra("default",0);
        //heading.setText("Choose a subject for " + classID + " or create a new one:");
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {
            subjectsDropdown.getSelectedItem();
            if (subjectsDropdown.getSelectedItem().toString().equals("-----") && textSubject.getText().toString().equals("")) {
                new AlertDialog.Builder(AssignSubjectActivity.this)
                        .setTitle("Attention")
                        .setMessage("Please select a subject or enter a new subject")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else if ((!subjectsDropdown.getSelectedItem().toString().equals("-----")) && (!textSubject.getText().toString().equals(""))) {
                new AlertDialog.Builder(AssignSubjectActivity.this)
                        .setTitle("Attention")
                        .setMessage("Please select a subject or enter a new subject. Only use one of them!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else if ((!subjectsDropdown.getSelectedItem().toString().equals("-----")) && textSubject.getText().toString().equals("")) {
                long classID = prevIntent.getLongExtra("default",0);
                SchoolClass from_db = SchoolClass.findById(SchoolClass.class, classID);
                Subject newSubject = new Subject(subjectsDropdown.getSelectedItem().toString(),from_db);
                newSubject.save();

                Intent intent = new Intent(AssignSubjectActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                long classID = prevIntent.getLongExtra("default",0);
                SchoolClass from_db = SchoolClass.findById(SchoolClass.class, classID);
                Subject newSubject = new Subject(textSubject.getText().toString(),from_db);
                newSubject.save();

                textSubject.setText("");

                Intent intent = new Intent(AssignSubjectActivity.this, MainActivity.class);
                startActivity(intent);
            }

        }
    }

    public void fillSubjectsDropdown(ArrayList<String> subjectArray) {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjectArray);
        subjectsDropdown.setAdapter(adapter1);
    }
}
