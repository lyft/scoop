package com.example.scoop.basics.ui.wizardsample.screen;

import com.example.scoop.basics.ui.wizardsample.controller.ConfirmationController;
import com.example.scoop.basics.ui.wizardsample.module.ConfirmationModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.transitions.EnterTransition;
import com.lyft.scoop.transitions.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.basic.BackwardSlideTransition;
import com.lyft.scoop.transitions.basic.ForwardSlideTransition;

@Controller(ConfirmationController.class)
@DaggerModule(ConfirmationModule.class)
@EnterTransition(ForwardSlideTransition.class)
@ExitTransition(BackwardSlideTransition.class)
public class ConfirmationScreen extends Screen{
}
