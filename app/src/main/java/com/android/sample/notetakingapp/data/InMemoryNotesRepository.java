package com.android.sample.notetakingapp.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load notes from the a data source.
 */
public class InMemoryNotesRepository implements NotesRepository {

    private final NotesServiceApi mNotesServiceApi;

    /**
     * This method has reduced visibility for testing and is only visible to tests in the same
     * package.
     */
    @VisibleForTesting
    List<Note> mCachedNotes;

    public InMemoryNotesRepository(@NonNull NotesServiceApi notesServiceApi) {
        mNotesServiceApi = checkNotNull(notesServiceApi);
    }

    @Override
    public void getNotes(@NonNull final LoadNotesCallback callback) {
        checkNotNull(callback);

        if (mCachedNotes == null) {
            mNotesServiceApi.getAllNotes(new NotesServiceApi.NotesServiceCallback<List<Note>>() {
                @Override
                public void onLoaded(List<Note> notes) {
                    mCachedNotes = ImmutableList.copyOf(notes);
                    callback.onNotesLoaded(mCachedNotes);
                }
            });
        } else {
            callback.onNotesLoaded(mCachedNotes);
        }
    }

    @Override
    public void getNote(@NonNull String noteId, @NonNull final GetNoteCallback callback) {
        checkNotNull(noteId);
        checkNotNull(callback);

        mNotesServiceApi.getNote(noteId, new NotesServiceApi.NotesServiceCallback<Note>() {
            @Override
            public void onLoaded(Note notes) {
                callback.onNoteLoaded(notes);
            }
        });
    }

    @Override
    public void saveNote(@NonNull Note note) {
        checkNotNull(note);
        mNotesServiceApi.saveNote(note);
        refreshData();
    }

    @Override
    public void refreshData() {
        mCachedNotes = null;
    }
}
