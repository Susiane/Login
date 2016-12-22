package com.laboratory.android.login.controller;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.laboratory.android.login.R;
import com.laboratory.android.login.model.UserSignUp;
import com.laboratory.android.login.utils.FieldsUtil;

/**
 * Created by Susiane on 22/12/2016.
 */

public class SignUpController {
    private TextView txFirstName, txLastName, txEmail, txPassword;
    private UserSignUp userSignUp;
    private Activity signUpActivity;
    private String strFistName, strLastName, strEmail, strPassword;
    private TextView tvAuthMessage;

    public SignUpController(Activity signUpActivity) {
        this.userSignUp = new UserSignUp();
        this.signUpActivity = signUpActivity;
        this.txFirstName = (TextView) signUpActivity.findViewById(R.id.tx_first_name);
        this.txLastName = (TextView) signUpActivity.findViewById(R.id.tx_last_name);
        this.txEmail = (TextView) signUpActivity.findViewById(R.id.tx_email);
        this.txPassword = (TextView) signUpActivity.findViewById(R.id.tx_password);
        this.tvAuthMessage = (TextView)signUpActivity.findViewById(R.id.tv_auth_message);

    }

    /**
     * get information from fields and send UserSignUp object
     * @return UserSignUp object
     */
    public UserSignUp getUserFromActivity(){
        this.userSignUp.seteMail(this.txEmail.getText().toString());
        this.userSignUp.setName(this.txFirstName.getText().toString());
        this.userSignUp.setLastName(this.txLastName.getText().toString());
        return this.userSignUp;
    }

    /**
     * get password information from field and send a String object
     * @return
     */
    public String getPassword(){
        return this.txPassword.getText().toString();
    }

    /**
     * Valid fields
     * @return
     */
    public boolean isValidFields() {
        this.strEmail = this.txEmail.getText().toString();
        this.strPassword = this.txPassword.getText().toString();
        this.strFistName = this.txFirstName.getText().toString();
        this.strLastName = this.txLastName.getText().toString();

        if(isEmptyFields()){
            return false;
        }else if(FieldsUtil.isValidEmail(this.strEmail)){
            if (FieldsUtil.isValidPassword(this.strPassword)){
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
        if(FieldsUtil.isEmpty(this.strEmail)){
            showEmptyEmailErrorMessage();
            return true;
        }else if(FieldsUtil.isEmpty(this.strPassword)){
            showEmptyPassordErrorMessage();
            return true;
        }else if(FieldsUtil.isEmpty(this.strFistName)){
            showEmptyFistNameErrorMessage();
            return true;
        }else if(FieldsUtil.isEmpty(this.strLastName)){
            showEmptyLastNameErrorMessage();
            return true;
        }else{
            return false;
        }
    }

    /**
     * Show empty email error message in EditText
     */
    public void showEmptyEmailErrorMessage(){
        this.txEmail.setError(signUpActivity.getResources().getString(R.string.field_email_empty_message));
    }

    /**
     * Show empty password error message in EditText
     */
    public void showEmptyPassordErrorMessage(){
        this.txPassword.setError(signUpActivity.getResources().getString(R.string.field_password_empty_message));
    }

    /**
     * Show empty fist name error message in EditText
     */
    public void showEmptyFistNameErrorMessage(){
        this.txFirstName.setError(signUpActivity.getResources().getString(R.string.field_first_name_empty_message));
    }

    /**
     * Show empty last name error message in EditText
     */
    public void showEmptyLastNameErrorMessage(){
        this.txLastName.setError(signUpActivity.getResources().getString(R.string.field_last_name_empty_message));
    }

    /**
     * Show invalid error error message in EditText
     */
    public void showInvalidEmailError(){
        this.txEmail.setError(signUpActivity.getResources().getString(R.string.field_email_invalid_message));
    }

    /**
     * Show invalid password error message in EditText
     */
    public void showInvalidPasswordlError(){
        this.txPassword.setError(signUpActivity.getResources().getString(R.string.field_password_invalid_message));
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
