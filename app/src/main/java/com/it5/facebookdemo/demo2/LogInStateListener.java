package com.it5.facebookdemo.demo2;

/**
 * Created by IT5 on 2016/11/17.
 */

public interface LogInStateListener {
    public  void OnLoginSuccess(User user,String logType);
    public  void OnLoginError(String error);
}
