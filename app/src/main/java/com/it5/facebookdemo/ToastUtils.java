package com.it5.facebookdemo;

import android.widget.Toast;

/**
 * Created by IT5 on 2016/11/17.
 */
public class ToastUtils {
    public static void showToast(String str) {
        Toast.makeText(MApplication.getContext(),str,Toast.LENGTH_SHORT).show();
    }
}
