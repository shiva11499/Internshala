package com.gshivansh37.internshala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddNote.onSomeEventListener, UpdateNote.UpdateEventListener {


    private NotesListAdapter notesListAdapter;
    private ArrayList<Note> notes = new ArrayList<>();
    Button add_note;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_note = findViewById(R.id.add_note);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        notesListAdapter = new NotesListAdapter(notes);
        recyclerView.setAdapter(notesListAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                String title = notes.get(position).getTitle();
                String noteData = notes.get(position).getNoteData();
                notes.remove(position);
                notesListAdapter.notifyItemRemoved(position);
                add_note.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.addnote_frag, new UpdateNote(title, noteData, position));
                ft.addToBackStack(null);
                ft.commit();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();
            }
        }));


        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_note.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                ft.replace(R.id.addnote_frag, new AddNote());
                ft.commit();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        add_note.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        add_note.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void someEvent(String title, String info) {
        Log.d("Mera message", title);
        Log.d("Mera info", info);
        Fragment frag1 = getFragmentManager().findFragmentById(R.id.addnote_frag);
        Note n = new Note(title, info);
        if(!title.matches("Title") && !info.matches("Note")) {
            notes.add(n);
        }
        add_note.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        notesListAdapter.notifyDataSetChanged();
        getSupportActionBar().setTitle("Notes");

    }


    @Override
    public void updateEvent(String title, String info, int pos) {
        Note n = new Note(title, info);
        notes.add(pos,n);
        add_note.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        notesListAdapter.notifyDataSetChanged();
        getSupportActionBar().setTitle("Notes");

    }

    @Override
    public void deleteNote(int pos) {
//        notes.remove(pos);
        add_note.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        notesListAdapter.notifyDataSetChanged();
    }

    @Override
    public void backNote(String title, String info, int pos) {
        Note n = new Note(title, info);
        notes.add(pos,n);
        add_note.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        notesListAdapter.notifyDataSetChanged();

    }


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}