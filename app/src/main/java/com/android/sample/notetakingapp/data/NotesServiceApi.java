package com.android.sample.notetakingapp.data;

import java.util.List;

/**
 * Defines an interface to the service API that is used by this application. All data request should
 * be piped through this interface.
 */
public interface NotesServiceApi {

    interface NotesServiceCallback<T> {

        void onLoaded(T notes);
    }

    void getAllNotes(NotesServiceCallback<List<Note>> callback);

    void getNote(String noteId, NotesServiceCallback<Note> callback);

    void saveNote(Note note);
}
