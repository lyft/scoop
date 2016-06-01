package com.example.scoop.basics.ui.transitions.dialogtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.lyft.scoop.HandleBack;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class DialogDisableOnBackController extends ViewController implements HandleBack {

    private AppRouter appRouter;

    @Inject
    public DialogDisableOnBackController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.dialog;
    }

    @OnClick(R.id.dismiss_button)
    public void goToB() {
        appRouter.goBack();
    }

    @Override
    public boolean onBack() {
        //Disable back press on this view
        return true;
    }
}
