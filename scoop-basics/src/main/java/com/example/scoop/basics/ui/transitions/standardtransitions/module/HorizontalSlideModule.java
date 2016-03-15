package com.example.scoop.basics.ui.transitions.standardtransitions.module;

import com.example.scoop.basics.ui.transitions.standardtransitions.controller.HorizontalSlideController;
import dagger.Module;

@Module(
        injects = HorizontalSlideController.class,
        addsTo = FadeModule.class,
        library = true
)
public class HorizontalSlideModule {
}
