package com.nissar.nytestapp.network;



import com.nissar.nytestapp.activity.MainActivity;
import com.nissar.nytestapp.fragment.ArticlesFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetWorkModule.class, AppModule.class})
public interface AppComponent {

//    void inject(UploadImageFragment uploadImageFragment);
    void inject(MainActivity mainActivity);
    void inject(ArticlesFragment articlesFragment);
}
