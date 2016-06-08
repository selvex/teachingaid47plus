package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private int buttonSelected;
    private Spinner classDropdown;
    private Spinner subjectDropdown;
    private Spinner ratingDropdown;
    private Button participationButton;
    private Button homeworkButton;
    private Button testButton;
    private Button addDate;
    private TextView errorMsg;
    private ArrayList<TextView> textViewsStudents = new ArrayList<TextView>();
    private ArrayList<ArrayList<Button>> buttonsParticipationsMatrix = new ArrayList<>();
    private ArrayList<ArrayList<Button>> buttonsTestsMatrix = new ArrayList<>();
    private ArrayList<ArrayList<Button>> buttonsHomeworkMatrix = new ArrayList<>();
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
        buttonSelected = 0;


        classDropdown.setOnItemSelectedListener(this);
        subjectDropdown.setOnItemSelectedListener(this);
        participationButton.setOnClickListener(this);
        homeworkButton.setOnClickListener(this);
        testButton.setOnClickListener(this);

        //Create Database
        SchoolClass add = new SchoolClass("ewq", "ew", "ew");
        add.save();
        add.delete();

        Iterator<SchoolClass> classes = SchoolClass.findAll(SchoolClass.class);
        ArrayList<String> classArray = new ArrayList<String>();
        classArray.add("-----");
        while(classes.hasNext())
        {
            classArray.add(classes.next().getName());
        }


        Iterator<Subject> subjects = Subject.findAll(Subject.class);
        ArrayList<String> subjectArray = new ArrayList<String>();
        subjectArray.add("-----");

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

        if (selectedItem.equals("-----"))
            return;

        switch (parent.getId())
        {
            case R.id.class_spinner:
                List<SchoolClass> list_to_check = SchoolClass.find(SchoolClass.class, "name = ?", selectedItem);
                if(list_to_check.isEmpty()) {
                    return;
                }
                SchoolClass selected = list_to_check.get(0);
                ArrayList<String> subjectArray = new ArrayList<String>();
                List<Subject> subjects = Subject.find(Subject.class, "school_class = ?", String.valueOf(selected.getId()));

                for(Subject s : subjects) {
                    subjectArray.add(s.getName());
                }
                if(subjects.isEmpty()) {
                    subjectArray.add("-----");
                }
                fillSubjectsDropdown(subjectArray);
                break;
            case R.id.subject_spinner:

                List <Student> students = getStudents();
                if (students == null) {
                    return;
                }

                if(students.isEmpty()) {
                    return;
                }
                else {
                    if (buttonSelected == 0) {
                        createTableParticipation(students);
                    }
                    else if (buttonSelected == 1) {
                        createTableHomework(students);
                    }
                    else if (buttonSelected == 2) {
                        createTableTests(students);
                    }
                }
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    @Override
    public void onClick (View v) {
        List<Student> students = getStudents();
        if(v == addDate) {
            SimpleDateFormat dateFromUser;

            SchoolClass schoolClass = SchoolClass.find(SchoolClass.class, "name = ?", classDropdown.getSelectedItem().toString()).get(0);
            List<Subject> subjects = Subject.find(Subject.class, "name=?", subjectDropdown.getSelectedItem().toString());
            System.out.println("Number of subjects: " + String.valueOf(subjects.size()));
            for(Subject s: subjects) {
                if(s.getSchoolClass().getName().equals(schoolClass.getName())) {
                    showDateInputDialog(schoolClass, s);
                }
            }
        }
        else {

            switch (v.getId()) {
                case R.id.participation_button:
                    participationButton.setBackgroundResource(R.drawable.mybutton);
                    homeworkButton.setBackgroundResource(R.color.colorTransparent);
                    testButton.setBackgroundResource(R.color.colorTransparent);
                    buttonSelected = 0;
                    if (students != null) {
                        createTableParticipation(students);
                    }

                    break;
                case R.id.homework_button:
                    homeworkButton.setBackgroundResource(R.drawable.mybutton);
                    participationButton.setBackgroundResource(R.color.colorTransparent);
                    testButton.setBackgroundResource(R.color.colorTransparent);
                    buttonSelected = 1;
                    if(students != null) {
                        createTableHomework(students);
                    }

                    break;
                case R.id.test_button:
                    testButton.setBackgroundResource(R.drawable.mybutton);
                    participationButton.setBackgroundResource(R.color.colorTransparent);
                    homeworkButton.setBackgroundResource(R.color.colorTransparent);
                    buttonSelected = 2;
                    if (students != null) {
                        createTableTests(students);
                    }

                    break;
                default:
                    checkifStudentsClicked(v);
                    checkifPartisipationsClicked(v);
                    checkifTestsClicked(v);
                    checkifHomeworkClicked(v);
                    break;
            }
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

    public void fillRatingsDropdown(ArrayList<String> ratings) {
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ratings);
        ratingDropdown.setAdapter(adapter3);
    }

    public void createTableParticipation(List<Student> students){
        studentTable.removeAllViews();
        buttonsParticipationsMatrix = new ArrayList<>();
        int counterRows = 0;
        int counterStudents = 0;

        TableRow row = new TableRow(this);
        row.setPadding(15,0,0,0);
        Spinner subject_spinner = (Spinner) findViewById(R.id.subject_spinner);
        String selected_subject = subject_spinner.getSelectedItem().toString();
        Spinner class_spinner = (Spinner) findViewById(R.id.class_spinner);
        String selected_class = class_spinner.getSelectedItem().toString();
        SchoolClass class_from_db = SchoolClass.find(SchoolClass.class, "name = ?", selected_class).get(0);
        Subject subject_from_db = Subject.find(Subject.class, "name = ? and school_class = ?", selected_subject, String.valueOf(class_from_db.getId())).get(0);
        List<Participation> participations = Participation.find(Participation.class,
                "student=? and subject = ?", String.valueOf(students.get(0).getId()),
                String.valueOf(subject_from_db.getId()));
        fillFirstRowParticipations(participations, row);

        for(Student s : students) {

            row = new TableRow(this);
            row.setPadding(15,0,0,0);


            participations = Participation.find(Participation.class,"student = ? and subject = ?",
                    String.valueOf(s.getId()), String.valueOf(subject_from_db.getId()));
            counterStudents++;

            createStudentsColumn(counterRows, row, s);

            createParticipationEntries(participations, row);

            studentTable.addView(row);
            counterRows++;
        }
    }

    public void createTableTests(List<Student> students){
        studentTable.removeAllViews();
        buttonsTestsMatrix = new ArrayList<>();
        int counterRows = 0;
        int counterStudents = 0;

        TableRow row = new TableRow(this);
        row.setPadding(15,0,0,0);

        List<SchoolTest> tests = SchoolTest.find(SchoolTest.class, "student=?", String.valueOf(students.get(0).getId()));

        fillFirstRowTests(tests, row);

        for(Student s : students) {

            row = new TableRow(this);
            row.setPadding(15,0,0,0);


            tests = SchoolTest.find(SchoolTest.class,"student=?", String.valueOf(students.get(counterStudents).getId()));
            counterStudents++;

            createStudentsColumn(counterRows, row, s);

            createTestEntries(tests, row);

            studentTable.addView(row);
            counterRows++;
        }
    }

    public void createTableHomework(List<Student> students){
        studentTable.removeAllViews();
        buttonsHomeworkMatrix = new ArrayList<>();
        int counterRows = 0;
        int counterStudents = 0;

        TableRow row = new TableRow(this);
        row.setPadding(15,0,0,0);

        List<Homework> homework = Homework.find(Homework.class, "student=?", String.valueOf(students.get(0).getId()));

        fillFirstRowHomework(homework, row);

        for(Student s : students) {

            row = new TableRow(this);
            row.setPadding(15,0,0,0);


            homework = Homework.find(Homework.class,"student=?", String.valueOf(students.get(counterStudents).getId()));
            counterStudents++;

            createStudentsColumn(counterRows, row, s);

            createHomeworkEntries(homework, row);

            studentTable.addView(row);
            counterRows++;
        }
    }

    protected void showDateInputDialog(final SchoolClass schoolClass, final Subject subject) {

        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.date_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.date_editText);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            String date = editText.getText().toString();

                            List<Student> students = Student.find(Student.class, "school_class = ?", String.valueOf(schoolClass.getId()));

                            if (buttonSelected == 0) {

                                for (Student s : students) {
                                    Participation participation = new Participation(s, subject, date, 0);
                                    participation.save();
                                }
                                createTableParticipation(students);
                            }
                            else if (buttonSelected == 1) {

                                for (Student s : students) {
                                    Homework homework = new Homework(s, subject, date, Homework.Tags.NONE, "");
                                    homework.save();
                                }
                                createTableHomework(students);

                            }
                            else if (buttonSelected == 2) {

                                for (Student s : students) {
                                    SchoolTest test = new SchoolTest(date, s, 0, subject, "");
                                    test.save();
                                }
                                createTableTests(students);
                            }


                            dialog.cancel();
                        } catch (ParseException e) {

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

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
    protected void showParticipationInputDialog(final Participation participation, final List<Student> students) {

        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.participation_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final TextView date = (TextView) promptView.findViewById(R.id.participation_date_label);
        final EditText comment = (EditText) promptView.findViewById(R.id.participation_comment_edit);
        comment.setText(participation.getNote());


        Date dateAndTime = participation.getDate();
        SimpleDateFormat dateParticipation = new SimpleDateFormat("dd.MM.yyyy");
        String onlyDate = dateParticipation.format(dateAndTime);
        date.setText(onlyDate);

        ratingDropdown = (Spinner) promptView.findViewById(R.id.participation_spinner);

        ArrayList<String> ratings = new ArrayList<String>();
        ratings.add("- - -");
        ratings.add("- -");
        ratings.add("-");
        ratings.add("~");
        ratings.add("+");
        ratings.add("+ +");
        ratings.add("+ + +");


        fillRatingsDropdown(ratings);

        ratingDropdown.setSelection(participation.getRating() + 3);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int rating = 0;
                        switch (ratingDropdown.getSelectedItem().toString()){
                            case "- - -":
                                rating = -3;
                                break;
                            case "- -":
                                rating = -2;
                                break;
                            case "-":
                                rating = -1;
                                break;
                            case "~":
                                rating = 0;
                                break;
                            case "+":
                                rating = 1;
                                break;
                            case "+ +":
                                rating = 2;
                                break;
                            case "+ + +":
                                rating = 3;
                                break;
                        }
                        participation.setRating(rating);
                        participation.setNote(comment.getText().toString());
                        participation.save();

                        createTableParticipation(students);

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void showHomeworkInputDialog(final Homework homework, final List<Student> students) {

        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.homework_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final TextView date = (TextView) promptView.findViewById(R.id.homework_date_label);
        final EditText comment = (EditText) promptView.findViewById(R.id.homework_comment_edit);
        comment.setText(homework.getNote());


        Date dateAndTime = homework.getDate();
        SimpleDateFormat dateHomework = new SimpleDateFormat("dd.MM.yyyy");
        String onlyDate = dateHomework.format(dateAndTime);
        date.setText(onlyDate);

        ratingDropdown = (Spinner) promptView.findViewById(R.id.homework_spinner);

        ArrayList<String> ratings = new ArrayList<String>();
        ratings.add("none");
        ratings.add("excellent");
        ratings.add("ok");
        ratings.add("late");
        ratings.add("incomplete");
        ratings.add("missing");


        fillRatingsDropdown(ratings);

        ratingDropdown.setSelection(homework.getTag().getValue());

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        homework.setTag(ratingDropdown.getSelectedItemPosition());
                        homework.setNote(comment.getText().toString());
                        homework.save();

                        createTableHomework(students);

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void showTestInputDialog(final SchoolTest test, final List<Student> students) {

        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.test_input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final TextView date = (TextView) promptView.findViewById(R.id.test_date_label);
        final EditText comment = (EditText) promptView.findViewById(R.id.test_comment_edit);
        final EditText rating = (EditText) promptView.findViewById(R.id.test_rating_edit);
        comment.setText(test.getNote());
        rating.setText(String.valueOf(test.getRating()));


        Date dateAndTime = test.getTestDate();
        SimpleDateFormat dateParticipation = new SimpleDateFormat("dd.MM.yyyy");
        String onlyDate = dateParticipation.format(dateAndTime);
        date.setText(onlyDate);


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        try {
                            test.setRating(Integer.valueOf(rating.getText().toString()));
                            test.setNote(comment.getText().toString());
                            test.save();

                            createTableTests(students);
                        }
                        catch (NumberFormatException e) {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Attention")
                                    .setMessage("Please enter a valid rating!")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }


                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected List<Student> getStudents () {
        Spinner class_spinner = (Spinner)findViewById(R.id.class_spinner);
        String selected_class = class_spinner.getSelectedItem().toString();

        if(selected_class.equals("-----"))
            return null;
        List<SchoolClass> schoolClassestoCheck = SchoolClass.find(SchoolClass.class, "name = ?", selected_class);

        if(schoolClassestoCheck.isEmpty()) {
            return null;
        }
        SchoolClass selectedSchoolClass = schoolClassestoCheck.get(0);
        return Student.find(Student.class, "school_class = ?", String.valueOf(selectedSchoolClass.getId()));
    }

    protected void fillFirstRowParticipations(List<Participation> participations, TableRow row) {

        TextView date = new TextView(this);
        date.setText("Date:");
        date.setTextSize(40);
        date.setPadding(0, 30, 0, 0);

        if(date.getParent()!= null) {

            ((ViewGroup) date.getParent()).removeView(date);
        }
        row.addView(date);


        for(Participation p: participations) {
            TextView dateStudent = new TextView(this);
            Date dateAndTime = p.getDate();
            SimpleDateFormat dayAndMonth = new SimpleDateFormat("dd.MM.");
            SimpleDateFormat year = new SimpleDateFormat("yyyy");
            String onlyDate = dayAndMonth.format(dateAndTime) + "\n" + year.format(dateAndTime);
            dateStudent.setTextSize(18);
            dateStudent.setText(onlyDate);
            dateStudent.setPadding(1,20,1,80);
            dateStudent.setGravity(Gravity.CENTER);


            row.addView(dateStudent);
        }

        addDate = new Button(this);
        addDate.setText("+");
        addDate.setTextSize(40);
        addDate.setOnClickListener(this);

        row.addView(addDate);
        studentTable.addView(row);

    }

    protected void createStudentsColumn(int counterRows, TableRow row, Student s) {

        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
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
    }

    protected void  createParticipationEntries(List<Participation> participations, TableRow row) {

        ArrayList<Button> buttonsParticipation = new ArrayList<Button>();

        for(Participation p: participations) {
            Button ratingStudent = new Button(this);


            String rating = "~";
            switch (p.getRating()) {
                case -3:
                    rating = "- - -";
                    ratingStudent.setTextColor(getResources().getColor(R.color.colorNegative));
                    break;
                case -2:
                    rating = "- -";
                    ratingStudent.setTextColor(getResources().getColor(R.color.colorNegative));
                    break;
                case -1:
                    rating = "-";
                    ratingStudent.setTextColor(getResources().getColor(R.color.colorNegative));
                    break;
                case 0:
                    rating = "~";
                    ratingStudent.setTextColor(getResources().getColor(R.color.colorBlack));
                    break;
                case 1:
                    rating = "+";
                    ratingStudent.setTextColor(getResources().getColor(R.color.colorPositive));
                    break;
                case 2:
                    rating = "+ +";
                    ratingStudent.setTextColor(getResources().getColor(R.color.colorPositive));
                    break;
                case 3:
                    rating = "+ + +";
                    ratingStudent.setTextColor(getResources().getColor(R.color.colorPositive));
                    break;
            }
            ratingStudent.setText(rating);
            ratingStudent.setTextSize(40);
            ratingStudent.setOnClickListener(this);
            buttonsParticipation.add(ratingStudent);
            row.addView(ratingStudent);
        }

        Button addDateForStudent = new Button(this);
        addDateForStudent.setText("");
        addDateForStudent.setTextSize(40);

        row.addView(addDateForStudent);

        buttonsParticipationsMatrix.add(buttonsParticipation);
    }

    protected void checkifStudentsClicked(View v) {
        for(TextView tx : textViewsStudents) {

            if (v == tx) {
                String studentName = tx.getText().toString();
                Student student = Student.find(Student.class, "name = ?", studentName).get(0);
                Intent student_intent = new Intent(MainActivity.this, StudentInfoActivity.class);
                student_intent.putExtra("default", student.getId());
                startActivity(student_intent);
            }
        }
    }

    protected void checkifPartisipationsClicked(View v) {
        int counter_rows = 0;
        int counter_columns = 0;
        for(ArrayList<Button> arrayList : buttonsParticipationsMatrix) {
            counter_columns = 0;
            for (Button b : arrayList) {

                Spinner class_spinner = (Spinner)findViewById(R.id.class_spinner);
                String selected_class = class_spinner.getSelectedItem().toString();
                List<SchoolClass> schoolClassestoCheck = SchoolClass.find(SchoolClass.class, "name = ?", selected_class);
                SchoolClass selectedSchoolClass = schoolClassestoCheck.get(0);
                List<Student> students = Student.find(Student.class, "school_class = ?", String.valueOf(selectedSchoolClass.getId()));
                List<Participation> participations = Participation.find(Participation.class, "student = ?", String.valueOf(students.get(counter_rows).getId()));



                if (v == b) {
                    showParticipationInputDialog(participations.get(counter_columns), students);
                }
                counter_columns++;
            }
            counter_rows++;
        }
    }

    protected void fillFirstRowTests(List<SchoolTest> tests, TableRow row) {

        TextView date = new TextView(this);
        date.setText("Date:");
        date.setTextSize(40);

        if(date.getParent()!= null) {

            ((ViewGroup) date.getParent()).removeView(date);
        }
        row.addView(date);



        for(SchoolTest t: tests) {
            TextView dateStudent = new TextView(this);
            Date dateAndTime = t.getTestDate();
            SimpleDateFormat dayAndMonth = new SimpleDateFormat("dd.MM.");
            SimpleDateFormat year = new SimpleDateFormat("yyyy");
            String onlyDate = dayAndMonth.format(dateAndTime) + "\n" + year.format(dateAndTime);
            dateStudent.setTextSize(18);
            dateStudent.setPadding(1,20,1,50);
            dateStudent.setGravity(Gravity.CENTER);

            dateStudent.setText(onlyDate);


            row.addView(dateStudent);
        }

        addDate = new Button(this);
        addDate.setText("+");
        addDate.setTextSize(40);
        addDate.setOnClickListener(this);

        row.addView(addDate);
        studentTable.addView(row);

    }

    protected void  createTestEntries(List<SchoolTest> tests, TableRow row) {

        ArrayList<Button> buttonsTests = new ArrayList<Button>();

        for(SchoolTest t: tests) {
            Button ratingStudent = new Button(this);

            ratingStudent.setText(String.valueOf(t.getRating()));
            ratingStudent.setTextSize(40);
            ratingStudent.setOnClickListener(this);
            buttonsTests.add(ratingStudent);
            row.addView(ratingStudent);
        }

        Button addDateForStudent = new Button(this);
        addDateForStudent.setText("");
        addDateForStudent.setTextSize(40);

        row.addView(addDateForStudent);

        buttonsTestsMatrix.add(buttonsTests);
    }

    protected void checkifTestsClicked(View v) {
        int counter_rows = 0;
        int counter_columns = 0;
        for(ArrayList<Button> arrayList : buttonsTestsMatrix) {
            counter_columns = 0;
            for (Button b : arrayList) {

                Spinner class_spinner = (Spinner)findViewById(R.id.class_spinner);
                String selected_class = class_spinner.getSelectedItem().toString();
                List<SchoolClass> schoolClassestoCheck = SchoolClass.find(SchoolClass.class, "name = ?", selected_class);
                SchoolClass selectedSchoolClass = schoolClassestoCheck.get(0);
                List<Student> students = Student.find(Student.class, "school_class = ?", String.valueOf(selectedSchoolClass.getId()));
                List<SchoolTest> tests = SchoolTest.find(SchoolTest.class, "student = ?", String.valueOf(students.get(counter_rows).getId()));



                if (v == b) {

                    showTestInputDialog(tests.get(counter_columns), students);
                }
                counter_columns++;
            }
            counter_rows++;
        }
    }

    protected void fillFirstRowHomework(List<Homework> homeworks, TableRow row) {

        TextView date = new TextView(this);
        date.setText("Date:");
        date.setTextSize(40);

        if(date.getParent()!= null) {

            ((ViewGroup) date.getParent()).removeView(date);
        }
        row.addView(date);



        for(Homework h: homeworks) {
            TextView dateStudent = new TextView(this);
            Date dateAndTime = h.getDate();
            SimpleDateFormat dayAndMonth = new SimpleDateFormat("dd.MM.");
            SimpleDateFormat year = new SimpleDateFormat("yyyy");
            String onlyDate = dayAndMonth.format(dateAndTime) + "\n" + year.format(dateAndTime);
            dateStudent.setTextSize(18);
            dateStudent.setPadding(1,20,1,50);
            dateStudent.setGravity(Gravity.CENTER);

            dateStudent.setText(onlyDate);


            row.addView(dateStudent);
        }

        addDate = new Button(this);
        addDate.setText("+");
        addDate.setTextSize(40);
        addDate.setOnClickListener(this);

        row.addView(addDate);
        studentTable.addView(row);

    }

    protected void  createHomeworkEntries(List<Homework> homework, TableRow row) {

        ArrayList<Button> buttonsHomework = new ArrayList<Button>();

        for(Homework h: homework) {

            Button tagStudent = new Button(this);

            tagStudent.setText(String.valueOf(h.getTag()));
            tagStudent.setTextSize(20);
            tagStudent.setOnClickListener(this);
            buttonsHomework.add(tagStudent);
            row.addView(tagStudent);
        }

        Button addDateForStudent = new Button(this);
        addDateForStudent.setText("");
        addDateForStudent.setTextSize(40);

        row.addView(addDateForStudent);

        buttonsHomeworkMatrix.add(buttonsHomework);
    }

    protected void checkifHomeworkClicked(View v) {
        int counter_rows = 0;
        int counter_columns = 0;
        for(ArrayList<Button> arrayList : buttonsHomeworkMatrix) {
            counter_columns = 0;
            for (Button b : arrayList) {

                Spinner class_spinner = (Spinner)findViewById(R.id.class_spinner);
                String selected_class = class_spinner.getSelectedItem().toString();
                List<SchoolClass> schoolClassestoCheck = SchoolClass.find(SchoolClass.class, "name = ?", selected_class);
                SchoolClass selectedSchoolClass = schoolClassestoCheck.get(0);
                List<Student> students = Student.find(Student.class, "school_class = ?", String.valueOf(selectedSchoolClass.getId()));
                List<Homework> homework = Homework.find(Homework.class, "student = ?", String.valueOf(students.get(counter_rows).getId()));



                if (v == b) {
                    showHomeworkInputDialog(homework.get(counter_columns), students);
                }
                counter_columns++;
            }
            counter_rows++;
        }
    }
}

