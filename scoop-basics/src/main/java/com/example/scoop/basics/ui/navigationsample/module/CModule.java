package com.example.scoop.basics.ui.navigationsample.module;

import com.example.scoop.basics.ui.navigationsample.controller.CController;
import dagger.Module;

@Module(
        injects = CController.class,
        addsTo = BModule.class,
        library = true
)
public class CModule {
}
