package com.android.sample.notetakingapp;

import com.android.sample.notetakingapp.data.NoteRepositories;
import com.android.sample.notetakingapp.data.NotesRepository;
import com.android.sample.notetakingapp.data.FakeNotesServiceApiImpl;
import com.android.sample.notetakingapp.util.FakeImageFileImpl;
import com.android.sample.notetakingapp.util.ImageFile;

/**
 * Enables injection of mock implementations for {@link ImageFile} and
 * {@link NotesRepository} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */

public class Injection {
    public static ImageFile provideImageFile() {
        return new FakeImageFileImpl();
    }

    public static NotesRepository provideNotesRepository() {
        return NoteRepositories.getInMemoryRepoInstance(new FakeNotesServiceApiImpl());
    }
}
