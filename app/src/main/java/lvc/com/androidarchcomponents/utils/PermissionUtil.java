package lvc.com.androidarchcomponents.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonardo2050 on 06/06/17.
 */

public class PermissionUtil {

    public static final int MY_PERMISSIONS_REQUEST = 10;

    private static String[] PERMISSIONS_NECESSARIES = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static boolean hasToAskForPermission(Activity activity) {
        if(getPermissionNeed(activity).length == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void askNecessaryPermission(Activity context) {
        String[] permissions = getPermissionNeed(context);
        ActivityCompat.requestPermissions(context, permissions, MY_PERMISSIONS_REQUEST);
    }

    public static String[] getPermissionNeed(Activity context) {
        List<String> permissionsToAksList = new ArrayList<String>();

        for(String permission : PERMISSIONS_NECESSARIES) {
            int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
            if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
                permissionsToAksList.add(permission);
            }
        }

        String[] permissionsToAks = toArray(permissionsToAksList);
        return permissionsToAks;
    }

    private static String[] toArray(List<String> permissionsToAksList) {
        int size = permissionsToAksList.size();
        String[] permissionsToAks = new String[size];
        for(int i = 0; i < size; i++) {
            permissionsToAks[i] = permissionsToAksList.get(i);
        }

        return permissionsToAks;
    }

}