package com.example.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MemberInfoModel extends ViewModel {
    private static final String TAG=MemberInfoModel.class.getSimpleName();
    private MutableLiveData<String> accountLiveData = new MutableLiveData<>();
    private MutableLiveData<String> passwordLiveData = new MutableLiveData<>();
    private MutableLiveData<String> responseMessage = new MutableLiveData<>();
    private static final String REGISTER_COMPLETED = "Register is Completed !";
    private static final String LOGIN_SUCCESS = "Login Success.";
    private static final String PASSWORD_INCORRECT = "Password is Incorrect !";
    private static final String ACCOUNT_DOES_NOT_EXIST = "Account does not exist.";
    private static final String PASSOWRD_SHOULD_EXCEED_EIGHT_CHARACTERS = "Password should exceed eight characters";
    private static final String ACCOUNT_SHOULD_NOT_EMPTY = "Account should not empty";
    private static final String PASSWORD_SHOULD_NOT_EMPTY = "Password should not empty";

   public void registerAccount(String account , String password){
       if(account.length()>0){
           if(password.length()>8){
               accountLiveData.setValue(account);
               passwordLiveData.setValue(password);
               responseMessage.setValue(REGISTER_COMPLETED);
           }else if (password.length()>0 && password.length()<=8){
               responseMessage.setValue(PASSOWRD_SHOULD_EXCEED_EIGHT_CHARACTERS);
           }else if(password.length()==0){
               responseMessage.setValue(PASSWORD_SHOULD_NOT_EMPTY);
           }
       }else if(account.length()==0){
           responseMessage.setValue(ACCOUNT_SHOULD_NOT_EMPTY);
       }
   }

   public void login(String account , String password){
       Log.d(TAG,"account = "+account + "password = "+password);
       Log.d(TAG, "accountLiveData = "+accountLiveData.getValue() + "passwordLiveData = "+passwordLiveData.getValue());
       if(account.equals(accountLiveData.getValue()) && password.equals(passwordLiveData.getValue())){
           responseMessage.setValue(LOGIN_SUCCESS);
       }else if (account.equals(accountLiveData.getValue()) && !password.equals(passwordLiveData.getValue())){
           responseMessage.setValue(PASSWORD_INCORRECT);
       }else if(!account.equals(accountLiveData.getValue())){
           responseMessage.setValue(ACCOUNT_DOES_NOT_EXIST);
       }
   }


    public MutableLiveData<String> getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(MutableLiveData<String> responseMessage) {
        this.responseMessage = responseMessage;
    }

    public MutableLiveData<String> getAccountLiveData() {
        return accountLiveData;
    }

    public void setAccountLiveData(MutableLiveData<String> accountLiveData) {
        this.accountLiveData = accountLiveData;
    }

    public MutableLiveData<String> getPasswordLiveData() {
        return passwordLiveData;
    }

    public void setPasswordLiveData(MutableLiveData<String> passwordLiveData) {
        this.passwordLiveData = passwordLiveData;
    }

    public void clearAccountPassword(){
       accountLiveData.setValue("");
       passwordLiveData.setValue("");
    }
}
