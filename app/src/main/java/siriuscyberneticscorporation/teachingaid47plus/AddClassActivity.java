package siriuscyberneticscorporation.teachingaid47plus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


public class AddClassActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonNewClass;
    private Button buttonExistingClass;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);


        buttonNewClass = (Button) findViewById(R.id.new_class_button);
        buttonExistingClass = (Button) findViewById(R.id.existing_class_button);
        buttonNewClass.setBackgroundResource(R.drawable.mybutton);
        buttonExistingClass.setBackgroundResource(R.drawable.mybutton);


        buttonNewClass.setOnClickListener(this);
        buttonExistingClass.setOnClickListener(this);
    }

    public void onClick(View v){
        Button clickedButton = (Button) v;

        if(clickedButton.getId() == R.id.new_class_button){
            Intent intent = new Intent(AddClassActivity.this, NewClassActivity.class);
            startActivity(intent);
        }
        else if(clickedButton.getId() == R.id.existing_class_button){
            Intent intent = new Intent(AddClassActivity.this, ExistingClassActivity.class);
            startActivity(intent);
        }

    }
}
