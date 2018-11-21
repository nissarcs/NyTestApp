package com.nissar.nytestapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nissar.nytestapp.NewYorkTimesApplication;
import com.nissar.nytestapp.R;
import com.nissar.nytestapp.activity.MainActivity;
import com.nissar.nytestapp.adapter.ArticlesAdapter;
import com.nissar.nytestapp.model.Response;
import com.nissar.nytestapp.network.NetworkError;
import com.nissar.nytestapp.network.Service;
import com.nissar.nytestapp.utils.NewYorkTimesProgressDialog;

import javax.inject.Inject;

public class ArticlesFragment extends Fragment {

    @Inject
    Service service;
    private NewYorkTimesProgressDialog newYorkTimesProgressDialog;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private NewYorkTimesApplication newYorkTimesApplication;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         /* injecting dependency */
        newYorkTimesApplication = (NewYorkTimesApplication) getActivity().getApplication();
        (newYorkTimesApplication).getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        newYorkTimesProgressDialog = new NewYorkTimesProgressDialog(getActivity());
        newYorkTimesProgressDialog.showDialog();

        service.getBaseURL(new Service.ResponseCallback<Response>() {
            @Override
            public void onSuccess(Response response) {
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);

                mAdapter = new ArticlesAdapter(response, (MainActivity) getActivity(), getFragmentManager());
                recyclerView.setAdapter(mAdapter);
                newYorkTimesProgressDialog.dismissDialog();
            }

            @Override
            public void onError(NetworkError networkError) {
                newYorkTimesProgressDialog.dismiss();
                Toast.makeText(getActivity(), " Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
