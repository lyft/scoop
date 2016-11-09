package com.example.scoop.basics.ui.transitions.dialogtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.DialogRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideDownTransition;
import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideUpTransition;
import com.lyft.scoop.ScreenTransition;
import javax.inject.Inject;

public class DialogController extends BaseViewController {

    private DialogRouter dialogRouter;

    @Inject
    public DialogController(DialogRouter dialogRouter) {
        this.dialogRouter = dialogRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.dialog;
    }

    @OnClick(R.id.dismiss_button)
    public void goToB() {
        dialogRouter.dismiss();
    }

    @Override
    protected ScreenTransition enterTransition() {
        return new SlideUpTransition();
    }

    @Override
    protected ScreenTransition exitTransition() {
        return new SlideDownTransition();
    }
}
