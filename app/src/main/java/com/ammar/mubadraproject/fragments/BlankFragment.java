package com.ammar.mubadraproject.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ammar.mubadraproject.Adapter.NoteAdapter;
import com.ammar.mubadraproject.Note;
import com.ammar.mubadraproject.ProfileDetailsActivity;
import com.ammar.mubadraproject.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;




public class BlankFragment extends Fragment implements NoteAdapter.OnItemClickListener {
    Context thiscontext;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Notebook");
    private NoteAdapter adapter;

    private static final String TAG = "NewsFragment";
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();


     Fragment NewsFragment;
    View view;
    ImageView imageView;
    public ProgressBar prgress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        Query query = notebookRef.orderBy("priority", Query.Direction.ASCENDING);



        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();
        adapter = new NoteAdapter(options);
        RecyclerView recyclerView = view.findViewById(R.id.mainrecycleee);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

        Note note = documentSnapshot.toObject(Note.class);

        Log.d(TAG, "onItemClick: " + note.getTitle());

        Intent intent = new Intent( getContext(), ProfileDetailsActivity.class);
        this.startActivity(intent);


     }
}