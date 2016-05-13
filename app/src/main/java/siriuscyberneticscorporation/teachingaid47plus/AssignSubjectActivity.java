package siriuscyberneticscorporation.teachingaid47plus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AssignSubjectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    private Button buttonDone;
    private Spinner subjectsDropdown;

    //----------------------------------------------------------------------------------------------DELETE
    String test[] = {"Geschichte", "Mathe", "Sport", "Musik"};
    //----------------------------------------------------------------------------------------------DELETE

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_subject);

        buttonDone = (Button) findViewById(R.id.done_button);
        subjectsDropdown = (Spinner) findViewById(R.id.subjects_spinner);

        buttonDone.setOnClickListener(this);
        subjectsDropdown.setOnItemSelectedListener(this);

        fillSubjectsDropdown(test);
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
            subjectsDropdown.getSelectedItem();
            Intent intent = new Intent(AssignSubjectActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void fillSubjectsDropdown(String subjects[]) {
        String[] items = new String[subjects.length + 1];
        items[0] = "---";

        for(int i = 1; i <= subjects.length; i++) {
            items[i] = subjects[i - 1];
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        subjectsDropdown.setAdapter(adapter2);
    }
}
