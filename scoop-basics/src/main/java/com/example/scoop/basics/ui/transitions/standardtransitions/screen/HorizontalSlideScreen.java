package com.example.scoop.basics.ui.transitions.standardtransitions.screen;

import com.example.scoop.basics.ui.transitions.standardtransitions.controller.HorizontalSlideController;
import com.example.scoop.basics.ui.transitions.standardtransitions.module.HorizontalSlideModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.transitions.EnterTransition;
import com.lyft.scoop.transitions.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;
import com.lyft.scoop.transitions.basic.BackwardSlideTransition;
import com.lyft.scoop.transitions.basic.ForwardSlideTransition;

@Controller(HorizontalSlideController.class)
@DaggerModule(HorizontalSlideModule.class)
@EnterTransition(ForwardSlideTransition.class)
@ExitTransition(BackwardSlideTransition.class)
public class HorizontalSlideScreen extends Screen{
}
