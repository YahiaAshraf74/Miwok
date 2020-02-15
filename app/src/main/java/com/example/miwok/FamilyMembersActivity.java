package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class FamilyMembersActivity extends AppCompatActivity {
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_family_members);
        adapter = new RecyclerViewAdapter(this, R.color.colorTextViewFamilyMembers, DummyDate.miwokWordFamilyMembers, DummyDate.defaultWordFamilyMembers, DummyDate.audioFamilyMembers, DummyDate.imageFamilyMembers);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.releaseMediaPlayer();
    }
}
