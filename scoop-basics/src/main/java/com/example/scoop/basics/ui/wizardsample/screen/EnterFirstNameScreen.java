package com.example.scoop.basics.ui.wizardsample.screen;

import com.example.scoop.basics.ui.wizardsample.controller.EnterFirstNameController;
import com.example.scoop.basics.ui.wizardsample.module.EnterFirstNameModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.FadeTransition;

@Controller(EnterFirstNameController.class)
@DaggerModule(EnterFirstNameModule.class)
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class EnterFirstNameScreen extends Screen{
}
