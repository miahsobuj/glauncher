package com.glauncher.launcher.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.glauncher.launcher.ui.CalculatorFragment;
import com.glauncher.launcher.ui.FileManagerFragment;
import com.glauncher.launcher.ui.LauncherFragment;
import com.glauncher.launcher.ui.MusicPlayerFragment;
import com.glauncher.launcher.ui.NotepadFragment;
import com.glauncher.launcher.ui.PhoneContactsFragment;
import com.glauncher.launcher.ui.VideoDownloaderFragment;
import com.glauncher.launcher.ui.WebBrowserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager adapter for managing the 8 tool fragments
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        // Initialize with all 8 tool fragments
        setupFragments();
    }

    private void setupFragments() {
        // Add all 8 fragments in the order they should appear
        fragmentList.add(new LauncherFragment());
        fragmentTitleList.add("Launcher");

        fragmentList.add(new FileManagerFragment());
        fragmentTitleList.add("File Manager");

        fragmentList.add(new WebBrowserFragment());
        fragmentTitleList.add("Web Browser");

        fragmentList.add(new CalculatorFragment());
        fragmentTitleList.add("Calculator");

        fragmentList.add(new NotepadFragment());
        fragmentTitleList.add("Notepad");

        fragmentList.add(new MusicPlayerFragment());
        fragmentTitleList.add("Music Player");

        fragmentList.add(new PhoneContactsFragment());
        fragmentTitleList.add("Phone & Contacts");

        fragmentList.add(new VideoDownloaderFragment());
        fragmentTitleList.add("Video Downloader");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
