package com.glauncher.launcher.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Helper class for managing runtime permissions
 * Handles all the permissions required by the launcher as mentioned in README
 */
public class PermissionHelper {

    // Required permissions as listed in README
    public static final String[] REQUIRED_PERMISSIONS = {
            // Storage access
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE,

            // Phone capabilities
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_PHONE_STATE,

            // Contacts
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,

            // Internet and network
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,

            // Media access
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO,

            // Audio controls
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,

            // Special permissions
            Manifest.permission.SET_WALLPAPER,
            Manifest.permission.INSTALL_SHORTCUT,
            Manifest.permission.REQUEST_INSTALL_PACKAGES
    };

    /**
     * Check if all required permissions are granted
     */
    public static boolean arePermissionsGranted(Activity activity) {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Request all required permissions
     */
    public static void requestPermissions(Activity activity, int requestCode) {
        ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, requestCode);
    }
}
