package com.example.somy.camerabasic;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by somy on 17. 2. 10.
 */

public class PermissionConrtol {

    //permission check list
    public static final String PERMISSION_ARRAY[] = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.CAMERA
    };

    //1. Check the permission
    @TargetApi(Build.VERSION_CODES.M) // Target
    public static boolean checkPermission(Activity activity, int REQ_PERMISSTION){

        //1.1 check the Runtime permission

        boolean permCheck = true;
        for(String perm : PERMISSION_ARRAY){

            if(activity.checkSelfPermission(perm) != PackageManager.PERMISSION_GRANTED){
                permCheck = false;
                break;
            }

        }

        if(permCheck){

            return true;

        } else {

            activity.requestPermissions(PERMISSION_ARRAY, REQ_PERMISSTION);

            return false;

        }


    }


    //@Override //called from system
    public static boolean onCheckResult(int[] grantResults) {

        boolean checkResult = true;
        for(int result : grantResults){
            if(result != PackageManager.PERMISSION_GRANTED){
                checkResult = false;

                break;
            }

        }

        return checkResult;

    }


}
