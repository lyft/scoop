package com.example.scoop.basics.ui;

import com.example.scoop.basics.MainActivityModule;
import com.example.scoop.basics.ui.transitions.TransitionsController;

@dagger.Module(
        injects = {
                DemosController.class,
                TransitionsController.class
        },
        addsTo = MainActivityModule.class,
        library = true
)
public class DemosModule {
}
