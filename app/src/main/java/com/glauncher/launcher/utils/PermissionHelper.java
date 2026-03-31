package com.glauncher.launcher.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionHelper {

    public static boolean hasAllPermissions(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            List<String> permissions = getRequiredPermissions();
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
    }

    public static List<String> getMissingPermissions(Context context) {
        List<String> missing = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                // For Android R+, we just check the special permission
                missing.add(Manifest.permission.MANAGE_EXTERNAL_STORAGE);
            }
        } else {
            List<String> permissions = getRequiredPermissions();
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    missing.add(permission);
                }
            }
        }
        return missing;
    }

    public static List<String> getRequiredPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.READ_CONTACTS);
        permissions.add(Manifest.permission.INTERNET);
        permissions.add(Manifest.permission.CALL_PHONE);
        return permissions;
    }

    public static void requestPermissions(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // For Android R+, request the special permission
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},
                    100);
        } else {
            List<String> permissions = getRequiredPermissions();
            ActivityCompat.requestPermissions(activity,
                    permissions.toArray(new String[0]), 100);
        }
    }

    public static boolean isPermissionGranted(int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }
}