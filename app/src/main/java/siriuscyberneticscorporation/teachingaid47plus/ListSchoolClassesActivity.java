package siriuscyberneticscorporation.teachingaid47plus;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListSchoolClassesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    private TableLayout classListing;
    private ArrayList<TextView> classTextViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_classes);
        classListing = (TableLayout) findViewById(R.id.list_classes_table);
        classTextViews = new ArrayList<TextView>();

        Iterator<SchoolClass> classes = SchoolClass.findAll(SchoolClass.class);
        while(classes.hasNext())
        {
            SchoolClass current_class = classes.next();
            TableRow row_for_name = new TableRow(this);
            TableRow row_for_teacher = new TableRow(this);
            TableRow row_for_note = new TableRow(this);

            TextView class_name = new TextView(this);
            TextView teacher_name = new TextView(this);
            TextView class_note = new TextView(this);
            class_name.setText(current_class.getName());
            class_name.setTextSize(50);
            teacher_name.setText(current_class.getClassTeacher());
            teacher_name.setTextSize(25);
            class_note.setText(current_class.getNote());
            class_note.setTextSize(25);
            class_note.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.ruler));
            class_name.setOnClickListener(this);
            classTextViews.add(class_name);
            TableRow row_for_ruler = new TableRow(this);

            row_for_name.addView(class_name);
            row_for_teacher.addView(teacher_name);
            row_for_note.addView(class_note);
            classListing.addView(row_for_name);
            classListing.addView(row_for_teacher);
            classListing.addView(row_for_note);
        }
    }
    protected void checkIfClassClicked(View v) {
        for(TextView tx : classTextViews) {

            if (v == tx) {
                String className = tx.getText().toString();
                SchoolClass school_class = SchoolClass.find(SchoolClass.class, "name = ?", className).get(0);
                Intent school_class_intent = new Intent(ListSchoolClassesActivity.this, EditSchoolClassActivity.class);
                school_class_intent.putExtra("default", school_class.getId());
                startActivity(school_class_intent);
            }
        }
    }

    @Override
    public void onClick(View v) {
        checkIfClassClicked(v);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
