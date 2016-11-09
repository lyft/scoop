package com.example.scoop.basics.ui.transitions.dialogtransitions.screen;

import com.example.scoop.basics.ui.transitions.dialogtransitions.controller.DialogDisableOnBackController;
import com.example.scoop.basics.ui.transitions.dialogtransitions.module.DialogModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(DialogDisableOnBackController.class)
@DaggerModule(DialogModule.class)
public class DialogDisableOnBack extends Screen {
}
