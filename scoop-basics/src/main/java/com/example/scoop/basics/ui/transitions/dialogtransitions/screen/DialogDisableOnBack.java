package com.example.scoop.basics.ui.transitions.dialogtransitions.screen;

import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideDownTransition;
import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideUpTransition;
import com.example.scoop.basics.ui.transitions.dialogtransitions.controller.DialogDisableOnBackController;
import com.example.scoop.basics.ui.transitions.dialogtransitions.module.DialogModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.transitions.EnterTransition;
import com.lyft.scoop.transitions.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(DialogDisableOnBackController.class)
@DaggerModule(DialogModule.class)
@EnterTransition(SlideUpTransition.class)
@ExitTransition(SlideDownTransition.class)
public class DialogDisableOnBack extends Screen {
}
