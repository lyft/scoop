package com.example.scoop.basics.ui.standardtransitions.screen;

import com.example.scoop.basics.ui.standardtransitions.controller.FadeController;
import com.example.scoop.basics.ui.standardtransitions.module.FadeModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.FadeTransition;

@Controller(FadeController.class)
@DaggerModule(FadeModule.class)
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class FadeScreen extends Screen{
}
