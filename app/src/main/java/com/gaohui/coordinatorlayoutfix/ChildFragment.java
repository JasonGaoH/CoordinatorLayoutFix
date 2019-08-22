package com.gaohui.coordinatorlayoutfix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ChildFragment extends Fragment {

    private ArrayList<String> mDataList = new ArrayList<String>();

    RecyclerView childRecyclerView;

    public ChildFragment() {
        // Required empty public constructor
    }

    public static ChildFragment newInstance() {
        ChildFragment fragment = new ChildFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);childRecyclerView = view.findViewById(R.id.child_recycler_view);
       childRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
       childRecyclerView.setAdapter(new RecyclerViewAdapter(mDataList));

        for (int i =0;i<50;i++) {
            mDataList.add("child: " + i);
        }

        childRecyclerView.getAdapter().notifyDataSetChanged();

        childRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }


    public void stopNestedScrolling() {
        childRecyclerView.stopScroll();
    }
}
