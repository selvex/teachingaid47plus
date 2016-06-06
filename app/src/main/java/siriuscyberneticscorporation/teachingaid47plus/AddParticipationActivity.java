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

public class AddParticipationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private Spinner ratingDropdown;
    private EditText dateEdit;
    private EditText noteEdit;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participation);
        //buttonDone = (Button) findViewById(R.id.participation_button_done);
        buttonDone.setOnClickListener(this);
        ratingDropdown = (Spinner) findViewById(R.id.participation_spinner);
        //dateEdit = (EditText) findViewById(R.id.participation_date_edit);
        //noteEdit = (EditText) findViewById(R.id.participation_note_edit);
        ArrayList<String> ratings = new ArrayList<String>();
        ratings.add("~");
        ratings.add("- - -");
        ratings.add("- -");
        ratings.add("-");
        ratings.add("+");
        ratings.add("+ +");
        ratings.add("+ + +");

        fillDropdown(ratings);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        /*if (clickedButton.getId() == R.id.participation_button_done) {
            if(dateEdit.getText().toString().isEmpty()) {
                new AlertDialog.Builder(AddParticipationActivity.this)
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
                Student student = Student.findById(Student.class, 1);
                Subject subject = Subject.findById(Subject.class, 1);
                Participation to_be_created = null;
                try {
                    to_be_created = new Participation(student, subject,
                            dateEdit.getText().toString(), noteEdit.getText().toString(),
                            rating);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(to_be_created == null){
                    return;
                }
                to_be_created.save();

                Intent intent = new Intent(AddParticipationActivity.this, MainActivity.class);
                startActivity(intent);
            }

        }*/
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
