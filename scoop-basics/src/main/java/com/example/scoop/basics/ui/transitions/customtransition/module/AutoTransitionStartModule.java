package com.example.scoop.basics.ui.transitions.customtransition.module;

import com.example.scoop.basics.ui.DemosModule;
import com.example.scoop.basics.ui.transitions.customtransition.controller.AutoTransitionStartController;
import dagger.Module;

@Module(
        injects = AutoTransitionStartController.class,
        addsTo = DemosModule.class,
        library = true
)
public class AutoTransitionStartModule {
}
