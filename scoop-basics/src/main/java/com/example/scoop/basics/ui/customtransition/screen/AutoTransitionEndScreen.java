package com.example.scoop.basics.ui.customtransition.screen;

import com.example.scoop.basics.ui.customtransition.AutoTransition;
import com.example.scoop.basics.ui.customtransition.controller.AutoTransitionEndController;
import com.example.scoop.basics.ui.customtransition.module.AutoTransitionEndModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(AutoTransitionEndController.class)
@DaggerModule(AutoTransitionEndModule.class)
@EnterTransition(AutoTransition.class)
@ExitTransition(AutoTransition.class)
public class AutoTransitionEndScreen extends Screen {
}
