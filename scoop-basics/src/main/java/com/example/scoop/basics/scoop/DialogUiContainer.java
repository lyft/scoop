package com.example.scoop.basics.scoop;

import android.content.Context;
import android.util.AttributeSet;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.ui.Keyboard;
import com.example.scoop.basics.ui.transitions.dialogtransitions.screen.Dialog;
import com.lyft.scoop.LayoutInflater;
import com.lyft.scoop.RouteChange;
import com.lyft.scoop.ScreenScooper;
import com.lyft.scoop.UiContainer;
import com.lyft.scoop.ViewControllerInflater;
import com.lyft.scoop.dagger.DaggerInjector;
import com.lyft.scoop.dagger.DaggerLayoutInflater;
import com.lyft.scoop.dagger.DaggerViewControllerInflater;
import javax.inject.Inject;
import rx.functions.Action1;

public class DialogUiContainer extends UiContainer {

    @Inject
    DialogRouter dialogRouter;

    @Inject
    ScreenScooper screenScooper;

    private ViewSubscriptions subscriptions = new ViewSubscriptions();

    public DialogUiContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        }

        DaggerInjector.fromView(this).inject(this);
    }

    @Override
    protected ViewControllerInflater getViewControllerInflater() {
        return new DaggerViewControllerInflater();
    }

    @Override
    protected LayoutInflater getLayoutInflater() {
        return new DaggerLayoutInflater();
    }

    @Override
    protected ScreenScooper getScreenScooper() {
        return screenScooper;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (isInEditMode()) {
            return;
        }

        dialogRouter.show(new Dialog());
        dialogRouter.dismiss();
        subscriptions.add(dialogRouter.observeDialogChange(), onDialogChanged);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        subscriptions.unsubscribe();
    }

    private Action1<RouteChange> onDialogChanged = new Action1<RouteChange>() {
        @Override
        public void call(RouteChange routeChange) {
            goTo(routeChange);

            Keyboard.hideKeyboard(DialogUiContainer.this);
        }
    };
}
