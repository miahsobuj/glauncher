package com.glauncher.launcher.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.glauncher.launcher.models.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for managing notes
 * Supports the Notepad tool functionality
 */
public class NoteManager {

    private static final String PREFS_NAME = "NotesPrefs";
    private static final String KEY_NOTES = "notes";

    private final SharedPreferences preferences;

    public NoteManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Save a note
     */
    public void saveNote(Note note) {
        List<Note> notes = getAllNotes();
        // Remove existing note with same ID if present
        notes.removeIf(n -> n.getId() == note.getId());
        notes.add(note);
        // Save to SharedPreferences (simplified implementation)
        // In a real app, you'd use a proper database like Room
    }

    /**
     * Get all notes
     */
    public List<Note> getAllNotes() {
        // Simplified implementation - in reality you'd parse from SharedPreferences
        // or use a proper database
        return new ArrayList<>();
    }

    /**
     * Delete a note
     */
    public void deleteNote(int noteId) {
        List<Note> notes = getAllNotes();
        notes.removeIf(n -> n.getId() == noteId);
        // Save updated list
    }

    /**
     * Update a note
     */
    public void updateNote(Note note) {
        deleteNote(note.getId());
        saveNote(note);
    }
}
