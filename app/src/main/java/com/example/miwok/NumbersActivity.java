package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

public class NumbersActivity extends AppCompatActivity {
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_numbers);
        adapter = new RecyclerViewAdapter(this, R.color.colorTextViewNumbers, DummyDate.miwokWordNumbers, DummyDate.defaultWordNumbers, DummyDate.audioNumbers, DummyDate.imageNumbers);
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
