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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AddHomeworkActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private Spinner ratingDropdown;
    private EditText dateEdit;
    private EditText noteEdit;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        buttonDone = (Button) findViewById(R.id.homework_button_done);
        buttonDone.setOnClickListener(this);
        ratingDropdown = (Spinner) findViewById(R.id.homework_spinner);
        dateEdit = (EditText) findViewById(R.id.homework_date_edit);
        noteEdit = (EditText) findViewById(R.id.homework_note_edit);
        ArrayList<String> ratings = new ArrayList<String>();

        ratings.add("ok");
        ratings.add("excellent");
        ratings.add("late");
        ratings.add("incomplete");
        ratings.add("missing");

        fillDropdown(ratings);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.homework_button_done) {
            Homework to_check_date = new Homework(Homework.Tags.NONE);
            try {
                to_check_date.setDate(dateEdit.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
                new AlertDialog.Builder(AddHomeworkActivity.this)
                        .setTitle("Invalid Date")
                        .setMessage("You have to enter a valid date!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return;
            }
            if(dateEdit.getText().toString().isEmpty()) {
                new AlertDialog.Builder(AddHomeworkActivity.this)
                        .setTitle("Invalid Date")
                        .setMessage("You have to enter a valid date!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else{
                Homework.Tags rating = null;
                switch (ratingDropdown.getSelectedItem().toString()){
                    case "missing":
                        rating = Homework.Tags.MISSING;
                        break;
                    case "late":
                        rating = Homework.Tags.LATE;
                        break;
                    case "incomplete":
                        rating = Homework.Tags.INCOMPLETE;
                        break;
                    case "none":
                        rating = Homework.Tags.NONE;
                        break;
                    case "ok":
                        rating = Homework.Tags.OK;
                        break;
                    case "excellent":
                        rating = Homework.Tags.EXCELLENT;
                        break;
                }
                SchoolClass schoolClass = new SchoolClass("sd","sdf","");
                Student to_add = new Student("asfd","asdf","","","","", schoolClass);
                Subject rudi = new Subject("Tf", schoolClass);
                schoolClass.save();
                to_add.save();
                rudi.save();

                Student student = Student.findById(Student.class, 2);
                Subject subject = Subject.findById(Subject.class, 2);

                List<Homework> check_if_existing = Homework.find(Homework.class, "date = ? and student = ? and subject = ?",
                                                    dateEdit.getText().toString(), String.valueOf(student.getId()), String.valueOf(subject.getId()));
                if(check_if_existing.isEmpty())
                {
                    Homework to_be_created = null;
                    try {
                        to_be_created = new Homework(student, subject,
                                dateEdit.getText().toString(), rating, noteEdit.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if(to_be_created == null){
                        return;
                    }
                    to_be_created.save();

                    schoolClass.delete();
                    to_add.delete();
                    rudi.delete();

                    Intent intent = new Intent(AddHomeworkActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Homework to_edit = check_if_existing.get(0);
                    try {
                        to_edit.setDate(dateEdit.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    to_edit.setNote(noteEdit.getText().toString());
                    to_edit.setTag(rating);
                    to_edit.save();
                    schoolClass.delete();
                    to_add.delete();
                    rudi.delete();
                    Intent intent = new Intent(AddHomeworkActivity.this, MainActivity.class);
                    startActivity(intent);


                }


            }

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void fillDropdown(ArrayList<String> ratings) {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ratings);
        ratingDropdown.setAdapter(adapter1);
    }
}
