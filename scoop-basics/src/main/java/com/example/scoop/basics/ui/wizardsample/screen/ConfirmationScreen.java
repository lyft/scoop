package com.example.scoop.basics.ui.wizardsample.screen;

import com.example.scoop.basics.ui.wizardsample.controller.ConfirmationController;
import com.example.scoop.basics.ui.wizardsample.module.ConfirmationModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(ConfirmationController.class)
@DaggerModule(ConfirmationModule.class)
public class ConfirmationScreen extends Screen {
}
