package siriuscyberneticscorporation.teachingaid47plus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                final Intent home_intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(home_intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onClick(View v){
        Button clickedButton = (Button) v;

        if(clickedButton.getId() == R.id.change_pwd_button){
            //input dialog
            showChangePasswordDialog();
        }
        else if(clickedButton.getId() == R.id.edit_class_button){
            Intent intent = new Intent(SettingsActivity.this, ListSchoolClassesActivity.class);
            startActivity(intent);
        }

    }
    protected void showChangePasswordDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(SettingsActivity.this);
        final View promptView = layoutInflater.inflate(R.layout.change_password, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SettingsActivity.this);
        alertDialogBuilder.setView(promptView);
        final EditText new_password = (EditText) promptView.findViewById(R.id.new_password);
        final EditText new_password_repeat = (EditText) promptView.findViewById(R.id.new_password_repeat);


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(new_password.getText().toString().equals(new_password_repeat.getText().toString()) ) {
                            setPassword(new_password.getText().toString());
                            new AlertDialog.Builder(SettingsActivity.this)
                                    .setTitle("Success")
                                    .setMessage("Password successfully changed!")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                        else
                        {
                            new AlertDialog.Builder(SettingsActivity.this)
                                    .setTitle("Attention")
                                    .setMessage("Your provided passwords do not match!")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    protected void setPassword(String password) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("user_login", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("47plus_user_password", password);
        editor.commit();
    }
}
