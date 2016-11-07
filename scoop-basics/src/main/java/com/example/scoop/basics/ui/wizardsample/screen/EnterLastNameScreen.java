package com.example.scoop.basics.ui.wizardsample.screen;

import com.example.scoop.basics.ui.wizardsample.controller.EnterLastNameController;
import com.example.scoop.basics.ui.wizardsample.module.EnterLastNameModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(EnterLastNameController.class)
@DaggerModule(EnterLastNameModule.class)
public class EnterLastNameScreen extends Screen {
}
