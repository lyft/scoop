package com.example.scoop.basics.ui.transitions.standardtransitions.module;

import com.example.scoop.basics.ui.DemosModule;
import com.example.scoop.basics.ui.transitions.standardtransitions.controller.FadeController;
import dagger.Module;

@Module(
        injects = FadeController.class,
        addsTo = DemosModule.class,
        library = true
)
public class FadeModule {
}
