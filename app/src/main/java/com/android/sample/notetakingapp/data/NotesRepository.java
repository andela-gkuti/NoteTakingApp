package com.android.sample.notetakingapp.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Main entry point for accessing notes data.
 */
public interface NotesRepository {
    interface LoadNotesCallback {

        void onNotesLoaded(List<Note> notes);
    }

    interface GetNoteCallback {

        void onNoteLoaded(Note note);
    }

    void getNotes(@NonNull LoadNotesCallback callback);

    void getNote(@NonNull String noteId, @NonNull GetNoteCallback callback);

    void saveNote(@NonNull Note note);

    void refreshData();
}
