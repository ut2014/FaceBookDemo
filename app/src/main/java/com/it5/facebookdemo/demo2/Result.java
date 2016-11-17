package com.it5.facebookdemo.demo2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.it5.facebookdemo.R;
import com.it5.facebookdemo.ToastUtils;

/**
 * Created by IT5 on 2016/11/17.
 */

public class Result extends Activity implements LogOutStateListener{
    String logType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resutl);
        User user=(User) getIntent().getSerializableExtra("user");
        String logType=getIntent().getStringExtra("logtype");

        TextView text=(TextView)findViewById(R.id.user);
        text.setText(user==null?"":user.getFirstname()+user.getLastname()+"\n logtype:"+logType);
        Button button=(Button)findViewById(R.id.logout_button);
        if(!logType.equals("") && logType.equals("facebook")){
            LoginManager.initialize(Result.this);
            LoginManager.setFaceBookLogOutParams(button,this);
        }else if(!logType.equals("") && logType.equals("tibbr")){
            LoginManager.initialize(Result.this);
//            LoginManager.setTibbrLogOutParams(button, this);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }
    @Override
    public void OnLogOutListener(boolean isSuccess,String logOutType) {
        ToastUtils.showToast(logOutType+" log out success!");
        finish();
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
    }
}
