package com.example.scoop.basics.ui.customtransition.screen;

import com.example.scoop.basics.ui.customtransition.AutoTransition;
import com.example.scoop.basics.ui.customtransition.controller.AutoTransitionStartController;
import com.example.scoop.basics.ui.customtransition.module.AutoTransitionStartModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(AutoTransitionStartController.class)
@DaggerModule(AutoTransitionStartModule.class)
@EnterTransition(AutoTransition.class)
@ExitTransition(AutoTransition.class)
public class AutoTransitionStartScreen extends Screen{
}