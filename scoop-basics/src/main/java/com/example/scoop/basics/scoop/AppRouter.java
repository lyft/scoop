package com.example.scoop.basics.scoop;

import com.jakewharton.rxrelay.BehaviorRelay;
import com.lyft.scoop.RouteChange;
import com.lyft.scoop.Router;
import rx.Observable;

public class AppRouter extends Router {

    private final BehaviorRelay<RouteChange> routeChangeRelay = BehaviorRelay.create();

    public AppRouter(boolean hasEmptyStack) {
        super(hasEmptyStack);
    }

    @Override
    protected void onRouteChanged(RouteChange routeChange) {
        routeChangeRelay.call(routeChange);
    }

    public Observable<RouteChange> observeRouteChange() {
        return routeChangeRelay.asObservable();
    }
}
