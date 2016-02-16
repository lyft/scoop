package com.example.scoop.basics.ui.standardtransitions.module;

import com.example.scoop.basics.ui.standardtransitions.controller.VerticalSlideController;
import dagger.Module;

@Module(
        injects = VerticalSlideController.class,
        addsTo = HorizontalSlideModule.class,
        library = true
)
public class VerticalSlideModule {
}
