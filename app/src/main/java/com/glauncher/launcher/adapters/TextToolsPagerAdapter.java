package com.glauncher.launcher.adapters;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glauncher.launcher.R;

public class TextToolsPagerAdapter {

    private final LinearLayout container;
    private final MainActivity mainActivity;

    private View fileManagerView;
    private View notepadView;
    private View permissionView;

    public TextToolsPagerAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.container = new LinearLayout(mainActivity);
        this.container.setOrientation(LinearLayout.VERTICAL);

        // File Manager View
        fileManagerView = new TextView(mainActivity);
        fileManagerView.setText("📁 File Manager");
        fileManagerView.setPadding(16, 16, 16, 16);
        fileManagerView.setOnClickListener(v -> {
            // TODO: Implement file manager functionality
        });

        // Notepad View
        notepadView = new TextView(mainActivity);
        notepadView.setText("📝 Notepad");
        notepadView.setPadding(16, 16, 16, 16);
        notepadView.setOnClickListener(v -> {
            // TODO: Implement notepad functionality
        });

        // Permission View
        permissionView = new TextView(mainActivity);
        permissionView.setText("🔐 Permission Manager");
        permissionView.setPadding(16, 16, 16, 16);
        permissionView.setOnClickListener(v -> {
            // TODO: Implement permission manager functionality
        });

        // Add all views to container
        container.addView(fileManagerView);
        container.addView(notepadView);
        container.addView(permissionView);
    }

    public View getFileManagerView() {
        return fileManagerView;
    }

    public View getNotepadView() {
        return notepadView;
    }

    public View getPermissionView() {
        return permissionView;
    }

    public LinearLayout getContainer() {
        return container;
    }
}