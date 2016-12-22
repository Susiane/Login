package com.laboratory.android.login.controller;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.laboratory.android.login.R;
import com.laboratory.android.login.model.UserLogin;
import com.laboratory.android.login.view.SignUpActivity;

/**
 * Created by Susiane on 21/12/2016.
 */

public class LoginController {
    private EditText txEmail, txPassword;
    private UserLogin userLogin;
    private Intent intent;
    private Activity loginActivity;
    private String eMailAdress;

    public LoginController(Activity loginActivity) {
        userLogin = new UserLogin();
        this.loginActivity = loginActivity;
        this.txEmail = (EditText) loginActivity.findViewById(R.id.tx_email);
        this.txPassword = (EditText) loginActivity.findViewById(R.id.tx_password);
    }

    public UserLogin getUserFromActivity() {
            this.userLogin.seteMail(this.txEmail.getText().toString());
            this.userLogin.setPassword(this.txPassword.getText().toString());
            return this.userLogin;
    }

    public void openSignUp() {
        intent = new Intent(this.loginActivity, SignUpActivity.class);
        this.loginActivity.startActivity(intent);
    }

    public boolean isValidFields() {
        if(isEmptyFields()){
            return false;
        }else if(isValidEmail()){
            if (isValidPassword()){
                return true;
            }else{
                showInvalidPasswordlError();
                return false;
            }
        }else{
            showInvalidEmailError();
            return false;
        }
    }

    public boolean isValidEmail() {
        this.eMailAdress = this.txEmail.getText().toString();
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(this.eMailAdress);
        return m.matches();
    }

    public boolean isValidPassword(){
        if(this.txPassword.getText().toString().length() >= 6){
            return true;
        }else {
            return false;
        }
    }

    public boolean isEmptyFields(){
        if(isEmpty(this.txEmail)){
            showEmptyEmailErrorMessage();
            return false;
        }else if(isEmpty(this.txPassword)){
            showEmptyPassordErrorMessage();
            return false;
        }else{
            return true;
        }
    }

    public boolean isEmpty(TextView textView){
        return textView.getText().toString().isEmpty();
    }

    public void showEmptyEmailErrorMessage(){
        //TODO: PUT STRING ON RESOURCE
        this.txEmail.setError("Please enter a email adress.");
    }
    public void showEmptyPassordErrorMessage(){
        //TODO: PUT STRING ON RESOURCE
        this.txPassword.setError("Please enter a valid password.");
    }

    public void showInvalidEmailError(){
        //TODO: PUT ON RESOURCE
        this.txEmail.setError("Invalid email address. Valid e-mail can contain only latin letters, numbers, '@' and '.");
    }

    public void showInvalidPasswordlError(){
        //TODO: PUT ON RESOURCE
        this.txPassword.setError("Password must be 6 characters");
    }
}
