package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends AppCompatActivity {


    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
            mAuth = FirebaseAuth.getInstance();
        TextView username =(TextView) findViewById(R.id.profileUsername);
        TextView email =(TextView) findViewById(R.id.profileEmail);
        TextView cubcash =(TextView) findViewById(R.id.profileCubCash);
        btnLogout = findViewById(R.id.LogOutButton);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(Profile.this,MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(Profile.this,"Logout Successful!",Toast.LENGTH_LONG).show();
            }
        });
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String Name = userProfile.Name;
                    String Email = userProfile.Email;
                    String CubCash = String.valueOf(userProfile.CubCash);

                    username.setText("Name: " + Name + "!");
                    email.setText("Email: " + Email);
                    cubcash.setText("CubCash: $" + CubCash);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
