package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner classDropdown;
    private Spinner subjectDropdown;
    private Button participationButton;
    private Button homeworkButton;
    private Button testButton;
    private Button addDate;
    private TextView errorMsg;
    private ArrayList<TextView> textViewsStudents = new ArrayList<TextView>();
    private ArrayList<ArrayList<Button>> buttonsParticipationsMatrix = new ArrayList<>();
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
        if(v == addDate) {
            SimpleDateFormat dateFromUser;

            SchoolClass schoolClass = SchoolClass.find(SchoolClass.class, "name = ?", classDropdown.getSelectedItem().toString()).get(0);
            List<Subject> subjects = Subject.find(Subject.class, "name=?", subjectDropdown.getSelectedItem().toString());
            for(Subject s: subjects) {
                if(s.getSchoolClass().getName().equals(schoolClass.getName())) {
                    Log.d("No plan", "Really no plan");
                    showInputDialog(schoolClass, s);
                }
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

        int counterRows = 0;

        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        TextView date = new TextView(this);
        date.setId(counterRows + 1);
        date.setText("Date:");
        date.setTextSize(40);
        if(date.getParent()!= null) {

            ((ViewGroup) date.getParent()).removeView(date);
        }
        row.addView(date);

        List<Participation> participations = Participation.find(Participation.class,"student=?", String.valueOf(students.get(0).getId()));

        for(Participation p: participations) {
            TextView dateStudent = new TextView(this);
            Log.d("Date", "Date: " + p.getDate());

            Date dateAndTime = p.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            String onlyDate = dateFormat.format(dateAndTime);



            dateStudent.setText(onlyDate);

            row.addView(dateStudent);
        }

        addDate = new Button(this);
        addDate.setText("+");
        addDate.setTextSize(40);
        addDate.setOnClickListener(this);

        row.addView(addDate);



        studentTable.addView(row);


        for(Student s : students) {

            row = new TableRow(this);
            //tableRows.add(row);
            //TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);


            if(counterRows % 2 == 0) {
                row.setBackgroundResource(R.color.colorStudentTable);
            }
            row.setLayoutParams(lp);
            TextView tx = new TextView(this);
            textViewsStudents.add(tx);
            textViewsStudents.get(counterRows).setId(counterRows + 1);
            textViewsStudents.get(counterRows).setOnClickListener(this);
            textViewsStudents.get(counterRows).setText(s.getName());
            textViewsStudents.get(counterRows).setTextSize(40);
            if(textViewsStudents.get(counterRows).getParent()!= null) {

                ((ViewGroup) textViewsStudents.get(counterRows).getParent()).removeView(textViewsStudents.get(counterRows));
            }
            row.addView(textViewsStudents.get(counterRows));

            ArrayList<Button> buttonsParticipation = new ArrayList<Button>();


            for(Participation p: participations) {
                Button ratingStudent = new Button(this);
                

                String rating = "~";
                switch (p.getRating()) {
                    case -3:
                        rating = "- - -";
                        break;
                    case -2:
                        rating = "- -";
                        break;
                    case -1:
                        rating = "-";
                        break;
                    case 0:
                        rating = "~";
                        break;
                    case 1:
                        rating = "+";
                        break;
                    case 2:
                        rating = "+ +";
                        break;
                    case 3:
                        rating = "+ + +";
                        break;
                }
                ratingStudent.setText(rating);
                ratingStudent.setOnClickListener(this);
                buttonsParticipation.add(ratingStudent);
                row.addView(ratingStudent);
            }

            Button addDateForStudent = new Button(this);
            addDateForStudent.setText("");
            addDateForStudent.setTextSize(40);
            addDateForStudent.setOnClickListener(this);
            buttonsParticipation.add(addDateForStudent);

            row.addView(addDateForStudent);

            buttonsParticipationsMatrix.add(buttonsParticipation);

            //row.addView();
            studentTable.addView(row);
            counterRows++;
        }
    }
    protected void showInputDialog(final SchoolClass schoolClass, final Subject subject) {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.date_editText);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            String date = editText.getText().toString();

                            List<Student> students = Student.find(Student.class, "school_class = ?", String.valueOf(schoolClass.getId()));

                            for (Student s: students){
                                Participation participation = new Participation(s, subject, date, 0);
                                participation.save();
                            }

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                            dialog.cancel();
                        }
                        catch (ParseException e) {

                            TextView errorText = (TextView) promptView.findViewById(R.id.error_textView);

                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Attention")
                                    .setMessage("Please enter a valid date!")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}


