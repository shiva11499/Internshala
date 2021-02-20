package com.gshivansh37.internshala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddNote.onSomeEventListener {


    private NotesListAdapter notesListAdapter;
    private ArrayList<Note> notes = new ArrayList<>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add_note = findViewById(R.id.add_note);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        notesListAdapter = new NotesListAdapter(notes);
        recyclerView.setAdapter(notesListAdapter);

//        notesListAdapter.notifyDataSetChanged();




        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void someEvent(String title, String info) {
        Log.d("Mera message", title);
        Log.d("Mera info", info);
        Fragment frag1 = getFragmentManager().findFragmentById(R.id.addnote_frag);
        Note n = new Note(title,info);
        notes.add(n);
        recyclerView.setVisibility(View.VISIBLE);
        notesListAdapter.notifyDataSetChanged();



    }

//    @Override
//    public void someEvent(String s) {
//        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
//        ((TextView)frag1.getView().findViewById(R.id.textView)).setText("Text from Fragment 2:" + s);
//    }
}