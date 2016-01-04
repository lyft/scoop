package com.example.scoop.basics;

import dagger.Component;

@Component(modules = { AppModule.class, MainActivityModule.class }, dependencies = AppComponent.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
