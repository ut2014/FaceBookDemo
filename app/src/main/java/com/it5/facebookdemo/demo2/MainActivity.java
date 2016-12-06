package com.it5.facebookdemo.demo2;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.it5.facebookdemo.FaceBookLoginUtil;
import com.it5.facebookdemo.R;
import com.it5.facebookdemo.auth2.AuthActivity_1;

import java.io.Serializable;

/**
 * Created by IT5 on 2016/11/17.
 */

public class MainActivity extends FragmentActivity implements LogInStateListener {
    Button facebookbutton;
    Button tibbrButton;
    Button tibbrforgotButton;
    Button tibbrregButton;
    Button authToken;
    FaceBookLoginUtil facebookutil;

    //    TibbrLoginUtil tibbrUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.initialize(MainActivity.this);
        setContentView(R.layout.fragment_demo2);
        facebookbutton = (Button) findViewById(R.id.facebook_button);

        tibbrButton = (Button) findViewById(R.id.tibbr_button);

        tibbrforgotButton = (Button) findViewById(R.id.tibbr_forget_button);
        tibbrregButton = (Button) findViewById(R.id.tibbr_reg_button);
        authToken = (Button) findViewById(R.id.auth_token);
        authToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AuthActivity_1.class));
            }
        });
        login();

    }



    public static final String AUTHTOKEN_TYPE_FULL_ACCESS = "Full access";

    private Account getAuth_token() {
        mAccountManager = AccountManager.get(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }

        Account[] arr = mAccountManager.getAccounts();
        for (Account a : arr) {
            System.out.print(a.name + " : " + a.type);
        }

        final Account availableAccounts[] = mAccountManager.getAccountsByType("com.facebook.auth.login");

        if (availableAccounts.length == 0) {
            Toast.makeText(this, "No accounts", Toast.LENGTH_SHORT).show();
        } else {
            String name[] = new String[availableAccounts.length];
            for (int i = 0; i < availableAccounts.length; i++) {
                name[i] = availableAccounts[i].name;
            }
        }
//        getExistingAccountAuthToken(availableAccounts[0], AUTHTOKEN_TYPE_FULL_ACCESS);
        return availableAccounts[0];
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
    public void OnLoginSuccess(User user, String logType) {
        Intent intent = new Intent(MainActivity.this, Result.class);
        intent.putExtra("user", (Serializable) user);
        intent.putExtra("logtype", logType);
        startActivity(intent);
    }

    @Override
    public void OnLoginError(String error) {
        System.out.println(error);
    }

    private void login() {
        LoginManager.setFaceBookLoginParams(MainActivity.this, facebookbutton, null, this);
    }


    private AccountManager mAccountManager;



    private void showMessage(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
