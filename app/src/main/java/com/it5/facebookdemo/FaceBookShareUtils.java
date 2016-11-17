package com.it5.facebookdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * facebook 分享类
 * Created by IT5 on 2016/11/17.
 */

public class FaceBookShareUtils {
    private Activity mActivity ;
    private ShareDialog shareDialog;
    private CallbackManager callBackManager;
    public static final int SHARE_REQUEST_CODE = 10010;
    private ShareLinkContent.Builder shareLinkContentBuilder;

    public FaceBookShareUtils(Activity activity, CallbackManager callBackManager, FacebookCallback facebookCallback) {
        this.mActivity = activity ;
        this.callBackManager = callBackManager;
        shareDialog = new ShareDialog(mActivity);
        //注册分享状态监听回调接口
        shareDialog.registerCallback(callBackManager, facebookCallback, FaceBookShareUtils.SHARE_REQUEST_CODE);
        shareLinkContentBuilder = new ShareLinkContent.Builder();
    }

    /**
     * 分享
     */
    public void share(String contentTitle,String imageUrl,String desc) {

        shareLinkContentBuilder.setContentTitle(contentTitle)
                .setImageUrl(Uri.parse(imageUrl))
                .setContentDescription(desc)
                .setContentUrl(Uri.parse("http://www.soso.com"));
        ShareLinkContent shareLinkContent = shareLinkContentBuilder.build();
        if(shareDialog.canShow(ShareLinkContent.class)) {
            shareDialog.show(mActivity,shareLinkContent);

        }
    }
    /**
     * 分享
     */
    public void share(String contentTitle, Bitmap imageUrl, String desc) {

//        SharePhoto photo = new SharePhoto.Builder()
//                .setBitmap(imageUrl)
//                .setCaption(desc)
//                .build();
//
//        SharePhotoContent content = new SharePhotoContent.Builder()
//                .addPhoto(photo)
////                .setContentUrl(Uri.parse(Api.SHARE_LEFT_URL))
//                .build();
//
//        ShareApi.share(content, null);
//        if(shareDialog.canShow(SharePhotoContent.class)) {
//            shareDialog.show(mActivity,content);
//        }
        if (shareDialog.canShow(ShareLinkContent.class)) {

            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(contentTitle)
                    .setContentDescription(desc)
                    .setContentUrl(Uri.parse("http://www.soso.com"))

//                    .setImageUrl(Uri.parse("android.resource://de.ginkoboy.flashcards/" + com.dora.feed.R.drawable.app_logo))
                    .setImageUrl(Uri.parse("http://bagpiper-andy.de/bilder/dudelsack%20app.png"))
                    .build();

            shareDialog.show(linkContent);
        }

    }
}
