package com.example.scoop.basics.ui.layoutsample.screen;

import com.example.scoop.basics.ui.layoutsample.layout.NestedLayoutViewController;
import com.example.scoop.basics.ui.layoutsample.module.NestedLayoutModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.transitions.EnterTransition;
import com.lyft.scoop.transitions.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.basic.FadeTransition;

@Controller(NestedLayoutViewController.class)
@DaggerModule(NestedLayoutModule.class)
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class NestedLayoutScreen extends Screen {
}
