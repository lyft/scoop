package com.example.scoop.basics.ui.layoutsample.module;

import com.example.scoop.basics.ui.DemosModule;
import com.example.scoop.basics.ui.layoutsample.layout.NestedLayoutViewController;
import dagger.Module;

@Module(
        injects = NestedLayoutViewController.class,
        addsTo = DemosModule.class,
        library = true
)
public class NestedLayoutModule {
}
