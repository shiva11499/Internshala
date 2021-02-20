package com.gshivansh37.internshala;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add_note = findViewById(R.id.add_note);
        final ConstraintLayout am = findViewById(R.id.main_act);
        add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                am.setVisibility(View.GONE);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
                ft.replace(R.id.addnote_frag, new AddNote());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
                ft.commit();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}