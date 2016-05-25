package siriuscyberneticscorporation.teachingaid47plus;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner classDropdown;
    private Spinner subjectDropdown;
    private Button participationButton;
    private Button homeworkButton;
    private Button testButton;
    private ArrayList<TextView> textViewsStudents = new ArrayList<TextView>();
    private ArrayList<TextView> textViewsDate = new ArrayList<TextView>();
    private TableLayout studentTable;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar wat = getSupportActionBar();
        wat.show();
        classDropdown = (Spinner) findViewById(R.id.class_spinner);
        subjectDropdown = (Spinner) findViewById(R.id.subject_spinner);
        participationButton = (Button) findViewById(R.id.participation_button);
        homeworkButton = (Button) findViewById(R.id.homework_button);
        testButton = (Button) findViewById(R.id.test_button);
        studentTable = (TableLayout) findViewById(R.id.student_table);

        classDropdown.setOnItemSelectedListener(this);
        subjectDropdown.setOnItemSelectedListener(this);
        participationButton.setOnClickListener(this);
        homeworkButton.setOnClickListener(this);
        testButton.setOnClickListener(this);

        SchoolClass add = new SchoolClass("ewq", "ew", "ew");
        add.save();
        add.delete();
        Iterator<SchoolClass> classes = SchoolClass.findAll(SchoolClass.class);
        Iterator<Subject> subjects = Subject.findAll(Subject.class);
        ArrayList<String> classArray = new ArrayList<String>();
        ArrayList<String> subjectArray = new ArrayList<String>();

        classArray.add("-----");
        subjectArray.add("-----");
        while(classes.hasNext())
        {
            classArray.add(classes.next().getName());
        }
        while(subjects.hasNext())
        {
            subjectArray.add(subjects.next().getName());
        }
        fillClassDropdown(classArray);
        fillSubjectsDropdown(subjectArray);

        participationButton.setBackgroundResource(R.drawable.mybutton);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        System.out.println("Looking for: " + selectedItem);
        if (selectedItem == "-----")
            return;
        switch (parent.getId())
        {
            case R.id.class_spinner:
                List<SchoolClass> list_to_check = SchoolClass.find(SchoolClass.class, "name = ?", selectedItem);
                if(list_to_check.isEmpty()) {
                    System.out.println("list was empty");
                    return;
                }
                SchoolClass selected = list_to_check.get(0);
                ArrayList<String> subjectArray = new ArrayList<String>();

                List<Subject> subjects = Subject.find(Subject.class, "school_class = ?", String.valueOf(selected.getId()));

                for(Subject s : subjects) {
                    System.out.println("Subject found: " + s.getName());
                    subjectArray.add(s.getName());
                }
                if(subjects.isEmpty()) {
                    subjectArray.add("   ---  ");
                }
                fillSubjectsDropdown(subjectArray);
                break;
            case R.id.subject_spinner:
                System.out.println("Subject 123 ;)");
                Spinner class_spinner = (Spinner)findViewById(R.id.class_spinner);
                String selected_class = class_spinner.getSelectedItem().toString();
                if(selected_class == "-----")
                    return;
                List<SchoolClass> schoolClassestoCheck = SchoolClass.find(SchoolClass.class, "name = ?", selected_class);
                System.out.println("class : " + selected_class);

                if(schoolClassestoCheck.isEmpty()) {
                    System.out.println("list was empty");
                    return;
                }
                SchoolClass selectedSchoolClass = schoolClassestoCheck.get(0);
                List <Student> students = Student.find(Student.class, "school_class = ?", String.valueOf(selectedSchoolClass.getId()));

                if(students.isEmpty()) {
                    System.out.println("Student list empty");
                    return;
                }
                else {
                    createTableParticipation(students);
                }
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public void onClick (View v) {
        for(TextView tx : textViewsStudents) {

            if (v == tx) {
                String studentName = tx.getText().toString();
                Student student = Student.find(Student.class, "name = ?", studentName).get(0);
                Intent student_intent = new Intent(MainActivity.this, StudentInfoActivity.class);
                student_intent.putExtra("default", student.getId());
                startActivity(student_intent);
            }
        }
        switch (v.getId()) {
            case R.id.participation_button:
                participationButton.setBackgroundResource(R.drawable.mybutton);
                homeworkButton.setBackgroundResource(R.color.colorTransparent);
                testButton.setBackgroundResource(R.color.colorTransparent);

                break;
            case R.id.homework_button:
                homeworkButton.setBackgroundResource(R.drawable.mybutton);
                participationButton.setBackgroundResource(R.color.colorTransparent);
                testButton.setBackgroundResource(R.color.colorTransparent);

                break;
            case R.id.test_button:
                testButton.setBackgroundResource(R.drawable.mybutton);
                participationButton.setBackgroundResource(R.color.colorTransparent);
                homeworkButton.setBackgroundResource(R.color.colorTransparent);

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_class:
                Intent class_intent = new Intent(MainActivity.this, AddClassActivity.class);
                startActivity(class_intent);
                return true;
            case R.id.action_settings:
                Intent student_intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(student_intent);
                return true;
            case R.id.action_add_subject:
                Intent subject_intent = new Intent(MainActivity.this, AddSubjectActivity.class);
                startActivity(subject_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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

    public void createTableParticipation(List<Student> students){
        studentTable.removeAllViews();

        int counter = 0;

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        TextView date = new TextView(this);
        date.setId(counter + 1);
        date.setText("Date:");
        date.setTextSize(40);
        if(date.getParent()!= null) {

            ((ViewGroup) date.getParent()).removeView(date);
        }
        row.addView(date);

        List<Participation> participations = Participation.find(Participation.class,"student=?",students.get(0).getName());

        for(Participation p: participations) {
            TextView date_student = new TextView(this);
            date_student.setText(p.getDate());

            row.addView(date_student);
        }

        studentTable.addView(row);


        for(Student s : students) {

            row = new TableRow(this);
            //tableRows.add(row);
            //TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);




            if(counter % 2 == 1) {
                row.setBackgroundResource(R.color.colorStudentTable);
            }
            row.setLayoutParams(lp);
            TextView tx = new TextView(this);
            textViewsStudents.add(tx);
            textViewsStudents.get(counter).setId(counter + 1);
            textViewsStudents.get(counter).setOnClickListener(this);
            textViewsStudents.get(counter).setText(s.getName());
            textViewsStudents.get(counter).setTextSize(40);
            if(textViewsStudents.get(counter).getParent()!= null) {

                ((ViewGroup) textViewsStudents.get(counter).getParent()).removeView(textViewsStudents.get(counter));
            }
            row.addView(textViewsStudents.get(counter));
            //row.addView();
            studentTable.addView(row);
            counter++;
        }
    }
}


