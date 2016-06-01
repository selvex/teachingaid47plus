package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class NewClassActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonDone;
    private EditText textName;
    private EditText textTeacher;
    private EditText textNote;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class);

        buttonDone = (Button) findViewById(R.id.done_button);
        textName = (EditText) findViewById(R.id.class_edittext);
        textTeacher = (EditText) findViewById(R.id.teacher_edittext);
        textNote = (EditText) findViewById(R.id.note_edittext);

        buttonDone.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {


            if (textName.getText().toString().isEmpty()) {
                new AlertDialog.Builder(NewClassActivity.this)
                        .setTitle("Error - Empty Fields")
                        .setMessage("Please fill out the name-field!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else {
                SchoolClass schoolClass = new SchoolClass(textName.getText().toString(), textTeacher.getText().toString(),textNote.getText().toString());
                boolean doesNotExist = true;
                List<SchoolClass> existingClasses = SchoolClass.find(SchoolClass.class, "name = ?", textName.getText().toString());

                if (existingClasses.isEmpty()) {
                    long class_id = schoolClass.save();
                    Intent intent = new Intent(NewClassActivity.this, AddStudentsActivity.class);
                    intent.putExtra("default", class_id);
                    textName.setText("");
                    textTeacher.setText("");
                    textNote.setText("");
                    startActivity(intent);
                }
                else {
                    new AlertDialog.Builder(NewClassActivity.this)
                            .setTitle("Attention")
                            .setMessage("This class already exists.")
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
}
