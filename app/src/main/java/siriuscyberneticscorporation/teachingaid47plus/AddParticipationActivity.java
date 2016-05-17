package siriuscyberneticscorporation.teachingaid47plus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddParticipationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    private Spinner ratingDropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participation);
        ratingDropdown = (Spinner) findViewById(R.id.participation_spinner);
        ArrayList<String> ratings = new ArrayList<String>();
        ratings.add("- - -");
        ratings.add("- -");
        ratings.add("-");
        ratings.add("~");
        ratings.add("+");
        ratings.add("+ +");
        ratings.add("+ + +");

        fillDropdown(ratings);
    }

    @Override
    public void onClick(View v) {

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
