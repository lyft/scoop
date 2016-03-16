package com.example.scoop.basics.ui.transitions;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.DialogRouter;
import com.example.scoop.basics.ui.transitions.customtransition.screen.AutoTransitionStartScreen;
import com.example.scoop.basics.ui.transitions.dialogtransitions.screen.Dialog;
import com.example.scoop.basics.ui.transitions.dialogtransitions.screen.DialogDisableOnBack;
import com.example.scoop.basics.ui.transitions.standardtransitions.screen.FadeScreen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class TransitionsController extends ViewController {

    private AppRouter appRouter;
    private DialogRouter dialogRouter;

    ViewSubscriptions viewSubscriptions = new ViewSubscriptions();

    @Inject
    public TransitionsController(AppRouter appRouter, DialogRouter dialogRouter) {
        this.appRouter = appRouter;
        this.dialogRouter = dialogRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.transitions;
    }

    @Override
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        viewSubscriptions.unsubscribe();
    }

    @OnClick(R.id.custom_transition_button)
    public void goToCustomTransitionSample() {
        appRouter.goTo(new AutoTransitionStartScreen());
    }

    @OnClick(R.id.standard_transitions_button)
    public void goToStandardTransitions() {
        appRouter.goTo(new FadeScreen());
    }

    @OnClick(R.id.dialog_button)
    public void openDialogTransition() {
        dialogRouter.show(new Dialog());
    }

    @OnClick(R.id.dialog_on_back_override_button)
    public void openDialogOnBackOverride() {
        dialogRouter.show(new DialogDisableOnBack());
    }
}
