package com.android.sample.notetakingapp.data;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by mayowa.adegeye on 28/08/2016.
 */
public class NoteRepositories {

    private NoteRepositories() {}

    private static NotesRepository repository = null;

    public synchronized static NotesRepository getInMemoryRepoInstance(@NonNull NotesServiceApi notesServiceApi) {
        checkNotNull(notesServiceApi);
        if (null == repository) {
            repository = new InMemoryNotesRepository(notesServiceApi);
        }
        return repository;
    }
}
