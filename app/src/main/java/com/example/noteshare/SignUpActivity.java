package com.example.noteshare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private Button btnSignUp;
    private Spinner schoolSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        btnSignUp = findViewById(R.id.btnSignUp);
        schoolSpinner = findViewById(R.id.schoolSpinner);

        ArrayList<String> schoolOptions = new ArrayList<>();
        schoolOptions.add("Select School...");
        schoolOptions.add("Humboldt State University");
        schoolOptions.add("San Francisco State University");
        schoolOptions.add("San Diego State University");
        schoolOptions.add("San Jose State University");
        schoolOptions.add("Sonoma State University");
        schoolOptions.add("UC Berkeley");
        schoolOptions.add("UC Davis");
        schoolOptions.add("UC Riverside");
        schoolOptions.add("UC San Diego");
        schoolOptions.add("UC Santa Barbara");
        schoolOptions.add("UC Santa Cruz");










        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_dropdown_item, schoolOptions);
        schoolAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(schoolAdapter);
        schoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //selectedSemester = schoolAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick signUp button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String passwordConfirm = etPasswordConfirm.getText().toString();

                //Input Validation
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    Log.i(TAG, "Please fill in all fields");
                    Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(passwordConfirm)) {
                    Log.i(TAG, "Passwords do not match!");
                    Toast.makeText(SignUpActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    return;
                }

                else{
                    signUpUser(username,password);
                }
            }
        });
    }

    private void signUpUser(String username, String password) {
        ParseUser user = new ParseUser();
        // Set the user's username and password, which can be obtained by a forms
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(SignUpActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    ParseUser.logOut();
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                goMainActivity();
            }
        });
    }

    private void goMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


}