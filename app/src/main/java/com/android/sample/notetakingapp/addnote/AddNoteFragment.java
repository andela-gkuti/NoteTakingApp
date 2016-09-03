package com.android.sample.notetakingapp.addnote;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.sample.notetakingapp.Injection;
import com.android.sample.notetakingapp.R;
import com.android.sample.notetakingapp.util.EspressoIdlingResource;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkState;

/**
 * Main UI for the add note screen. Users can enter a note title and description. Images can be
 * added to notes by clicking on the options menu.
 */
public class AddNoteFragment extends Fragment implements AddNoteContract.View {

    public static final int REQUEST_CODE_IMAGE_CAPTURE = 0x1001;

    private AddNoteContract.UserActionsListener mActionListener;

    private TextView mTitle;

    private TextView mDescription;

    private ImageView mImageThumbnail;

    public static AddNoteFragment newInstance() {
        return new AddNoteFragment();
    }

    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionListener = new AddNotePresenter(Injection.provideNotesRepository(), this,
                Injection.provideImageFile());

        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_notes);
        fab.setImageResource(R.drawable.ic_done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActionListener.saveNote(mTitle.getText().toString(),
                        mDescription.getText().toString());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_note, container, false);
        mTitle = (TextView) root.findViewById(R.id.add_note_title);
        mDescription = (TextView) root.findViewById(R.id.add_note_description);
        mImageThumbnail = (ImageView) root.findViewById(R.id.add_note_image_thumbnail);

        setHasOptionsMenu(true);
        setRetainInstance(true);
        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.take_picture:
                try {
                    mActionListener.takePicture();
                } catch (IOException ioe) {
                    if (getView() != null) {
                        Snackbar.make(getView(), getString(R.string.take_picture_error),
                                Snackbar.LENGTH_LONG).show();
                    }
                }
                return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_add_note_options_menu_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void showEmptyNoteError() {

    }

    @Override
    public void showNotesList() {

    }

    @Override
    public void openCamera(String saveTo) {

    }

    @Override
    public void showImagePreview(@NonNull String imageUrl) {

    }

    @Override
    public void showImageError() {

    }


}
