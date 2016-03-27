package com.example.scoop.basics.ui.wizardsample.screen;

import com.example.scoop.basics.ui.wizardsample.controller.EnterLastNameController;
import com.example.scoop.basics.ui.wizardsample.module.EnterLastNameModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.transitions.EnterTransition;
import com.lyft.scoop.transitions.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.basic.BackwardSlideTransition;
import com.lyft.scoop.transitions.basic.ForwardSlideTransition;

@Controller(EnterLastNameController.class)
@DaggerModule(EnterLastNameModule.class)
@EnterTransition(ForwardSlideTransition.class)
@ExitTransition(BackwardSlideTransition.class)
public class EnterLastNameScreen extends Screen{
}
