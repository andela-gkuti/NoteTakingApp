package com.android.sample.notetakingapp.notedetails;


import android.support.annotation.Nullable;

public interface NoteDetailsContract {

    interface View {
        void setProgressIndicator(boolean active);

        void showMissingNote();

        void hideTitle();

        void showTitle(String title);

        void showImage(String imageUrl);

        void hideImage();

        void hideDescription();

        void showDescription(String description);
    }

    interface UserActionsListener {
        void openNote(@Nullable String noteId);
    }
}
