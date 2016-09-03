package com.android.sample.notetakingapp.notedetails;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.sample.notetakingapp.data.Note;
import com.android.sample.notetakingapp.data.NotesRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link NoteDetailsFragment}), retrieves the data and updates
 * the UI as required.
 */
public class NoteDetailsPresenter implements NoteDetailsContract.UserActionsListener {

    private final NotesRepository mNotesRepository;

    private final NoteDetailsContract.View mNotesDetailView;

    public NoteDetailsPresenter(@NonNull NotesRepository notesRepository,
                                @NonNull NoteDetailsContract.View noteDetailView) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null!");
        mNotesDetailView = checkNotNull(noteDetailView, "noteDetailView cannot be null!");
    }


    @Override
    public void openNote(@Nullable String noteId) {

    }

    private void showNote(Note note) {

    }
}
