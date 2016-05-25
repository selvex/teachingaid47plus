package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;

public class AddTestActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText dateEdit;
    private EditText ratingEdit;
    private EditText noteEdit;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        buttonDone = (Button) findViewById(R.id.test_done_button);
        buttonDone.setOnClickListener(this);
        dateEdit = (EditText) findViewById(R.id.test_date_edit);
        ratingEdit = (EditText) findViewById(R.id.test_rating_edit);
        noteEdit = (EditText) findViewById(R.id.test_note_edit);
    }

    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.test_done_button) {
            if(dateEdit.getText().toString().isEmpty()) {
                new AlertDialog.Builder(AddTestActivity.this)
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

                Student student = Student.findById(Student.class, 1);
                Subject subject = Subject.findById(Subject.class, 1);
                SchoolTest to_be_created = null;
                int rating = 0;
                try {
                    rating = Integer.parseInt(ratingEdit.getText().toString());
                }
                catch (NumberFormatException e) {
                    e.printStackTrace();
                    new AlertDialog.Builder(AddTestActivity.this)
                            .setTitle("Invalid Rating")
                            .setMessage("You have to enter a valid rating!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }
                try {
                    to_be_created = new SchoolTest(dateEdit.getText().toString(),student,
                            rating, subject, noteEdit.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (to_be_created == null){
                    new AlertDialog.Builder(AddTestActivity.this)
                            .setTitle("Invalid Date")
                            .setMessage("Please use the format dd.mm.yyyy for the date!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    return;
                }
                to_be_created.save();

                Intent intent = new Intent(AddTestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
