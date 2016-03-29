package com.example.scoop.basics.scoop;

import com.lyft.scoop.Router;
import com.lyft.scoop.RouteChange;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ScreenScooper;
import com.lyft.scoop.TransitionDirection;
import java.util.List;
import rx.Observable;
import rx.subjects.BehaviorSubject;

public class AppRouter extends Router {

    private final BehaviorSubject<RouteChange> screenChangeSubject = BehaviorSubject.create();

    public AppRouter(ScreenScooper screenScooper, boolean hasEmptyStack) {
        super(screenScooper, hasEmptyStack);
    }

    public Observable<RouteChange> observeScreenChange() {
        return screenChangeSubject.asObservable();
    }

    @Override
    protected void onScoopChanged(RouteChange change) {
        screenChangeSubject.onNext(change);
    }
}
