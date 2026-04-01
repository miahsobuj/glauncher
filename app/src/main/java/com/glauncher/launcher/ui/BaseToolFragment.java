package com.glauncher.launcher.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Base fragment for all tools in the launcher.
 * Each tool will extend this class and implement its specific functionality.
 */
public abstract class BaseToolFragment extends Fragment {

    protected abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize the UI components
        initializeUI(view);
    }

    protected abstract void initializeUI(View view);
}
