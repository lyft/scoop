package com.example.scoop.basics.ui.transitions.dialogtransitions.controller;

import android.graphics.drawable.BitmapDrawable;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.transitions.dialogtransitions.screen.Dialog;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class DialogController extends ViewController {

    private final AppRouter appRouter;

    @Inject
    public DialogController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    public void onAttach() {
        super.onAttach();
        restoreBackground();
    }

    @Override
    public void onDetach() {
        saveBackground();
        super.onDetach();
    }

    private void restoreBackground() {
        Dialog dialog = Screen.fromController(this);
        if (dialog.getBackground() != null) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getView().getResources(), dialog.getBackground());
            getView().setBackground(bitmapDrawable);
        }
    }

    private void saveBackground() {
        Dialog dialog = Screen.fromController(this);
        dialog.setBackground((BitmapDrawable) getView().getBackground());
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
