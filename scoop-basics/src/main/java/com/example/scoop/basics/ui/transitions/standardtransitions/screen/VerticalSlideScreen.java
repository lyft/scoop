package com.example.scoop.basics.ui.transitions.standardtransitions.screen;

import com.example.scoop.basics.ui.transitions.standardtransitions.controller.VerticalSlideController;
import com.example.scoop.basics.ui.transitions.standardtransitions.module.VerticalSlideModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.transitions.EnterTransition;
import com.lyft.scoop.transitions.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.basic.DownwardSlideTransition;
import com.lyft.scoop.transitions.basic.UpwardSlideTransition;

@Controller(VerticalSlideController.class)
@DaggerModule(VerticalSlideModule.class)
@EnterTransition(UpwardSlideTransition.class)
@ExitTransition(DownwardSlideTransition.class)
public class VerticalSlideScreen extends Screen{
}
