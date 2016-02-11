package com.example.scoop.basics.ui.customtransition.screen;

import com.example.scoop.basics.scoop.DaggerModule;
import com.example.scoop.basics.ui.customtransition.AutoTransition;
import com.example.scoop.basics.ui.customtransition.controller.AutoTransitionStartController;
import com.example.scoop.basics.ui.customtransition.module.AutoTransitionEndModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;

@Controller(AutoTransitionStartController.class)
@DaggerModule(AutoTransitionEndModule.class)
@EnterTransition(AutoTransition.class)
@ExitTransition(AutoTransition.class)
public class AutoTransitionStartScreen extends Screen{
}