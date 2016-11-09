package com.example.scoop.basics.ui.transitions.customtransition.screen;

import com.example.scoop.basics.ui.transitions.customtransition.controller.AutoTransitionStartController;
import com.example.scoop.basics.ui.transitions.customtransition.module.AutoTransitionStartModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(AutoTransitionStartController.class)
@DaggerModule(AutoTransitionStartModule.class)
public class AutoTransitionStartScreen extends Screen {
}
