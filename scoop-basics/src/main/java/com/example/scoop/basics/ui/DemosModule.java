package com.example.scoop.basics.ui;

import com.example.scoop.basics.MainActivityModule;

@dagger.Module(
        injects = DemosController.class,
        addsTo = MainActivityModule.class,
        library = true
)
public class DemosModule {
}
