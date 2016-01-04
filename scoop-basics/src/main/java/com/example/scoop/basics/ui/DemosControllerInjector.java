package com.example.scoop.basics.ui;

import com.example.scoop.basics.MainActivityComponent;
import com.example.scoop.basics.scoop.Dagger;
import com.example.scoop.basics.scoop.Injector;
import com.lyft.scoop.Scoop;
import com.lyft.scoop.ViewController;

public class DemosControllerInjector implements Injector {

    @Override
    public void configureScope(Scoop.Builder builder, Scoop parentScoop) {
        MainActivityComponent dagger = Dagger.fromScoop(parentScoop);

        //builder.service(Dagger.SERVICE_NAME, DaggerAut)
    }

    @Override
    public ViewController getController() {
        return null;
    }

    @dagger.Module
    public static class Module {

    }

    @dagger.Component(modules = Module.class, dependencies = MainActivityComponent.class)
    public interface Component {

        //DemosController getDemosController();
    }
}
