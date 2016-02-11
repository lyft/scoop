package com.example.scoop.basics.ui.paramsample.module;

import com.example.scoop.basics.ui.DemosModule;
import com.example.scoop.basics.ui.paramsample.controller.ParametrizedController;
import dagger.Module;

@Module(
        injects = ParametrizedController.class,
        addsTo = DemosModule.class,
        library = true
)
public class ParametrizedModule {
}
