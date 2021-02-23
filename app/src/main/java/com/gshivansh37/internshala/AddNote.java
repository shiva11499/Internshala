package com.gshivansh37.internshala;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNote#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNote extends Fragment {

    int REQUEST_CODE = 101;

    public interface onSomeEventListener {
        public void someEvent(String title, String info);
    }

    onSomeEventListener someEventListener;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            someEventListener = (onSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    public AddNote() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddNote.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNote newInstance() {
        AddNote fragment = new AddNote();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Add Note");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_add_note, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView save_note = getView().findViewById(R.id.save_note);
        final EditText et_note = getView().findViewById(R.id.et_note);
        final EditText et_title = getView().findViewById(R.id.et_title);


        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someEventListener.someEvent(et_title.getText().toString(), et_note.getText().toString());
                getFragmentManager().beginTransaction().remove(AddNote.this).commit();

//                DisplayNotes ldf = new DisplayNotes();
//                Bundle args = new Bundle();
//                args.putString("Title", et_title.getText().toString());
//                args.putString("Note", et_note.getText().toString());
//                ldf.setArguments(args);
//
////Inflate the fragment
//                getFragmentManager().beginTransaction().replace(R.id.addnote_frag, ldf).commit();
//
//                getFragmentManager().popBackStackImmediate();
            }
        });


    }


}