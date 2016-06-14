package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                final Intent home_intent = new Intent(NewClassActivity.this, MainActivity.class);
                if(textName.getText().toString().equals("") && textNote.getText().toString().equals("") &&
                        textTeacher.getText().toString().equals("")) {
                    startActivity(home_intent);
                }
                else{
                    new AlertDialog.Builder(NewClassActivity.this)
                            .setTitle("Attention")
                            .setMessage("The information in the textboxes gets lost, if you use the home-button!")


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
