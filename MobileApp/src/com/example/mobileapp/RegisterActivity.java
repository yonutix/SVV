package com.example.mobileapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends Activity {

    EditText email_textfield;
    EditText password_textfield;
    EditText password_retype;
    EditText name;
    EditText phoneNo;
    CheckBox manager;

    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        manager = (CheckBox)v;
       if(manager.isChecked()){

       }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        email_textfield = (EditText)findViewById(R.id.user_email);
        password_textfield = (EditText)findViewById(R.id.user_password);
        password_retype = (EditText)findViewById(R.id.user_repassword);
        name = (EditText)findViewById(R.id.user_name);
        phoneNo = (EditText)findViewById(R.id.user_phone);
        manager = (CheckBox)findViewById(R.id.user_manager);
    }

    public void onRegisterButtonDone(View v){

        User newUser;

        if(! password_textfield.getText().toString().equals(password_retype.getText().toString())){
            Context context = getApplicationContext();
            CharSequence text = "Make sure passwords match!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if(email_textfield.getText().toString().isEmpty()){
            Context context = getApplicationContext();
            CharSequence text = "Please insert email!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else{
            GlobalDBConnection.UserType userType = GlobalDBConnection.UserType.CLIENT;
            if(manager.isChecked()){
                userType = GlobalDBConnection.UserType.MANAGER;

            }
            newUser = new User(name.getText().toString(), email_textfield.getText().toString(), password_textfield.getText().toString(),
                    phoneNo.getText().toString(), userType);

            if(newUser.register()){
                Intent result = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(result);
            } else {
                CharSequence text = "Unable to create user!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        }
    }
}