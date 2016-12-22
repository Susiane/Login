package com.laboratory.android.login.view;

import android.content.Intent;
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
import com.laboratory.android.login.controller.SignUpController;
import com.laboratory.android.login.model.UserSignUp;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private UserSignUp userSignUp;
    private SignUpController signUpController;
    private String password;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        signUpController =  new SignUpController(this);
    }

    /***
     * Method that is called from SignUp button, valid fields, create user account and login
     * @param v
     */
    public void signUp(View v){
        signUpController.cleanTextViewAuthMessage();
        if(signUpController.isValidFields()) {
            userSignUp = signUpController.getUserFromActivity();
            password = signUpController.getPassword();
            mAuth.createUserWithEmailAndPassword(userSignUp.geteMail(), password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                persistData(userSignUp.getName(), userSignUp.getLastName(), userSignUp.geteMail());
                                intent = new Intent(SignUpActivity.this, WelcomeActivity.class);
                                startActivity(intent);
                            } else {
                                signUpController.showInTextViewAuthMessage(getResources().getString(R.string.sign_up_failed));
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof FirebaseAuthUserCollisionException) {
                        signUpController.showInTextViewAuthMessage(getResources().getString(R.string.email_in_use));
                    }
                }
            });
        }
    }

    /**
     * Persist user name and email in Firebase realtime database
     * @param fisrtName
     * @param lastName
     * @param email
     */
    public void persistData(String fisrtName, String lastName, String email){
        databaseRef = FirebaseDatabase.getInstance().getReference("users");
        String userId = databaseRef.push().getKey();
        UserSignUp user = new UserSignUp(fisrtName,lastName,email);
        databaseRef.child(userId).setValue(user);
    }

}
