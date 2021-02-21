package com.gshivansh37.internshala;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateNote#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateNote extends Fragment {

    public interface UpdateEventListener {
        public void updateEvent(String title, String info);
    }

    UpdateNote.UpdateEventListener updateEventListener;

    private String title;
    private String noteData;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            updateEventListener = (UpdateNote.UpdateEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    public UpdateNote(String title, String noteData) {
        // Required empty public constructor
        this.title = title;
        this.noteData = noteData;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateNote.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateNote newInstance(String param1, String param2) {
        UpdateNote fragment = new UpdateNote(param1,param2);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Update Note");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_note, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button save_note = getView().findViewById(R.id.save_note);
        final EditText et_note = getView().findViewById(R.id.et_note);
        final EditText et_title = getView().findViewById(R.id.et_title);

        et_title.setText(title);
        et_note.setText(noteData);

        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEventListener.updateEvent(et_title.getText().toString(), et_note.getText().toString());
                getFragmentManager().beginTransaction().remove(UpdateNote.this).commit();
            }
        });
    }
}