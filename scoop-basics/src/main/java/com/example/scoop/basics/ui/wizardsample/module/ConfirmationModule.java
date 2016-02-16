package com.example.scoop.basics.ui.wizardsample.module;

import com.example.scoop.basics.ui.wizardsample.controller.ConfirmationController;
import dagger.Module;

@Module(
        injects = ConfirmationController.class,
        addsTo = EnterLastNameModule.class,
        library = true
)
public class ConfirmationModule {
}
