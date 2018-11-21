package com.nissar.nytestapp;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nissar.nytestapp.activity.MainActivity;
import com.nissar.nytestapp.adapter.ArticlesAdapter;
import com.nissar.nytestapp.fragment.ArticlesFragment;
import com.nissar.nytestapp.model.Response;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.spy;


@RunWith(PowerMockRunner.class)
@PrepareForTest({Context.class, AssetManager.class, MainActivity.class})
public class ArticlesAdapterTest {
    @Mock
    private ViewGroup viewGroup;
    private ArticlesAdapter articlesAdapter;
    private MainActivity spyMainActivity;
    @Mock
    private LayoutInflater mockLayoutInflater;
    @Mock
    private View mockInflatedView;

    private ArticlesFragment spyArticlesFragment;
    @Mock
    private Context context;
    @Mock
    private FragmentManager fragmentManager;
    @Mock
    Response response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        spyArticlesFragment = spy(ArticlesFragment.class);
        spyMainActivity = spy(new MainActivity());
    }
}
