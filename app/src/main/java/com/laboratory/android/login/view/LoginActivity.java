package com.laboratory.android.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.laboratory.android.login.controller.LoginController;
import com.laboratory.android.login.model.UserLogin;
import com.laboratory.android.login.utils.Constants;
import com.laboratory.android.login.R;

public class LoginActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView youTubePlayerView;
    private FirebaseAuth mAuth;
    private LoginController loginController;
    private UserLogin userLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.video_introduction);
        youTubePlayerView.initialize(Constants.API_KEY, this);
        mAuth = FirebaseAuth.getInstance();
        loginController = new LoginController(this);
    }

    public void login(View v) {
        Log.e("TAG", String.valueOf(loginController.isValidFields()));
        if(loginController.isValidFields()){
            userLogin = loginController.getUserFromActivity();
            mAuth.signInWithEmailAndPassword(userLogin.geteMail(), userLogin.getPassword())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Signed", Toast.LENGTH_LONG).show();
                                Log.e("TAG", "Signed");
                                Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                                startActivity(intent);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(LoginActivity.this, "Invalid password.", Toast.LENGTH_SHORT).show();
                            } else if (e instanceof FirebaseAuthInvalidUserException) {
                                Toast.makeText(LoginActivity.this, "No account whith this e-mail..", Toast.LENGTH_SHORT).show();
                            } else {
                                //TODO: Lembrar de modificar
                                Toast.makeText(LoginActivity.this, "No account whith this e-mail..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    public void openSignUp(View v) {
        loginController.openSignUp();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(Constants.VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, getResources().getString(R.string.video_fail_message), Toast.LENGTH_LONG).show();
    }
}
