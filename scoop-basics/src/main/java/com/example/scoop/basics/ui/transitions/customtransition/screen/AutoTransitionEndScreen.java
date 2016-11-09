package com.example.scoop.basics.ui.transitions.customtransition.screen;

import com.example.scoop.basics.ui.transitions.customtransition.controller.AutoTransitionEndController;
import com.example.scoop.basics.ui.transitions.customtransition.module.AutoTransitionEndModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(AutoTransitionEndController.class)
@DaggerModule(AutoTransitionEndModule.class)
public class AutoTransitionEndScreen extends Screen {
}
