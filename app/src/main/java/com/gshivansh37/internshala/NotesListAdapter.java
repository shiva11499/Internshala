package com.gshivansh37.internshala;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    Onclick onclick;
    Context context;


    NotesListAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public interface Onclick {
        void onEvent(Note notes,int pos);
    }

    public NotesListAdapter(Context context, ArrayList<Note> notes, Onclick onclick) {
        this.context = context;
        this.notes = notes;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public NotesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_note, parent, false);
        return new ViewHolder(inflatedView);
    }


    @Override
    public void onBindViewHolder(@NonNull NotesListAdapter.ViewHolder holder, final int position) {
        holder.noteTitle.setText(notes.get(position).getTitle());
        holder.noteData.setText(notes.get(position).getNoteData());

//        holder.removeImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                notes.remove(position);
//                notifyDataSetChanged();
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    public void updateList(ArrayList<Note> itemList){
        notes.clear();
//        this.list = itemList;
//        list.addAll(itemList);
//        notifyItemInserted(list.size());
//        notifyDataSetChanged();
        this.notes.addAll(itemList);
//        notifyItemInserted(list.size());
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView noteTitle, noteData;
        ImageView  removeImg;

        public ViewHolder(View view) {
            super(view);
            noteTitle = view.findViewById(R.id.frag_et_title);
            noteData = view.findViewById(R.id.frag_et_note);
//            removeImg = itemView.findViewById(R.id.image_cross);

        }

        @Override
        public void onClick(View v) {

            }
        }

    }




