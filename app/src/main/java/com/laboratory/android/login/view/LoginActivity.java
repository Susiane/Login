package com.laboratory.android.login.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.transition.TransitionInflater;
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
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.video_introduction);
        youTubePlayerView.initialize(Constants.API_KEY, this);
        mAuth = FirebaseAuth.getInstance();
        loginController = new LoginController(this);
    }

    /**
     * Method that is called from Login button, valid fields, do authentication and login
     * @param v
     */
    public void login(View v) {
        loginController.cleanTextViewAuthMessage();
        if(loginController.isValidFields()){
            userLogin = loginController.getUserFromActivity();
            mAuth.signInWithEmailAndPassword(userLogin.geteMail(), userLogin.getPassword())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                                startActivity(intent);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                loginController.showInTextViewAuthMessage(getResources().getString(R.string.invalid_password));
                            } else if (e instanceof FirebaseAuthInvalidUserException) {
                                loginController.showInTextViewAuthMessage(getResources().getString(R.string.invalid_email));
                            } else {
                                loginController.showInTextViewAuthMessage(getResources().getString(R.string.authentication_failed));
                            }
                        }
                    });
        }

    }

    /**
     * Open SignUpActivity
     * @param v
     */
    public void openSignUp(View v) {
        loginController.openSignUp();
    }

    /**
     * Listener from Youtube API that is called when initialization is sucess and set content in YouTubePlayerView
     * @param provider
     * @param youTubePlayer
     * @param wasRestored
     */
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(Constants.VIDEO_ID);
        }
    }

    /**
     * Listener from Youtube API that is called when initialization is failure and show a message
     * @param provider
     * @param youTubeInitializationResult
     */
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, getResources().getString(R.string.video_fail_message), Toast.LENGTH_LONG).show();
    }
}
