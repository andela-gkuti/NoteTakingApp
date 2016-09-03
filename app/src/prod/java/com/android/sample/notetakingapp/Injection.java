/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.sample.notetakingapp;


import com.android.sample.notetakingapp.data.NoteRepositories;
import com.android.sample.notetakingapp.data.NotesRepository;
import com.android.sample.notetakingapp.data.NotesServiceApiImpl;
import com.android.sample.notetakingapp.util.ImageFile;
import com.android.sample.notetakingapp.util.ImageFileImpl;

/**
 * Enables injection of production implementations for {@link ImageFile} and
 * {@link NotesRepository} at compile time.
 */
public class Injection {

    public static ImageFile provideImageFile() {
        return new ImageFileImpl();
    }

    public static NotesRepository provideNotesRepository() {
        return NoteRepositories.getInMemoryRepoInstance(new NotesServiceApiImpl());
    }
}
