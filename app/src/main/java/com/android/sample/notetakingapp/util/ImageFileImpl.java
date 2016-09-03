package com.android.sample.notetakingapp.util;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.VisibleForTesting;

import java.io.File;
import java.io.IOException;

/**
 * A thin wrapper around Android file APIs to make them more testable and allows the injection of a
 * fake implementation for hermetic UI tests.
 */
public class ImageFileImpl implements ImageFile {
    @VisibleForTesting  File mImageFile;

    @Override
    public void create(String name, String extension) throws IOException {
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        mImageFile = File.createTempFile(
                name,  /* prefix */
                extension,        /* suffix */
                storageDir      /* directory */
        );
    }

    @Override
    public boolean exists() {
        return null != mImageFile && mImageFile.exists();
    }

    @Override
    public void delete() {
        mImageFile = null;
    }

    @Override
    public String getPath() {
        return Uri.fromFile(mImageFile).toString();
    }
}
