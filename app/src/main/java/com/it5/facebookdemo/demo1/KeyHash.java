package com.it5.facebookdemo.demo1;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IT5 on 2016/11/16.
 */

public class KeyHash {
    public static void getKeyHash(Context context, String packageName) {
        try {
            PackageInfo info = null;
            info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest messageDigest = null;
                messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                String hs = Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT);
                System.out.println("KeyHash======:  " + hs);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
