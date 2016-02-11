package com.example.scoop.basics.ui.standardtransitions.module;

import com.example.scoop.basics.ui.DemosModule;
import com.example.scoop.basics.ui.standardtransitions.controller.FadeController;
import dagger.Module;

@Module(
        injects = FadeController.class,
        addsTo = DemosModule.class,
        library = true
)
public class FadeModule {
}
