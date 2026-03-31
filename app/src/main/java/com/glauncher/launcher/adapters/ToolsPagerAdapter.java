package com.glauncher.launcher.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.glauncher.launcher.R;
import com.glauncher.launcher.utils.FileUtils;
import com.glauncher.launcher.utils.NoteManager;
import com.glauncher.launcher.utils.PermissionHelper;

import java.util.ArrayList;
import java.util.List;

public class ToolsPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments = new ArrayList<>();
    private final List<String> fragmentTitles = new ArrayList<>();

    public ToolsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        // Add the three main tools: File Manager, Notepad, and Permission Manager
        fragments.add(new FileManagerFragment());
        fragmentTitles.add("File Manager");

        fragments.add(new NotepadFragment());
        fragmentTitles.add("Notepad");

        fragments.add(new PermissionFragment());
        fragmentTitles.add("Permissions");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }

    // Fragment implementations for each tool
    public static class FileManagerFragment extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_file_manager, container, false);
        }
    }

    public static class NotepadFragment extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_notepad, container, false);
        }
    }

    public static class PermissionFragment extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_permission, container, false);
        }
    }
}