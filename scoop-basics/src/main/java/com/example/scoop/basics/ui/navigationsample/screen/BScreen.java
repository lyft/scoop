package com.example.scoop.basics.ui.navigationsample.screen;

import com.example.scoop.basics.ui.navigationsample.controller.BController;
import com.example.scoop.basics.ui.navigationsample.module.BModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.transitions.EnterTransition;
import com.lyft.scoop.transitions.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.basic.BackwardSlideTransition;
import com.lyft.scoop.transitions.basic.ForwardSlideTransition;

@Controller(BController.class)
@DaggerModule(BModule.class)
@EnterTransition(ForwardSlideTransition.class)
@ExitTransition(BackwardSlideTransition.class)
public class BScreen extends Screen{
}
