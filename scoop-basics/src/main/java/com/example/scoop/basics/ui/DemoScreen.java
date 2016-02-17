package com.example.scoop.basics.ui;

import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(DemosController.class)
@DaggerModule(DemosModule.class)
public class DemoScreen extends Screen {
}
