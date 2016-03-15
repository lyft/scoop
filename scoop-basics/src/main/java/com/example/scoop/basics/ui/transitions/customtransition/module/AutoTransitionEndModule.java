package com.example.scoop.basics.ui.transitions.customtransition.module;

import com.example.scoop.basics.ui.transitions.customtransition.controller.AutoTransitionEndController;
import dagger.Module;

@Module(
        injects = AutoTransitionEndController.class,
        addsTo = AutoTransitionStartModule.class,
        library = true
)
public class AutoTransitionEndModule {
}
