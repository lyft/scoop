package com.example.scoop.basics.ui.standardtransitions.module;

import com.example.scoop.basics.ui.standardtransitions.controller.HorizontalSlideController;
import dagger.Module;

@Module(
        injects = HorizontalSlideController.class,
        addsTo = FadeModule.class,
        library = true
)
public class HorizontalSlideModule {
}
