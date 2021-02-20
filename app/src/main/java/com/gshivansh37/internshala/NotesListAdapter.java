package com.gshivansh37.internshala;

import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder> {

    private ArrayList<Note> notes;

    NotesListAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_note, parent, false);
        return new ViewHolder(inflatedView);
    }


    @Override
    public void onBindViewHolder(@NonNull NotesListAdapter.ViewHolder holder, int position) {
        holder.noteTitle.setText(notes.get(position).getTitle());
        holder.noteData.setText(notes.get(position).getNoteData());
    }


    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView noteTitle, noteData;

        public ViewHolder(View view) {
            super(view);
            noteTitle = view.findViewById(R.id.et_title);
            noteData = view.findViewById(R.id.et_note);
        }

    }


}
