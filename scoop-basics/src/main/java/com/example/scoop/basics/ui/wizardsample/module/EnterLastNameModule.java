package com.example.scoop.basics.ui.wizardsample.module;

import com.example.scoop.basics.ui.wizardsample.controller.EnterLastNameController;
import dagger.Module;

@Module(
        injects = EnterLastNameController.class,
        addsTo = EnterFirstNameModule.class,
        library = true
)
public class EnterLastNameModule {
}