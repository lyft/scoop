package com.example.scoop.basics.ui.transitions.standardtransitions.screen;

import com.example.scoop.basics.ui.transitions.standardtransitions.controller.HorizontalSlideController;
import com.example.scoop.basics.ui.transitions.standardtransitions.module.HorizontalSlideModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(HorizontalSlideController.class)
@DaggerModule(HorizontalSlideModule.class)
public class HorizontalSlideScreen extends Screen {
}
