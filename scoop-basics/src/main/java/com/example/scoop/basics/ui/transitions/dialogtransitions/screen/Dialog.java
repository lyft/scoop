package com.example.scoop.basics.ui.transitions.dialogtransitions.screen;

import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideDownTransition;
import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideUpTransition;
import com.example.scoop.basics.ui.transitions.dialogtransitions.controller.DialogController;
import com.example.scoop.basics.ui.transitions.dialogtransitions.module.DialogModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(DialogController.class)
@DaggerModule(DialogModule.class)
@EnterTransition(SlideDownTransition.class)
@ExitTransition(SlideUpTransition.class)
public class Dialog extends Screen {
}
