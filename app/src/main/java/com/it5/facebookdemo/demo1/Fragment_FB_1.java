package com.it5.facebookdemo.demo1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.it5.facebookdemo.FaceBookShareUtils;
import com.it5.facebookdemo.R;
import com.it5.facebookdemo.SysoutUtil;
import com.it5.facebookdemo.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by IT5 on 2016/11/17.
 */

public class Fragment_FB_1 extends Fragment {
    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebook, container, false);

        loginButton = (LoginButton) view.findViewById(R.id.loginButton);
        /*loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });*/
        view.findViewById(R.id.sharing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareFaceBook();
            }
        });
        LoginFaceBook();
        return view;
    }

    public void LoginFaceBook(){
        List<String> permissions = Arrays.asList("public_profile", "user_friends", "user_status");

        LoginManager.getInstance().logInWithReadPermissions(getActivity(), permissions);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                SysoutUtil.out("logingResult:" + loginResult);
                AccessToken accessToken = loginResult.getAccessToken();
                GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        //获取登录成功之后的用户详细信息
                        SysoutUtil.out("JSONObject:" + object);
                        String facebook_id = object.optString("id");
                        String facebook_name = object.optString("name");
                        String picture = object.optString("picture");
                        String imageUrl = null;
                        try {
                            JSONObject jsonObject = new JSONObject(picture);
                            String data = jsonObject.getString("data");
                            imageUrl = new JSONObject(data).getString("url");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                //包入你想要得到的資料 送出request
               /* Bundle parameters = new Bundle();
                parameters.putString("fields", DEFAULT_REQUEST_VALUE);
                request.setParameters(parameters);
                request.executeAsync();*/
            }

            @Override
            public void onCancel() {
                SysoutUtil.out("onCancel:");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                SysoutUtil.out("onError:");
            }
        });
    }

    public void shareFaceBook() {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.user_default);
        callbackManager  = CallbackManager.Factory.create();
        new FaceBookShareUtils(getActivity(),callbackManager,facebookCallback)
                .share(getResources().getString(R.string.app_name),
                        image,
                        getResources().getString(R.string.share_tips_tips));
    }

    /**
     * facebook分享状态回调
     */
    private FacebookCallback facebookCallback = new FacebookCallback() {

        @Override
        public void onSuccess(Object o) {
            SysoutUtil.out("onSuccess" + o.toString());
//            Message msg = Message.obtain();
//            msg.what = SHARE_COMPLETE;
//            mHandler.sendMessage(msg);
        }

        @Override
        public void onCancel() {
            SysoutUtil.out("onCancel");
//            Message msg = Message.obtain();
//            msg.what = SHARE_CANCEL;
//            mHandler.sendMessage(msg);
        }

        @Override
        public void onError(FacebookException error) {
            SysoutUtil.out("onError");
            ToastUtils.showToast("share error--" + error.getMessage());
//            Message msg = Message.obtain();
//            msg.what = SHARE_ERROR;
//            mHandler.sendMessage(msg);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(callbackManager != null){ //facebook的回调
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        /*if(loginButton != null){    //twitter的回调
            loginButton.onActivityResult(requestCode, resultCode, data);

        }*/

        SysoutUtil.out("data:" + data);
    }
}
