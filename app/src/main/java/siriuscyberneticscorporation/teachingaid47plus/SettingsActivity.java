package siriuscyberneticscorporation.teachingaid47plus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonEditClass;
    private Button buttonChangePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonChangePwd = (Button) findViewById(R.id.change_pwd_button);
        buttonEditClass = (Button) findViewById(R.id.edit_class_button);
        buttonChangePwd.setBackgroundResource(R.drawable.mybutton);
        buttonEditClass.setBackgroundResource(R.drawable.mybutton);

        buttonEditClass.setOnClickListener(this);
        buttonChangePwd.setOnClickListener(this);
    }

    public void onClick(View v){
        Button clickedButton = (Button) v;

        if(clickedButton.getId() == R.id.change_pwd_button){
            //input dialog
        }
        else if(clickedButton.getId() == R.id.edit_class_button){
            Intent intent = new Intent(SettingsActivity.this, ListSchoolClassesActivity.class);
            startActivity(intent);
        }

    }
}
