package com.glauncher.launcher.utils;

import android.content.Context;
import android.webkit.MimeTypeMap;
import androidx.annotation.NonNull;

import java.io.File;

/**
 * Utility class for file operations
 * Supports the File Manager tool functionality
 */
public class FileUtils {

    /**
     * Get file extension from filename
     */
    public static String getFileExtension(String filename) {
        if (filename == null) {
            return "";
        }
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return filename.substring(lastDotIndex + 1);
    }

    /**
     * Get MIME type from file extension
     */
    public static String getMimeType(String extension) {
        if (extension == null) {
            return "*/*";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
    }

    /**
     * Check if file is an image
     */
    public static boolean isImageFile(String filename) {
        String ext = getFileExtension(filename).toLowerCase();
        return ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || 
               ext.equals("gif") || ext.equals("bmp") || ext.equals("webp");
    }

    /**
     * Check if file is a video
     */
    public static boolean isVideoFile(String filename) {
        String ext = getFileExtension(filename).toLowerCase();
        return ext.equals("mp4") || ext.equals("avi") || ext.equals("mkv") || 
               ext.equals("mov") || ext.equals("wmv") || ext.equals("flv") ||
               ext.equals("webm") || ext.equals("3gp");
    }

    /**
     * Check if file is an audio file
     */
    public static boolean isAudioFile(String filename) {
        String ext = getFileExtension(filename).toLowerCase();
        return ext.equals("mp3") || ext.equals("wav") || ext.equals("flac") || 
               ext.equals("aac") || ext.equals("ogg") || ext.equals("m4a");
    }

    /**
     * Get human-readable file size
     */
    @NonNull
    public static String getReadableFileSize(long size) {
        if (size <= 0) {
            return "0 Bytes";
        }
        final String[] units = {"Bytes", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return String.format("%.2f %s", size / Math.pow(1024, digitGroups), units[digitGroups]);
    }
}
