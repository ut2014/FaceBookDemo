package com.it5.facebookdemo.demo2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.it5.facebookdemo.FaceBookLoginUtil;

/**
 * Created by IT5 on 2016/11/17.
 */

public class LoginManager {
    private static FaceBookLoginUtil fbUtil=null;
//    private static TibbrLoginUtil tbutils=null;
    /**
     * 登陆工具类，
     *
     */
    public static void initialize(Context context){
        fbUtil=FaceBookLoginUtil.getInstance();
//        tbutils=TibbrLoginUtil.getInstance();
    }
    /**
     * 1 if you use facebook account login,you must call this method and set params.
     * @param activity
     * @param loginButton
     * @param arrarPermission
     * @param loginstateListener
     */
    public  static void setFaceBookLoginParams(Activity activity, View loginButton, String arrarPermission, LogInStateListener loginstateListener){
        fbUtil.SetFaceBookLoginActivity(activity);
        fbUtil.SetFaceBookLoginButton(loginButton);
        fbUtil.SetFaceBookReadPermission(arrarPermission);
        fbUtil.SetOnFaceBookLoginStateListener(loginstateListener);
        fbUtil.open();
    }
    /**
     * if you use facebook logout,you must call this method and set params
     * @param logoutButton
     * @param logOutStateListener
     */
    public static void setFaceBookLogOutParams(View logoutButton, LogOutStateListener logOutStateListener){
        //fbUtil.SetFaceBookLoginActivity(activity);
        fbUtil.SetFaceBookLogOutButton(logoutButton);
        fbUtil.SetOnFaceBookLogOutListener(logOutStateListener);
        fbUtil.open();
    };

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(fbUtil!=null){
            fbUtil.onActivityResult(requestCode, resultCode, data);
        }

       /* else if(tbutils!=null){
            tbutils.onActivityResult(requestCode, resultCode, data);
        }*/
    }
    public static void OnDestory(){
        if(fbUtil!=null){
            fbUtil.OnDestory();
        }
       /* else if(tbutils!=null){
            tbutils.onDestroy();
        }*/
    }

}
