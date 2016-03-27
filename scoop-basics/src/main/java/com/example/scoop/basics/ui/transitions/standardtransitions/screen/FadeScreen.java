package com.example.scoop.basics.ui.transitions.standardtransitions.screen;

import com.example.scoop.basics.ui.transitions.standardtransitions.controller.FadeController;
import com.example.scoop.basics.ui.transitions.standardtransitions.module.FadeModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.transitions.EnterTransition;
import com.lyft.scoop.transitions.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.basic.FadeTransition;

@Controller(FadeController.class)
@DaggerModule(FadeModule.class)
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class FadeScreen extends Screen{
}
