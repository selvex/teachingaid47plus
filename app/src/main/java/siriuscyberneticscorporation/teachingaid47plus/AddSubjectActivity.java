package siriuscyberneticscorporation.teachingaid47plus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddSubjectActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonDone;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        buttonDone = (Button) findViewById(R.id.done_button);

        buttonDone.setOnClickListener(this);
    }

    public void onClick(View v) {
        Button clickedButton = (Button) v;

        if (clickedButton.getId() == R.id.done_button) {
            Intent intent = new Intent(AddSubjectActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }
}