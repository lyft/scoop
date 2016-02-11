package com.example.scoop.basics.ui;

import com.example.scoop.basics.scoop.DaggerModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;

@Controller(DemosController.class)
@DaggerModule(DemosModule.class)
public class DemoScreen extends Screen {
}
