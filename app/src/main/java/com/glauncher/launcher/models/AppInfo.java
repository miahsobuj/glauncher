package com.glauncher.launcher.models;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private String name;
    private String packageName;
    private String className;
    private Drawable icon;

    public AppInfo(String name, String packageName, String className, Drawable icon) {
        this.name = name;
        this.packageName = packageName;
        this.className = className;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}