package com.example.scoop.basics.ui.transitions.standardtransitions.screen;

import com.example.scoop.basics.ui.transitions.standardtransitions.controller.VerticalSlideController;
import com.example.scoop.basics.ui.transitions.standardtransitions.module.VerticalSlideModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.DownwardSlideTransition;
import com.lyft.scoop.transitions.UpwardSlideTransition;

@Controller(VerticalSlideController.class)
@DaggerModule(VerticalSlideModule.class)
@EnterTransition(UpwardSlideTransition.class)
@ExitTransition(DownwardSlideTransition.class)
public class VerticalSlideScreen extends Screen{
}
