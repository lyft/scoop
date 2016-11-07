package com.example.scoop.basics.ui.layoutsample.screen;

import com.example.scoop.basics.ui.layoutsample.layout.NestedLayoutViewController;
import com.example.scoop.basics.ui.layoutsample.module.NestedLayoutModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(NestedLayoutViewController.class)
@DaggerModule(NestedLayoutModule.class)
public class NestedLayoutScreen extends Screen {
}
