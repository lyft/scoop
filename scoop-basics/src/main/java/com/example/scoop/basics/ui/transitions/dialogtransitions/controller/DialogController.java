package com.example.scoop.basics.ui.transitions.dialogtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.lyft.scoop.ViewController;

public class DialogController extends ViewController {

    private final AppRouter appRouter;

    public DialogController(AppRouter appRouter) {
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

    @OnClick(R.id.dialog_container)
    public void onTapOutside() {
        appRouter.goBack();
    }
}
