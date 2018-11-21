package com.nissar.nytestapp;

import android.app.Application;

import com.nissar.nytestapp.network.AppComponent;
import com.nissar.nytestapp.network.AppModule;
import com.nissar.nytestapp.network.DaggerAppComponent;
import com.nissar.nytestapp.network.NetWorkModule;


public class NewYorkTimesApplication  extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netWorkModule(new NetWorkModule())
                .build();

    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}