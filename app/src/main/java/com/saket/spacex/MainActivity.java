package com.saket.spacex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.transition.Visibility;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.saket.spacex.Dao.CrewDao;
import com.saket.spacex.Database.AppDatabase;
import com.saket.spacex.Remote.Api;
import com.saket.spacex.Repository.CrewRepository;
import com.saket.spacex.ViewModel.CrewViewModel;
import com.saket.spacex.ViewModel.ViewModelFactory;
import com.saket.spacex.adapters.CrewAdapter;
import com.saket.spacex.model.Crew;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CrewAdapter crewAdapter;
    private CrewViewModel crewViewModel;
    private List<Crew> crewList;
    private TextView noData;
    private MaterialToolbar toolbar;
    private ProgressBar progressBar;
    private int state =0;
    Activity activity;
    private String url = "https://api.spacexdata.com/v4/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) ;
        CrewDao dao= AppDatabase.getInstance(getApplicationContext()).crewDao();
        CrewRepository crewRepository= new CrewRepository(dao);
        ViewModelFactory factory = new ViewModelFactory(crewRepository);
        crewViewModel = new ViewModelProvider(this,factory).get(CrewViewModel.class);
        toolbar=findViewById(R.id.toolbar);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);
        noData=findViewById(R.id.txt_noData);
        activity=this;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar.inflateMenu(R.menu.appbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.menuRefresh)
                {
                    progressBar.setVisibility(View.VISIBLE);
                   try {
                        if(isConnected()) {
                            crewViewModel.deleteALl();
                            progressBar.setVisibility(View.VISIBLE);
                            noData.setVisibility(View.GONE);
                            getRemote();
                        }
                        else
                       {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(activity,"No Internet Available",Toast.LENGTH_SHORT).show();
                       }
                   } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
                else if(item.getItemId()==R.id.menuDelete)
                {
                    crewViewModel.deleteALl();
                    noData.setVisibility(View.VISIBLE);
                    state=1;
                }
                return false;
            }
        });

        if(state == 0)
        {
            getRemote();
        }

        crewViewModel.getCrewList().observe(this, new Observer<List<Crew>>() {
            @Override
            public void onChanged(List<Crew> crews) {
                if(crews.size()!=0)
                progressBar.setVisibility(View.GONE);
                else
                    progressBar.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(new CrewAdapter(activity,crews));

            }
        });
    }


    private void getRemote() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<List<Crew>> call = api.getCrewListRetro();
        call.enqueue(new Callback<List<Crew>>() {
            @Override
            public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {
                crewViewModel.insertAll(response.body());
                Log.e("APIRespond",""+response.body());
            }

            @Override
            public void onFailure(Call<List<Crew>> call, Throwable t) {

            }
        });
    }

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }

}