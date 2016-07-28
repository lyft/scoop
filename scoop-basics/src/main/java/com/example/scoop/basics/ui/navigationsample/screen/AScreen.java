package com.example.scoop.basics.ui.navigationsample.screen;

import com.example.scoop.basics.ui.navigationsample.controller.AController;
import com.example.scoop.basics.ui.navigationsample.module.AModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.FadeTransition;

@Controller(AController.class)
@DaggerModule(AModule.class)
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class AScreen extends Screen {
}
