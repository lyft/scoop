package com.example.scoop.basics.scoop;

import com.lyft.scoop.Scoop;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.ViewControllerInflater;

public class DaggerViewControllerInflater extends ViewControllerInflater {

    @Override
    protected ViewController createViewController(Scoop scoop, Class<? extends ViewController> clazz) {

        DaggerInjector injector = DaggerInjector.fromScoop(scoop);

        return injector.get(clazz);
    }
}
