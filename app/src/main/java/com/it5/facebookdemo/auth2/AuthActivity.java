package com.it5.facebookdemo.auth2;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.it5.facebookdemo.demo2.MyAccount;

/**
 * Created by IT5 on 2016/12/6.
 */

public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button btn = new Button(this);
        setContentView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FacebooTask.execute();
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void facebookToken1() throws Exception {
        final AccountManager accountManager = AccountManager.get(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        final Account[] accounts = accountManager.getAccountsByType(null);
        final String authType = MyAccount.AUTH_TYPE;
        Account account = new Account(accounts[0].name, authType);
        AccountManagerFuture accFut =accountManager.getAuthToken(account, "ah", null, this, null, null);
        Bundle authTokenBundle = (Bundle) accFut.getResult();
        String authToken = authTokenBundle.get(AccountManager.KEY_AUTHTOKEN).toString();

    }

    private void facebookToken() {
        final AccountManager accountManager = AccountManager.get(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        final Account[] accounts = accountManager.getAccountsByType(null);
        final String authType = MyAccount.AUTH_TYPE;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String token = null;
                try {
                    token = accountManager.blockingGetAuthToken(accounts[0], authType, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("token", "run: " + token);
            }
        }).start();

    }

    private AsyncTask<Void,Void,Void> FacebooTask =new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
//            facebookToken();
            try {
                facebookToken1();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    };
}
