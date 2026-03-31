package com.glauncher.launcher;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.glauncher.launcher.R;
import com.glauncher.launcher.adapters.TextAppAdapter;
import com.glauncher.launcher.adapters.TextToolsPagerAdapter;
import com.glauncher.launcher.models.AppInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextAppAdapter.OnAppClickListener {

    private LinearLayout appListContainer;
    private TextToolsPagerAdapter toolsPagerAdapter;
    private LinearLayout toolsContainer;
    private EditText searchBox;
    private PackageManager pm;

    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] REQUIRED_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pm = getPackageManager();
        initViews();
        checkPermissions();
        loadApps();
        setupToolsPager();
        setupSearch();
        setupDateTime();
    }

    private void initViews() {
        appListContainer = findViewById(R.id.app_list_container);
        searchBox = findViewById(R.id.search_box);
        toolsContainer = findViewById(R.id.tools_container);
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(intent);
            }
        } else {
            List<String> permissionsToRequest = new ArrayList<>();
            for (String permission : REQUIRED_PERMISSIONS) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsToRequest.add(permission);
                }
            }
            if (!permissionsToRequest.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        permissionsToRequest.toArray(new String[0]), PERMISSION_REQUEST_CODE);
            }
        }
    }

    private void loadApps() {
        new Thread(() -> {
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            List<ResolveInfo> availableActivities = pm.queryIntentActivities(intent, 0);
            appList.clear();

            for (ResolveInfo ri : availableActivities) {
                String appName = ri.loadLabel(pm).toString();
                String packageName = ri.activityInfo.packageName;

                // Don't show this launcher in the app list
                if (!packageName.equals(getPackageName())) {
                    AppInfo appInfo = new AppInfo(appName, packageName, ri.activityInfo.name, null);
                    appList.add(appInfo);
                }
            }

            Collections.sort(appList, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
            appListContainer.removeAllViews();
            for (AppInfo app : appList) {
                TextView appView = new TextView(this);
                appView.setText(app.getName());
                appView.setPadding(8, 8, 8, 8);
                appView.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onAppClick(app);
                    }
                });
                appView.setOnLongClickListener(v -> {
                    if (listener != null) {
                        listener.onAppLongClick(app, appView);
                    }
                });
                appListContainer.addView(appView);
            }
        }).start();
    }

    private void setupToolsPager() {
        toolsPagerAdapter = new TextToolsPagerAdapter(this);
        toolsContainer.removeAllViews();
        toolsContainer.addView(toolsPagerAdapter.getFileManagerView());
        toolsContainer.addView(toolsPagerAdapter.getNotepadView());
        toolsContainer.addView(toolsPagerAdapter.getPermissionView());
    }

    private void setupSearch() {
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterApps(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterApps(String query) {
        appListContainer.removeAllViews();
        if (query.isEmpty()) {
            for (AppInfo app : appList) {
                TextView appView = new TextView(this);
                appView.setText(app.getName());
                appView.setPadding(8, 8, 8, 8);
                appView.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onAppClick(app);
                    }
                });
                appView.setOnLongClickListener(v -> {
                    if (listener != null) {
                        listener.onAppLongClick(app, appView);
                    }
                });
                appListContainer.addView(appView);
            } else {
                String lowerQuery = query.toLowerCase();
                for (AppInfo app : appList) {
                    if (app.getName().toLowerCase().contains(lowerQuery)) {
                        TextView appView = new TextView(this);
                        appView.setText(app.getName());
                        appView.setPadding(8, 8, 8, 8);
                        appView.setOnClickListener(v -> {
                            if (listener != null) {
                                listener.onAppClick(app);
                            }
                        });
                        appView.setOnLongClickListener(v -> {
                            if (listener != null) {
                                listener.onAppLongClick(app, appView);
                            }
                        });
                        appListContainer.addView(appView);
                    }
                }
            }
        }
        if (listener != null) {
            // Notify adapter of data changes (not needed for linear layout)
        }
    }

    private void setupDateTime() {
        TextView dateText = findViewById(R.id.date_text);
        TextView timeText = findViewById(R.id.time_text);

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("EEEE, MMMM d", java.util.Locale.getDefault());
        java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault());

        Handler timeHandler = new Handler(Looper.getMainLooper());
        Runnable timeUpdater = new Runnable() {
            @Override
            public void run() {
                dateText.setText(dateFormat.format(new java.util.Date()));
                timeText.setText(timeFormat.format(new java.util.Date()));
                timeHandler.postDelayed(this, 1000);
            }
        };
        timeHandler.post(timeUpdater);
    }

    @Override
    public void onAppClick(AppInfo app) {
        Intent intent = pm.getLaunchIntentForPackage(app.getPackageName());
        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Cannot open app", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAppLongClick(AppInfo app, View view) {
        showAppOptions(app, view);
    }

    private void showAppOptions(AppInfo app, View anchorView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(app.getName());
        builder.setMessage("Select an action:");
        builder.setPositiveButton("Open", (dialog, which) -> {
            onAppClick(app);
            dialog.dismiss();
        });
        builder.setNegativeButton("App Info", (dialog, which) -> {
            showAppInfo(app);
            dialog.dismiss();
        });
        builder.setNeutralButton("Uninstall", (dialog, which) -> {
            uninstallApp(app);
            dialog.dismiss();
        });
        builder.show();
    }

    private void showAppInfo(AppInfo app) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("App Info");
        builder.setMessage("Name: " + app.getName() + "\n" +
                "Package: " + app.getPackageName() + "\n" +
                "Class: " + app.getClassName());
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
    }

    private void uninstallApp(AppInfo app) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + app.getPackageName()));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Stay on home screen - don't exit
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadApps(); // Refresh app list
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    // Permission denied
                }
            }
        }
    }
}