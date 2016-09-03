package com.android.sample.notetakingapp.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.sample.notetakingapp.Injection;
import com.android.sample.notetakingapp.R;
import com.android.sample.notetakingapp.addnote.AddNoteActivity;
import com.android.sample.notetakingapp.data.Note;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Displays a grid of notes
 */
public class NotesFragment extends Fragment implements NotesContract.View {
    private static final int REQUEST_ADD_NOTE = 1;

    private NotesContract.UserActionsListener mUserActionListener;

    private NotesAdapter mListAdapter;

    /**
     * Listener for clicks on notes in the RecyclerView.
     */
    NoteItemListener mItemListener = new NoteItemListener() {
        @Override
        public void onNoteClick(Note clickedNote) {
            mUserActionListener.openNoteDetails(clickedNote);
        }
    };

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new NotesAdapter(new ArrayList<Note>(0), mItemListener);
        mUserActionListener = new NotesPresenter(Injection.provideNotesRepository(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionListener.loadNotes(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // If a note was successfully added, show snackbar
        if (REQUEST_ADD_NOTE == requestCode && Activity.RESULT_OK == resultCode) {
            Snackbar.make(getView(), getString(R.string.successfully_saved_note_message),
                    Snackbar.LENGTH_SHORT).show();
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView(view);

        // Set up floating action button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_notes);

        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserActionListener.addNewNote();
            }
        });

        // Pull-to-refresh
        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mUserActionListener.loadNotes(true);
            }
        });
    }

    @Override
    public void setProgressIndicator(final boolean active) {
        if (getView() == null) {
            return ;
        }

        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showNotes(List<Note> notes) {

    }

    @Override
    public void showAddNote() {

    }

    @Override
    public void showNoteDetailUi(String noteId) {

    }

    private void setupRecyclerView(View root) {
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.notes_list);
        recyclerView.setAdapter(mListAdapter);

        int numColumns = getContext().getResources().getInteger(R.integer.num_notes_columns);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));
    }

    private static class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
        private List<Note> mNotes;
        private NoteItemListener mItemListener;

        public NotesAdapter(List<Note> notes, NoteItemListener itemListener) {
            setList(notes);
            this.mItemListener = itemListener;
        }

        @Override
        public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.item_note, parent, false);

            return new ViewHolder(noteView, mItemListener);
        }

        @Override
        public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
            Note note = mNotes.get(position);

            holder.title.setText(note.getTitle());
            holder.description.setText(note.getDescription());
        }

        public void replaceData(List<Note> notes) {
            setList(notes);
            notifyDataSetChanged();
        }

        private void setList(List<Note> notes) {
            mNotes = checkNotNull(notes);
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }

        public Note getItem(int position) {
            return mNotes.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView title;
            public TextView description;

            private NoteItemListener itemListener;

            public ViewHolder (View itemView, NoteItemListener itemListener) {
                super(itemView);
                this.itemListener = itemListener;
                title = (TextView) itemView.findViewById(R.id.note_detail_title);
                description = (TextView) itemView.findViewById(R.id.note_detail_description);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Note note = getItem(position);
                mItemListener.onNoteClick(note);
            }
        }
    }

    public interface NoteItemListener {

        void onNoteClick(Note clickedNote);
    }
}
