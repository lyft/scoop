package com.example.scoop.basics.ui.transitions.dialogtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.DialogRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideDownTransition;
import com.example.scoop.basics.ui.transitions.dialogtransitions.SlideUpTransition;
import com.lyft.scoop.HandleBack;
import com.lyft.scoop.ScreenTransition;
import javax.inject.Inject;

public class DialogDisableOnBackController extends BaseViewController implements HandleBack {

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

    @Override
    protected Class<? extends ScreenTransition> enterTransition() {
        return SlideUpTransition.class;
    }

    @Override
    protected Class<? extends ScreenTransition> exitTransition() {
        return SlideDownTransition.class;
    }
}
