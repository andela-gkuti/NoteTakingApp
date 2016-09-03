package com.android.sample.notetakingapp.data;

import android.support.v4.util.ArrayMap;

/**
 * This is the endpoint for your data source. Typically, it would be a SQLite db and/or a server
 * API. In this example, we fake this by creating the data on the fly.
 */
public class NotesServiceApiEndpoint {
    private final static ArrayMap<String, Note> DATA;

    static {
        DATA = new ArrayMap(2);
        addNote("Oh yes!", "I demand trial by Unit testing", null);
        addNote("Espresso", "UI Testing for Android", null);
    }

    private static void addNote(String title, String description, String imageUrl) {
        Note newNote = new Note(title, description, imageUrl);
        DATA.put(newNote.getId(), newNote);
    }

    /**
     * @return the Notes to show when starting the app.
     */
    public static ArrayMap<String, Note> loadPersistedNotes() {
        return DATA;
    }
}
