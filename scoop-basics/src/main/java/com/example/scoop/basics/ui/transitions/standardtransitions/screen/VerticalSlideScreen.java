package com.example.scoop.basics.ui.transitions.standardtransitions.screen;

import com.example.scoop.basics.ui.transitions.standardtransitions.controller.VerticalSlideController;
import com.example.scoop.basics.ui.transitions.standardtransitions.module.VerticalSlideModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(VerticalSlideController.class)
@DaggerModule(VerticalSlideModule.class)
public class VerticalSlideScreen extends Screen {
}
