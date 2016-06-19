package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                final Intent home_intent = new Intent(AssignSubjectActivity.this, MainActivity.class);
                new AlertDialog.Builder(AssignSubjectActivity.this)
                        .setTitle("Attention")
                        .setMessage("If you stop here, your class has no subjects!")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                textSubject.setText("");
                                startActivity(home_intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
