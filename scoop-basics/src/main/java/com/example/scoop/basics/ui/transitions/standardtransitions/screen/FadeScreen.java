package com.example.scoop.basics.ui.transitions.standardtransitions.screen;

import com.example.scoop.basics.ui.transitions.standardtransitions.controller.FadeController;
import com.example.scoop.basics.ui.transitions.standardtransitions.module.FadeModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(FadeController.class)
@DaggerModule(FadeModule.class)
public class FadeScreen extends Screen {
}
