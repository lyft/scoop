package com.example.scoop.basics.ui.transitions.dialogtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.DialogRouter;
import com.lyft.scoop.HandleBack;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class DialogDisableOnBackController extends ViewController implements HandleBack {

    private DialogRouter dialogRouter;

    @Inject
    public DialogDisableOnBackController(DialogRouter dialogRouter) {
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
    public boolean onBack() {
        //Disable back press on this view
        return true;
    }
}