package com.android.sample.notetakingapp.data;

import android.os.Handler;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Notes Service API that adds a latency simulating network.
 */
public class NotesServiceApiImpl implements NotesServiceApi {
    private static final int SERVICE_LATENCY_IN_MILLIS = 2000;
    private static final ArrayMap<String, Note> NOTES_SERVICE_DATA =
            NotesServiceApiEndpoint.loadPersistedNotes();

    @Override
    public void getNote(String noteId, NotesServiceCallback<Note> callback) {
        //TODO: Add network latency here too.
        Note note = NOTES_SERVICE_DATA.get(noteId);
        callback.onLoaded(note);
    }

    @Override
    public void getAllNotes(final NotesServiceCallback<List<Note>> callback) {
        // Simulate network by delaying the execution.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Note> notes = new ArrayList<>(NOTES_SERVICE_DATA.values());
                callback.onLoaded(notes);
            }
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    @Override
    public void saveNote(Note note) {
        NOTES_SERVICE_DATA.put(note.getId(), note);
    }
}
