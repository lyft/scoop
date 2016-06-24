package com.example.scoop.basics.ui.transitions;

import android.widget.Toast;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.RxViewController;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.DialogRouter;
import com.example.scoop.basics.ui.transitions.customtransition.screen.AutoTransitionStartScreen;
import com.example.scoop.basics.ui.transitions.dialogtransitions.screen.Dialog;
import com.example.scoop.basics.ui.transitions.dialogtransitions.screen.DialogDisableOnBack;
import com.example.scoop.basics.ui.transitions.standardtransitions.controller.FadeController;
import com.example.scoop.basics.ui.transitions.standardtransitions.screen.FadeScreen;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;

public class TransitionsController extends RxViewController {

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

        new RxBinder().bind(observePreviousResult(FadeController.FadeResult.class), onFadeScreenResult);
    }

    private Action1<FadeController.FadeResult> onFadeScreenResult = new Action1<FadeController.FadeResult>() {
        @Override
        public void call(FadeController.FadeResult screenResult) {
            Toast.makeText(getView().getContext(),
                    "got result " + screenResult.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

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

    private static class RxBinder {

        <T> void bind(Observable<T> observable, Action1<T> callback) {
            observable.subscribe(callback);
        }
    }
}
