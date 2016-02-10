package com.example.scoop.basics.scoop;

import android.content.Context;
import android.util.AttributeSet;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.ui.Keyboard;
import com.lyft.scoop.RouteChange;
import com.lyft.scoop.UiContainer;
import com.lyft.scoop.ScoopInflater;
import javax.inject.Inject;
import rx.functions.Action1;
import timber.log.Timber;

public class MainUiContainer extends UiContainer {

    @Inject
    AppRouter appRouter;

    private ViewSubscriptions subscriptions = new ViewSubscriptions();

    public MainUiContainer(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        }

        DaggerInjector.fromView(this).inject(this);
    }

    @Override
    protected ScoopInflater getViewControllerInflater() {
        return new DaggerScoopInflater();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (isInEditMode()) {
            return;
        }

        subscriptions.add(appRouter.observeScreenChange(), onScreenChanged);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        subscriptions.unsubscribe();
    }

    private Action1<RouteChange> onScreenChanged = new Action1<RouteChange>() {
        @Override
        public void call(RouteChange screenChange) {
            Timber.d("Scoop changed:" + screenChange.next.getName());
            MainUiContainer.this.goTo(screenChange);
            Keyboard.hideKeyboard(MainUiContainer.this);
        }
    };
}
