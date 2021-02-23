package com.gshivansh37.internshala;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class DisplayNotes extends Fragment  {

    private NotesListAdapter notesListAdapter;
    public static ArrayList<Note> notes = new ArrayList<>();
    Button add_note;
    RecyclerView recyclerView;

    int REQUEST_CODE = 101;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DisplayNotes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayNotes.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayNotes newInstance() {
        DisplayNotes fragment = new DisplayNotes();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_notes, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle b = this.getArguments();
        if(b != null) {
            String title = getArguments().getString("Title");
            String info = getArguments().getString("Note");
            Note n = new Note(title, info);
            Log.d("Mera msg", title);
            notes.add(n);
            notesListAdapter.updateList(notes);
        }

        add_note = getView().findViewById(R.id.add_note);
        recyclerView = getView().findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        notesListAdapter = new NotesListAdapter(notes);
        recyclerView.setAdapter(notesListAdapter);



        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_note.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.addnote_frag, new AddNote());
                ft.commit();

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode== Activity.RESULT_OK) {
            String datafromC = data.getStringExtra("datafrom C");
        }
    }

//    @Override
//    public void someEvent(String title, String info) {
//        Note n = new Note(title, info);
//        notes.add(n);
//        add_note.setVisibility(View.VISIBLE);
//        recyclerView.setVisibility(View.VISIBLE);
//        notesListAdapter.notifyDataSetChanged();
//
//    }
//
//    @Override
//    public void updateEvent(String title, String info, int pos) {
//        Note n = new Note(title, info);
//        notes.add(pos,n);
//        add_note.setVisibility(View.VISIBLE);
//        recyclerView.setVisibility(View.VISIBLE);
//        notesListAdapter.notifyDataSetChanged();
//    }
}