package com.it5.facebookdemo.demo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;

import com.it5.facebookdemo.FaceBookLoginUtil;
import com.it5.facebookdemo.R;

import java.io.Serializable;

/**
 * Created by IT5 on 2016/11/17.
 */

public class  MainActivity extends FragmentActivity implements LogInStateListener{
    Button facebookbutton;
    Button tibbrButton;
    Button tibbrforgotButton;
    Button tibbrregButton;
    FaceBookLoginUtil facebookutil;
//    TibbrLoginUtil tibbrUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.initialize(MainActivity.this);
        setContentView(R.layout.fragment_demo2);
        facebookbutton=(Button)findViewById(R.id.facebook_button);

        tibbrButton=(Button)findViewById(R.id.tibbr_button);

        tibbrforgotButton=(Button)findViewById(R.id.tibbr_forget_button);
        tibbrregButton=(Button)findViewById(R.id.tibbr_reg_button);
        login();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();


    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        LoginManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        LoginManager.OnDestory();
    }
    @Override
    public void OnLoginSuccess(User user,String logType) {
        Intent intent=new Intent(MainActivity.this,Result.class);
        intent.putExtra("user",(Serializable)user);
        intent.putExtra("logtype", logType);
        startActivity(intent);
    }
    @Override
    public void OnLoginError(String error) {
        System.out.println(error);
    }
    private void login(){
        LoginManager.setFaceBookLoginParams(MainActivity.this, facebookbutton, null,this);
    }

}
