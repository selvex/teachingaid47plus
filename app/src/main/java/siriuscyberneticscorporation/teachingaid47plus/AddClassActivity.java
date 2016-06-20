package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                final Intent home_intent = new Intent(AddClassActivity.this, MainActivity.class);
                startActivity(home_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
