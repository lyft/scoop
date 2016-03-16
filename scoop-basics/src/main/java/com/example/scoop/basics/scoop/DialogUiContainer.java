package com.example.scoop.basics.scoop;

import android.content.Context;
import android.util.AttributeSet;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.ui.Keyboard;
import com.lyft.scoop.LayoutInflater;
import com.lyft.scoop.RouteChange;
import com.lyft.scoop.UiContainer;
import com.lyft.scoop.ViewControllerInflater;
import com.lyft.scoop.dagger.DaggerInjector;
import com.lyft.scoop.dagger.DaggerLayoutInflater;
import com.lyft.scoop.dagger.DaggerViewControllerInflater;
import javax.inject.Inject;
import rx.functions.Action1;
import timber.log.Timber;

public class DialogUiContainer extends UiContainer {

    @Inject
    DialogRouter dialogRouter;

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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (isInEditMode()) {
            return;
        }

        subscriptions.add(dialogRouter.observeDialogChange(), onDialogChanged);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        subscriptions.unsubscribe();
    }

    private Action1<RouteChange> onDialogChanged = new Action1<RouteChange>() {
        @Override
        public void call(RouteChange screenChange) {
            if (screenChange.next != null) {
                Timber.d("Scoop changed:" + screenChange.next.getClass().getSimpleName());
            }
            DialogUiContainer.this.goTo(screenChange);
            Keyboard.hideKeyboard(DialogUiContainer.this);
        }
    };
}