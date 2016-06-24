package com.example.scoop.basics.ui.resultsample.module;

import com.example.scoop.basics.ui.DemosModule;
import com.example.scoop.basics.ui.resultsample.controller.ResultViewController;
import dagger.Module;

@Module(
        injects = ResultViewController.class,
        addsTo = DemosModule.class,
        library = true
)
public class ResultModule {
}
