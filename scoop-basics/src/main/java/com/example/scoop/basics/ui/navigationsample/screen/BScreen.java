package com.example.scoop.basics.ui.navigationsample.screen;

import com.example.scoop.basics.ui.navigationsample.controller.BController;
import com.example.scoop.basics.ui.navigationsample.module.BModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(BController.class)
@DaggerModule(BModule.class)
public class BScreen extends Screen {
}
