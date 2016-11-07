package com.example.scoop.basics.ui.paramsample.screen;

import com.example.scoop.basics.ui.paramsample.controller.ParametrizedController;
import com.example.scoop.basics.ui.paramsample.module.ParametrizedModule;
import com.lyft.scoop.Controller;
import com.lyft.scoop.Screen;
import com.lyft.scoop.dagger.DaggerModule;

@Controller(ParametrizedController.class)
@DaggerModule(ParametrizedModule.class)
public class ParametrizedScreen extends Screen {

    private final String args;

    public ParametrizedScreen(String args) {
        this.args = args;
    }

    public String getArgs() {
        return args;
    }
}
