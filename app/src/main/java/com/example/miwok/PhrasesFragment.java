package com.example.miwok;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {
    private RecyclerViewAdapter adapter;

    public PhrasesFragment() {
        // Required empty public constructor
    }

    public static PhrasesFragment getInstance() {
        return new PhrasesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_phrases, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_phrases);
        adapter = new RecyclerViewAdapter(getActivity(), R.color.colorTextViewPhrases, DummyDate.miwokWordPhrases, DummyDate.defaultWordPhrases, DummyDate.audioPhrases);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.releaseMediaPlayer();
    }
}
