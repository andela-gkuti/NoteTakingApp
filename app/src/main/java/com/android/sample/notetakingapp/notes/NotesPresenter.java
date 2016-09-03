package com.android.sample.notetakingapp.notes;

import android.support.annotation.NonNull;

import com.android.sample.notetakingapp.data.Note;
import com.android.sample.notetakingapp.data.NotesRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link NotesFragment}), retrieves the data and updates the
 * UI as required.
 */

public class NotesPresenter implements NotesContract.UserActionsListener {
    private final NotesRepository mNotesRepository;
    private final NotesContract.View mNotesView;

    public NotesPresenter(
            @NonNull NotesRepository notesRepository, @NonNull NotesContract.View notesView) {
        mNotesRepository = checkNotNull(notesRepository, "notesRepository cannot be null");
        mNotesView = checkNotNull(notesView, "notesView cannot be null!");
    }


    @Override
    public void loadNotes(final boolean forceUpdates) {

    }

    @Override
    public void addNewNote() {

    }

    @Override
    public void openNoteDetails(@NonNull Note requestedNote) {

    }
}
