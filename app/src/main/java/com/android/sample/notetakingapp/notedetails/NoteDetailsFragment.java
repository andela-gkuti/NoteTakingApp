package com.android.sample.notetakingapp.notedetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.sample.notetakingapp.Injection;
import com.android.sample.notetakingapp.R;

/**
 * Created by mayowa.adegeye on 28/08/2016.
 */
public class NoteDetailsFragment extends Fragment implements NoteDetailsContract.View {

    public static final String ARGUMENT_NOTE_ID = "NOTE_ID";

    private NoteDetailsContract.UserActionsListener mActionsListener;

    private TextView mDetailTitle;

    private TextView mDetailDescription;

    private ImageView mDetailImage;

    public static NoteDetailsFragment newInstance(String noteId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_NOTE_ID, noteId);
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionsListener = new NoteDetailsPresenter(Injection.provideNotesRepository(),
                this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        mDetailTitle = (TextView) root.findViewById(R.id.note_detail_title);
        mDetailDescription = (TextView) root.findViewById(R.id.note_detail_description);
        mDetailImage = (ImageView) root.findViewById(R.id.note_detail_image);
    }

    @Override
    public void onResume() {
        super.onResume();
        String noteId = getArguments().getString(ARGUMENT_NOTE_ID);
        mActionsListener.openNote(noteId);
    }

    @Override
    public void setProgressIndicator(boolean active) {

    }

    @Override
    public void showMissingNote() {

    }

    @Override
    public void hideTitle() {

    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showImage(String imageUrl) {

    }

    @Override
    public void hideImage() {

    }

    @Override
    public void hideDescription() {

    }

    @Override
    public void showDescription(String description) {

    }
}
