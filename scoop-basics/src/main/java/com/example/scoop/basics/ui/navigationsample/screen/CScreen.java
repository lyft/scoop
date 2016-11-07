package com.example.scoop.basics.ui.navigationsample.screen;

import com.example.scoop.basics.ui.navigationsample.controller.CController;
import com.example.scoop.basics.ui.navigationsample.module.CModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(CController.class)
@DaggerModule(CModule.class)
public class CScreen extends Screen {
}
