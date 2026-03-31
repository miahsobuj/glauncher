package com.glauncher.launcher.adapters;

import android.view.View;
import android.widget.TextView;

import com.glauncher.launcher.models.AppInfo;

public interface OnAppClickListener {
    void onAppClick(AppInfo app);
    void onAppLongClick(AppInfo app, View view);
}

// This interface is now implemented directly in MainActivity for text-based UI