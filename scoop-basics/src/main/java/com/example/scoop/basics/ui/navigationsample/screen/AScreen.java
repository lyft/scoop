package com.example.scoop.basics.ui.navigationsample.screen;

import com.example.scoop.basics.ui.navigationsample.controller.AController;
import com.example.scoop.basics.ui.navigationsample.module.AModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(AController.class)
@DaggerModule(AModule.class)
public class AScreen extends Screen {
}
