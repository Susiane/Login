package com.laboratory.android.login.controller;

import android.app.Activity;
import android.content.Intent;

import com.laboratory.android.login.view.CropActivity;

/**
 * Created by Susiane on 22/12/2016.
 */

public class WelcomeController {
    Activity wellcomeActivity;

    public WelcomeController(Activity wellcomeActivity) {
        this.wellcomeActivity = wellcomeActivity;
    }

    public void openCamera(){
        /*Intent intent = new Intent(this.wellcomeActivity, CropActivity.class);
        this.wellcomeActivity.startActivity(intent);*/
    }
}
