package com.example.roma.todo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roma.todo.R;
import com.example.roma.todo.adapter.RemindListAdapter;
import com.example.roma.todo.dto.RemindDbHelper;



public class IdeasFragment extends AbstractTabFragment {
    private static final int LAYOUT = R.layout.fragment_contents;
    //private View view;


    public static IdeasFragment getInstance() {
        Bundle args = new Bundle();
        IdeasFragment fragment = new IdeasFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(LAYOUT, container, false);
        dbHelper = new RemindDbHelper(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        recyclerView.setAdapter(new RemindListAdapter(dbHelper.getSomeTypeReminds(getContext().getString(R.string.tab_item_ideas))));
        return view;
    }


    @Override
    public void onResume() {
        dbHelper = new RemindDbHelper(getContext());
        recyclerView.setAdapter(new RemindListAdapter(dbHelper.getSomeTypeReminds(getContext().getString(R.string.tab_item_ideas))));
        super.onResume();
    }
}
