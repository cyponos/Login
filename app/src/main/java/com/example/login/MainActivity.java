package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    TextView infoTv;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();



        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton registerbtn = (MaterialButton) findViewById(R.id.registerbtn);

        //admin and admin
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String password_text = password.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password_text)){
                    Toast.makeText(MainActivity.this, "Please fill in the blank, AirHead!", Toast.LENGTH_SHORT).show();
                }   else{
                    loginUser(email, password_text);
                }

            }
        });


        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivity2();
            }
        });


    }
    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Welcome back "+ email +
                            " We missed you!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this , MainActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void openActivity2() {
        Intent intent =  new Intent(this, Register.class);
        startActivity(intent);
    }
/*
    public void showModal(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.modal);

        final EditText nameEt = dialog.findViewById(R.id.name_et);
        final EditText ageEt = dialog.findViewById(R.id.password_et);
        final CheckBox termsCb = dialog.findViewById(R.id.terms_cb);
        Button submitButton = dialog.findViewById(R.id.submit_button);

        submitButton.setOnClickListener((v) ->{
            String name = nameEt.getText().toString();
            String age = ageEt.getText().toString();
            Boolean hasAccepted = termsCb.isChecked();
            populateInfoTv(name,age,hasAccepted);
            dialog.dismiss();

        } );
        dialog.show();

    }

    void populateInfoTv (String name, String age, Boolean hasAcceptedTerms) {
        infoTv.setVisibility(View.VISIBLE);
        String acceptedText = "have";
        if(!hasAcceptedTerms) {
            acceptedText = "have not";
        }
    }
*/

}
