package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditSchoolClassActivity extends AppCompatActivity implements View.OnClickListener {
    private Button doneButton;
    private Button addButton;
    private Intent prevIntent;
    private EditText className;
    private EditText classTeacher;
    private EditText classNote;
    private TableLayout studentTable;
    private TableLayout subjectTable;
    private ArrayList<Button> deleteStudent;
    private ArrayList<Button> deleteSubject;
    SchoolClass from_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_school_class);
        addButton = (Button) findViewById(R.id.add_student_button);
        addButton.setBackgroundResource(R.drawable.mybutton);
        addButton.setOnClickListener(this);
        doneButton = (Button) findViewById(R.id.done_button);
        doneButton.setOnClickListener(this);
        prevIntent = getIntent();

        long classID = prevIntent.getLongExtra("default", 0);
        from_db = SchoolClass.findById(SchoolClass.class, classID);
        className = (EditText) findViewById(R.id.class_edittext);
        className.setText(from_db.getName());
        classTeacher = (EditText) findViewById(R.id.teacher_edittext);
        classTeacher.setText(from_db.getClassTeacher());
        classNote = (EditText) findViewById(R.id.note_edittext);
        classNote.setText(from_db.getNote());
        studentTable = (TableLayout) findViewById(R.id.student_table);
        List<Student> students = Student.find(Student.class, "school_class = ?", String.valueOf(from_db.getId()));
        TableRow row = new TableRow(this);
        row.setPadding(15, 0, 0, 0);

        TextView studentTextView = new TextView(this);
        studentTextView.setText("Students:");
        studentTextView.setTextSize(40);
        studentTextView.setPadding(0, 30, 0, 0);
        row.addView(studentTextView);
        TextView nothing = new TextView(this);
        nothing.setText("");
        row.addView(nothing);
        studentTable.addView(row);


        for(Student s: students){
            row = new TableRow(this);
            TextView student_db = new TextView(this);
            student_db.setText(s.getName());
            student_db.setTextSize(30);
            student_db.setOnClickListener(this);
            row.addView(student_db);
            Button delete = new Button(this);
            delete.setText("-");

            delete.setOnClickListener(this);
            delete.setBackgroundResource(R.drawable.mybutton);
            row.addView(delete);
            /*deleteStudent.add(delete);*/
            studentTable.addView(row);
        }


        subjectTable = (TableLayout) findViewById(R.id.subject_table);
        List<Subject> subjects = Subject.find(Subject.class, "school_class = ?", String.valueOf(from_db.getId()));
        row = new TableRow(this);
        row.setPadding(15, 0, 0, 0);

        TextView subjectTextView = new TextView(this);
        subjectTextView.setText("Subjects:");
        subjectTextView.setTextSize(40);
        subjectTextView.setPadding(0, 30, 0, 0);
        row.addView(subjectTextView);
        nothing = new TextView(this);
        nothing.setText("");
        row.addView(nothing);
        subjectTable.addView(row);


        for(Subject s: subjects){
            row = new TableRow(this);
            TextView subject_db = new TextView(this);
            subject_db.setText(s.getName());
            subject_db.setTextSize(30);
            subject_db.setOnClickListener(this);
            row.addView(subject_db);
            Button delete = new Button(this);
            delete.setText("-");
            delete.setOnClickListener(this);
            delete.setBackgroundResource(R.drawable.mybutton);
            row.addView(delete);
            //deleteStudent.add(delete);
            subjectTable.addView(row);
        }

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
                final Intent home_intent = new Intent(EditSchoolClassActivity.this, MainActivity.class);
                if(from_db.getName().equals(className.getText().toString()) &&
                        from_db.getClassTeacher().equals(classTeacher.getText().toString()) &&
                        from_db.getNote().equals(classNote.getText().toString())) {
                    startActivity(home_intent);
                }
                else{
                    new AlertDialog.Builder(EditSchoolClassActivity.this)
                            .setTitle("Attention")
                            .setMessage("You have changed some information without saving it! If you continue " +
                                        "the new information will be lost.")


                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    startActivity(home_intent);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick (View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {

            Intent intent = new Intent(EditSchoolClassActivity.this, ListSchoolClassesActivity.class);

            if (className.getText().toString().equals("")){
                new AlertDialog.Builder(EditSchoolClassActivity.this)
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
                from_db.setName(className.getText().toString());
                from_db.setClassTeacher(classTeacher.getText().toString());
                from_db.setNote(classNote.getText().toString());
                from_db.save();
                startActivity(intent);
            }


        }


    }
}
