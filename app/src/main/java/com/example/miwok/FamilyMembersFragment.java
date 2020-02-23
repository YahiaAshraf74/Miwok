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
public class FamilyMembersFragment extends Fragment {
    private RecyclerViewAdapter adapter;

    public FamilyMembersFragment() {
        // Required empty public constructor
    }

    public static FamilyMembersFragment getInstance() {
        return new FamilyMembersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_family_members, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_family_members);
        adapter = new RecyclerViewAdapter(getActivity(), R.color.colorTextViewFamilyMembers, DummyDate.miwokWordFamilyMembers, DummyDate.defaultWordFamilyMembers, DummyDate.audioFamilyMembers, DummyDate.imageFamilyMembers);
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
