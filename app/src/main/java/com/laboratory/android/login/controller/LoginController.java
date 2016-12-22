package com.laboratory.android.login.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.laboratory.android.login.R;
import com.laboratory.android.login.model.UserLogin;
import com.laboratory.android.login.utils.FieldsUtil;
import com.laboratory.android.login.view.SignUpActivity;

/**
 * Created by Susiane on 21/12/2016.
 */

public class LoginController {
    private EditText txEmail, txPassword;
    private UserLogin userLogin;
    private Intent intent;
    private Activity loginActivity;
    private String emailAdress,password;
    private TextView tvAuthMessage;

    public LoginController(Activity loginActivity) {
        userLogin = new UserLogin();
        this.loginActivity = loginActivity;
        this.txEmail = (EditText) loginActivity.findViewById(R.id.tx_email);
        this.txPassword = (EditText) loginActivity.findViewById(R.id.tx_password);
        this.tvAuthMessage = (TextView)loginActivity.findViewById(R.id.tv_auth_message);
    }

    /**
     * get information from fields and send UserLogin object
     * @return UserLogin object
     */
    public UserLogin getUserFromActivity() {
            this.userLogin.seteMail(this.txEmail.getText().toString());
            this.userLogin.setPassword(this.txPassword.getText().toString());
            return this.userLogin;
    }

    /**
     * Open SignUpActivity
     */
    public void openSignUp() {
        intent = new Intent(this.loginActivity, SignUpActivity.class);
        this.loginActivity.startActivity(intent);
    }

    /**
     * Valid fields
     * @return
     */
    public boolean isValidFields() {
        this.emailAdress = this.txEmail.getText().toString();
        this.password = this.txPassword.getText().toString();

        if(isEmptyFields()){
            return false;
        }else if(FieldsUtil.isValidEmail(this.emailAdress)){
            if (FieldsUtil.isValidPassword(this.password)){
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

    /**
     * Verify empty fields
     * @return
     */
    public boolean isEmptyFields(){
        if(FieldsUtil.isEmpty(this.emailAdress)){
            showEmptyEmailErrorMessage();
            return true;
        }else if(FieldsUtil.isEmpty(this.password)){
            showEmptyPassordErrorMessage();
            return true;
        }else{
            return false;
        }
    }

    /**
     * Show empty email error message in EditText
     */
    public void showEmptyEmailErrorMessage(){
        this.txEmail.setError(loginActivity.getResources().getString(R.string.field_email_empty_message));
    }

    /**
     * Show empty password error message in EditText
     */
    public void showEmptyPassordErrorMessage(){
        this.txPassword.setError(loginActivity.getResources().getString(R.string.field_password_empty_message));
    }

    /**
     * Show invalid error error message in EditText
     */
    public void showInvalidEmailError(){
        this.txEmail.setError(loginActivity.getResources().getString(R.string.field_email_invalid_message));
    }

    /**
     * Show invalid password error message in EditText
     */
    public void showInvalidPasswordlError(){
        this.txPassword.setError(loginActivity.getResources().getString(R.string.field_password_invalid_message));
    }

    /**
     * Show message in TextView
     * @param message
     */
    public void showInTextViewAuthMessage(String message){
        this.tvAuthMessage.setVisibility(View.VISIBLE);
        this.tvAuthMessage.setText(message);
    }

    /**
     * Clean TextView
     */
    public void cleanTextViewAuthMessage(){
        this.tvAuthMessage.setVisibility(View.GONE);
        this.tvAuthMessage.setText(" ");
    }

}
