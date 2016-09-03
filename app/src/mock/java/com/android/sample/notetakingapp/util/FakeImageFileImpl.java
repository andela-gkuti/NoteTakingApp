package com.android.sample.notetakingapp.util;

import java.io.IOException;

/**
 * Created by mayowa.adegeye on 28/08/2016.
 */
public class FakeImageFileImpl extends ImageFileImpl {
    @Override
    public void create(String name, String extension) throws IOException {
        // Do nothing
    }

    @Override
    public String getPath() {
        return "file:///android_asset/atsl-logo.png";
    }

    @Override
    public boolean exists() {
        return true;
    }
}
