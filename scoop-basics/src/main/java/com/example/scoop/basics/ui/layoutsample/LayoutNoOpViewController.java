package com.example.scoop.basics.ui.layoutsample;

import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.example.scoop.basics.ui.DemosController;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

@ControllerModule(LayoutNoOpViewController.Module.class)
public class LayoutNoOpViewController extends ViewController{

    @dagger.Module(
            injects = LayoutNoOpViewController.class,
            addsTo = DemosController.Module.class,
            library = true
    )
    public static class Module {}
    private AppRouter appRouter;

    @Inject
    public LayoutNoOpViewController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return 0;
    }
}
