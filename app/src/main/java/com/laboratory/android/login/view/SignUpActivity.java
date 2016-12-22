package com.laboratory.android.login.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.laboratory.android.login.R;
import com.laboratory.android.login.model.UserSignIn;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private TextView txFirstName, txLastName, txEmail, txPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txFirstName = (TextView)findViewById(R.id.tx_first_name);
        txLastName = (TextView)findViewById(R.id.tx_last_name);
        txEmail = (TextView)findViewById(R.id.tx_email);
        txPassword = (TextView)findViewById(R.id.tx_password);

        database = FirebaseDatabase.getInstance();



        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(View v){
        mAuth.createUserWithEmailAndPassword(txEmail.getText().toString(),txPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    persistData();
                    Toast.makeText(SignUpActivity.this,"isSuccessful", Toast.LENGTH_LONG).show();
                    Log.e("TAG", "isSuccessful");
                }else{
                    Toast.makeText(SignUpActivity.this,"Sign Up Failed",Toast.LENGTH_LONG).show();
                    Log.e("TAG", "Sign Up Failed");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(SignUpActivity.this, "this email is arely in use", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void persistData(){
        //Persisting data
        databaseRef = FirebaseDatabase.getInstance().getReference("users");

        // Creating new user node, which returns the unique key value
        // new user node would be /users/$userid/
        String userId = databaseRef.push().getKey();

        // creating user object
        UserSignIn user = new UserSignIn(txFirstName.getText().toString(),txLastName.getText().toString(),txEmail.getText().toString());

        // pushing user to 'users' node using the userId
        databaseRef.child(userId).setValue(user);

    }
}
