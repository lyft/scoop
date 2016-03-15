package com.example.scoop.basics.ui.transitions.dialogtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.DialogRouter;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class DialogController extends ViewController {

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
}
