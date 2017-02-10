package com.example.agsva_000.proyectointegrador2dam;


import android.content.Context;
        import android.database.DataSetObserver;
        import android.nfc.Tag;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import org.w3c.dom.Comment;

        import java.util.ArrayList;
        import java.util.Iterator;

public class Tab2BuscarViaje extends Fragment {
    ListView listView;
    ArrayList<Travel> travels = new ArrayList<>();
    public static final String TAG = Tab2BuscarViaje.class.getSimpleName();
    FirebaseDatabase firebase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebase.getReference("travels");
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("travels");
    Travel travel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2buscarviaje, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> childrenIterator = dataSnapshot.getChildren().iterator();
                while (childrenIterator.hasNext()) {
                    travel = childrenIterator.next().getValue(Travel.class);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };
        reference.addValueEventListener(valueEventListener);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Travel travel = dataSnapshot.getValue(Travel.class);
                travels.add(travel);
                Log.d(TAG, " onChildAdded: " +dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "onChildChanged: " +dataSnapshot.getKey());
                Comment newComment=dataSnapshot.getValue(Comment.class);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, " onChildRemoved: " + dataSnapshot.getKey());
                String commentKey = dataSnapshot.getKey();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, " onChildMoved: " + dataSnapshot.getKey());
                Comment movedComment = dataSnapshot.getValue(Comment.class);
                String commentKey = dataSnapshot.getKey();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, " postComments:onCancelled ", databaseError.toException());
                Toast.makeText(getContext(), "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.addChildEventListener(childEventListener);
        TravelAdapter travelAdapter=new TravelAdapter(getContext(),travels);
        listView.setAdapter(travelAdapter);
    }
}