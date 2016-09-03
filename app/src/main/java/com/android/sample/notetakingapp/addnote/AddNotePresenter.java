package com.android.sample.notetakingapp.addnote;

import android.support.annotation.NonNull;

import com.android.sample.notetakingapp.data.Note;
import com.android.sample.notetakingapp.data.NotesRepository;
import com.android.sample.notetakingapp.util.ImageFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Listens to user actions from the UI ({@link AddNoteFragment}), retrieves the data and updates
 * the UI as required.
 */
public class AddNotePresenter  implements AddNoteContract.UserActionsListener {
    @NonNull
    private final NotesRepository mNotesRepository;
    @NonNull
    private final AddNoteContract.View mAddNoteView;
    @NonNull
    private final ImageFile mImageFile;

    public AddNotePresenter(@NonNull NotesRepository notesRepository,
                            @NonNull AddNoteContract.View addNoteView,
                            @NonNull ImageFile imageFile) {
        mNotesRepository = checkNotNull(notesRepository);
        mAddNoteView = checkNotNull(addNoteView);
        mImageFile = imageFile;
    }

    @Override
    public void saveNote(String title, String description) {

    }

    @Override
    public void takePicture() throws IOException {

    }

    @Override
    public void imageAvailable() {

    }

    @Override
    public void imageCaptureFailed() {

    }

    private void captureFailed() {
        mImageFile.delete();
        mAddNoteView.showImageError();
    }
}
