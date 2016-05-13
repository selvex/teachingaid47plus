package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ServiceConfigurationError;

public class NewClassActivity extends AppCompatActivity implements View.OnClickListener{

    public final static String EXTRA_MESSAGE = "com.siriuscyberneticscorporation.teachingaid47plus.MESSAGE";

    private Button buttonDone;
    private EditText textName;
    private EditText textTacher;
    private EditText textNote;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class);

        buttonDone = (Button) findViewById(R.id.done_button);
        textName = (EditText) findViewById(R.id.class_edittext);
        textTacher = (EditText) findViewById(R.id.teacher_edittext);
        textNote = (EditText) findViewById(R.id.note_edittext);

        buttonDone.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {


            if (textName.getText().toString().isEmpty() || textTacher.getText().toString().isEmpty()) {
                new AlertDialog.Builder(NewClassActivity.this)
                        .setTitle("Error - Empty Fields")
                        .setMessage("Please fill out the name-field and the teacher-field!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {}
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            else {
                SchoolClass schoolClass = new SchoolClass(textName.getText().toString(),textTacher.getText().toString(),textNote.getText().toString());
                long class_id = schoolClass.save();
                Intent intent = new Intent(NewClassActivity.this, AddStudentsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, class_id);
                textName.setText("");
                textTacher.setText("");
                textNote.setText("");
                startActivity(intent);

            }
        }

    }
}
