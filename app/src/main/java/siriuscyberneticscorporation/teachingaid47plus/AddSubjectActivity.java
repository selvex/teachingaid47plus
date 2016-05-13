package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSubjectActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonDone;
    private EditText subjectInput;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        buttonDone = (Button) findViewById(R.id.done_button);
        subjectInput = (EditText) findViewById(R.id.subject_edittext);
        buttonDone.setOnClickListener(this);
    }

    public void onClick(View v) {

        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {
            if(subjectInput.getText().toString().isEmpty()) {
                new AlertDialog.Builder(AddSubjectActivity.this)
                        .setTitle("Error - Empty Name")
                        .setMessage("Please fill out all required fields!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else{
                Subject new_subject = new Subject(subjectInput.getText().toString());
                new_subject.save();

                Intent intent = new Intent(AddSubjectActivity.this, MainActivity.class);
                startActivity(intent);
            }

        }

    }
}