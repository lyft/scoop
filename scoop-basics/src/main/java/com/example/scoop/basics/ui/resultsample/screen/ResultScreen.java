package com.example.scoop.basics.ui.resultsample.screen;

import com.example.scoop.basics.ui.resultsample.controller.ResultViewController;
import com.example.scoop.basics.ui.resultsample.module.ResultModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@DaggerModule(ResultModule.class)
@Controller(ResultViewController.class)
public class ResultScreen extends Screen {
}
