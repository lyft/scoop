package com.example.scoop.basics.ui.navigationsample.screen;

import com.example.scoop.basics.ui.navigationsample.controller.CController;
import com.example.scoop.basics.ui.navigationsample.module.CModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;

@Controller(CController.class)
@DaggerModule(CModule.class)
@EnterTransition(ForwardSlideTransition.class)
@ExitTransition(BackwardSlideTransition.class)
public class CScreen extends Screen {
}
