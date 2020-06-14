package com.example.marinero_kj.persistance.realtime;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class FirebaseQuery extends LiveData<DataSnapshot> {
    private static final String LOG_TAG = "FirebaseQueryLiveData";

    private final Query query;
    private final MyValueEventListener listener = new MyValueEventListener();

    //objasni zasto ovo, clanak
    private boolean listenerRemovePending =false;
    private final Handler handler= new Handler();
    private final Runnable removeListener= new Runnable() {
        @Override
        public void run() {
            query.removeEventListener(listener);
            listenerRemovePending =false;
        }
    };


    public FirebaseQuery(Query query) {
        this.query = query;
    }

    public FirebaseQuery(DatabaseReference ref) {
        this.query = ref;
    }

    @Override
    protected void onActive() {
        Log.d(LOG_TAG, "onActive");
        if(listenerRemovePending)
            handler.removeCallbacks(removeListener);
        else query.addValueEventListener(listener);
        listenerRemovePending=false;
    }

    @Override
    protected void onInactive() {
        Log.d(LOG_TAG, "onInactive");
        handler.postDelayed(removeListener, 2000);
        listenerRemovePending=true;
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e(LOG_TAG, "Can't listen to query " + query, databaseError.toException());
        }
    }
}
