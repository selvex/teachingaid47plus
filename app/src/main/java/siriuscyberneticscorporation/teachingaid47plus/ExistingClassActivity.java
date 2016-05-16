package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExistingClassActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button buttonDone;
    private Spinner classDropdown;
    private Spinner subjectDropdown;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_class);

        buttonDone = (Button) findViewById(R.id.done_button);
        classDropdown = (Spinner) findViewById(R.id.class_spinner);
        subjectDropdown = (Spinner) findViewById(R.id.subject_spinner);

        buttonDone.setOnClickListener(this);
        String subjectName;

        classDropdown.setOnItemSelectedListener(this);
        subjectDropdown.setOnItemSelectedListener(this);
        Iterator<SchoolClass> classes = SchoolClass.findAll(SchoolClass.class);
        Iterator<Subject> subjects = Subject.findAll(Subject.class);
        ArrayList<String> classArray = new ArrayList<String>();
        ArrayList<String> subjectArray = new ArrayList<String>();
        classArray.add("-----");
        subjectArray.add("-----");
        while(classes.hasNext()) {
            classArray.add(classes.next().getName());
        }
        while(subjects.hasNext()) {
            subjectName = subjects.next().getName();
            if (!subjectArray.contains(subjectName)) {
                subjectArray.add(subjectName);
            }
        }
        fillClassDropdown(classArray);
        fillSubjectsDropdown(subjectArray);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
    }

    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {
            String s_class = classDropdown.getSelectedItem().toString();
            String s_subject = subjectDropdown.getSelectedItem().toString();

            if (s_class.equals("-----") || s_subject.equals("-----")){
                new AlertDialog.Builder(ExistingClassActivity.this)
                        .setTitle("Attention")
                        .setMessage("Please select a valid class and subject")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else {
                SchoolClass class_to_link = SchoolClass.find(SchoolClass.class, "name = ?", s_class).get(0);
                List<Subject> subject_getting_linked = Subject.find(Subject.class, "name = ?", s_subject);

                boolean doesNotExist = true;

                for (Subject s: subject_getting_linked) {

                    if (s.getSchoolClass() != null && s.getSchoolClass().getName().equals(class_to_link.getName())) {
                        doesNotExist = false;
                    }
                }
                if (doesNotExist) {
                    Subject newSubject = new Subject(s_subject,class_to_link);
                    newSubject.save();

                    Intent intent = new Intent(ExistingClassActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    new AlertDialog.Builder(ExistingClassActivity.this)
                            .setTitle("Attention")
                            .setMessage("This combination of class and subject already exists.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        }
    }

    public void fillClassDropdown(ArrayList<String> classes) {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classes);
        classDropdown.setAdapter(adapter1);
    }

    public void fillSubjectsDropdown(ArrayList<String> subjects) {
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        subjectDropdown.setAdapter(adapter2);
    }
}
