package com.it5.facebookdemo.demo2;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import static android.accounts.AccountManager.KEY_AUTHTOKEN;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by IT5 on 2016/12/5.
 */

public class MyAccount {
    private  Account account;
    private  AccountManager manager;
    private final Activity context;

    public static final String AUTH_TYPE="com.facebook.auth.login";
    private String auth_type=null;
    public MyAccount(Activity context,String auth_type) {
        this.context = context;
        this.auth_type=auth_type;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        final Account[] accounts = AccountManager.get(getApplicationContext()).getAccountsByType(auth_type);
        account = accounts.length > 0 ? accounts[0] : null;
        manager = AccountManager.get(context);
    }

    public void getAuthToken1(){
        final AccountManagerFuture<Bundle> future =manager.getAuthToken(account, auth_type, new Bundle(), context, new OnTokenAcquired(), null);
        if (future!=null) {
            try {
                Bundle result = future.getResult();
                result.getString(KEY_AUTHTOKEN);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public String getAuthToken() {
        Bundle options =new Bundle();

//        AccountManagerFuture<Bundle> future = manager.getAuthToken(account,auth_type, false, null, null);
        final AccountManagerFuture<Bundle> future = manager.getAuthToken(account, auth_type, new Bundle(), context, new OnTokenAcquired(), new Handler(Looper.myLooper()));
//        String token = manager.getUserData(account, AccountManager.KEY_AUTHTOKEN);

        try {
            //GETTING EXCEPTION HERE
            Bundle result = future.getResult();
            return result != null ? result.getString(KEY_AUTHTOKEN) : null;
        }catch (Exception e) {
            Log.e("MyAccout", "Auth token lookup failed", e);
            return null;
        }
    }

   private class OnTokenAcquired implements AccountManagerCallback<Bundle>{

       @Override
       public void run(AccountManagerFuture<Bundle> result) {
           Bundle bundle= null;
           try {
               bundle = result.getResult();
           } catch (Exception e) {
               e.printStackTrace();
           }
           if (bundle==null) {
               return;
           }
           String token=bundle.getString(KEY_AUTHTOKEN);
           Log.e("myaccout", "token: "+token);
       }
   }

    private class OnError implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            Log.e("error ", "handleMessage: "+msg);
            return false;
        }
    }
}
