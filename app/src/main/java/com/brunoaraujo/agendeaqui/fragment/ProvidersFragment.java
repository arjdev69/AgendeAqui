package com.brunoaraujo.agendeaqui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brunoaraujo.agendeaqui.R;
import com.brunoaraujo.agendeaqui.adapter.AdapterProviders;
import com.brunoaraujo.agendeaqui.model.Provider;
import com.brunoaraujo.agendeaqui.service.ProviderService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProvidersFragment extends Fragment {

    private List<Provider> providersList = new ArrayList<>();;
    private RecyclerView recyclerView;

    private Retrofit retrofit;
    private ProviderService providerService;

    private String token = "";

    private AdapterProviders adpProviders;

    public ProvidersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_providers, container, false);

        this.getUserInfo(container.getContext());
        this.StartService();
        this.getProvidersList(container.getContext());

        recyclerView = view.findViewById(R.id.listViewSchedules);

        adpProviders = new AdapterProviders(container.getContext(), providersList);

        //Setup recyclerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adpProviders);

        return view;
    }

    // Services
    public void StartService(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10:3333")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        providerService = retrofit.create(ProviderService.class);
    }

    public void getProvidersList(Context cont){
        Call<List<Provider>> call = providerService.getProviderList("Bearer " + token);

        call.enqueue(new Callback<List<Provider>>() {
            @Override
            public void onResponse(Call<List<Provider>> call, Response<List<Provider>> response) {
                if(response.isSuccessful()){
                    providersList = response.body();
                    adpProviders.callData(providersList);
                    Toast.makeText( cont,"Instituições listadas", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d("Resp: ", " -> " + response.message());
                    Toast.makeText(cont," - ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Provider>> call, Throwable t) {
                Toast.makeText(cont,"Falha na requisição", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Actions
    public void getUserInfo(Context cont){
        SharedPreferences preferences = cont.getSharedPreferences("com.brunoaraujo.agendeaqui", Context.MODE_PRIVATE);
        token = preferences.getString("sessionToken", "");
    }
}