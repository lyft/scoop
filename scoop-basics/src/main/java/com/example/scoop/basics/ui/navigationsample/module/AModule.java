package com.example.scoop.basics.ui.navigationsample.module;

import com.example.scoop.basics.ui.DemosModule;
import com.example.scoop.basics.ui.navigationsample.controller.AController;
import dagger.Module;

@Module(
        injects = AController.class,
        addsTo = DemosModule.class,
        library = true
)
public class AModule {
}
