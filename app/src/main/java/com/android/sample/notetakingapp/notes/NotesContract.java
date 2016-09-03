package com.android.sample.notetakingapp.notes;

import android.support.annotation.NonNull;

import com.android.sample.notetakingapp.data.Note;

import java.util.List;

/**
 * Specifies the contract between the notes view and the presenter
 */
public interface NotesContract {

    interface View {
        void setProgressIndicator(boolean active);

        void showNotes(List<Note> notes);

        void showAddNote();

        void showNoteDetailUi(String noteId);
    }

    interface UserActionsListener {
        void loadNotes(boolean forceUpdates);

        void addNewNote();

        void openNoteDetails(@NonNull Note requestedNote);
    }
}
