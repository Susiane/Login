package com.laboratory.android.login;

import com.laboratory.android.login.utils.FieldsUtil;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by Susiane on 22/12/2016.
 */

public class FieldsUtilTests extends TestCase{
    @Test
    public void testFieldIsInvalid(){
        String emptyField = " ";
        boolean result = FieldsUtil.isEmpty(emptyField);
        assertFalse(result);
    }

    @Test
    public void testPasswordLessThanSixCharactersIsInvalid(){
        String password = "123";
        boolean result = FieldsUtil.isValidPassword(password);
        assertFalse(result);
    }

    @Test
    public void testEmailWithoutArrobaIsInvalid(){
        String email = "email.com";
        boolean result = FieldsUtil.isValidEmail(email);
        assertFalse(result);
    }

    @Test
    public void testEmailWithoutDotIsInvalid(){
        String email = "email@emailcom";
        boolean result = FieldsUtil.isValidEmail(email);
        assertFalse(result);
    }

    @Test
    public void testEmailWithoutFistSentenceIsInvalid(){
        String email = "@email.com";
        boolean result = FieldsUtil.isValidEmail(email);
        assertFalse(result);
    }

    @Test
    public void testEmailWithoutSentenceComIsInvalid(){
        String email = "email@email.";
        boolean result = FieldsUtil.isValidEmail(email);
        assertFalse(result);
    }


}
