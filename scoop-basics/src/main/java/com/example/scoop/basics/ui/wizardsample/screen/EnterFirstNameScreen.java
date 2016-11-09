package com.example.scoop.basics.ui.wizardsample.screen;

import com.example.scoop.basics.ui.wizardsample.controller.EnterFirstNameController;
import com.example.scoop.basics.ui.wizardsample.module.EnterFirstNameModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(EnterFirstNameController.class)
@DaggerModule(EnterFirstNameModule.class)
public class EnterFirstNameScreen extends Screen {
}
