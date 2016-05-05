package siriuscyberneticscorporation.teachingaid47plus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class ExistingClassActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
//public class ExistingClassActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonDone;
    private Spinner classDropdown;
    private Spinner subjectDropdown;

    //----------------------------------------------------------------------------------------------DELETE
    String test1[] = {"Geschichte", "Mathe", "Sport", "Musik"};
    String test2[] = {"4c", "3e", "2d", "7b"};
    //----------------------------------------------------------------------------------------------DELETE


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_class);

        buttonDone = (Button) findViewById(R.id.done_button);
        classDropdown = (Spinner) findViewById(R.id.class_spinner);
        subjectDropdown = (Spinner) findViewById(R.id.subject_spinner);

        buttonDone.setOnClickListener(this);

        classDropdown.setOnItemSelectedListener(this);
        subjectDropdown.setOnItemSelectedListener(this);

        fillClassDropdown(test1);
        fillSubjectsDropdown(test2);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        //TODO Auto-generated method stub
    }

    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {
            classDropdown.getSelectedItem();
            Intent intent = new Intent(ExistingClassActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void fillClassDropdown(String classes[]) {
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, classes);
        classDropdown.setAdapter(adapter1);
    }

    public void fillSubjectsDropdown(String subjects[]) {
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjects);
        subjectDropdown.setAdapter(adapter2);
    }
}
