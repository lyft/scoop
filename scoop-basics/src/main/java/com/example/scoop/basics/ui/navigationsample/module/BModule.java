package com.example.scoop.basics.ui.navigationsample.module;

import com.example.scoop.basics.ui.navigationsample.controller.BController;
import dagger.Module;

@Module(
        injects = BController.class,
        addsTo = AModule.class,
        library = true
)
public class BModule {
}
