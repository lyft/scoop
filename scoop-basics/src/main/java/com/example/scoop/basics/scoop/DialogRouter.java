package com.example.scoop.basics.scoop;

import com.lyft.scoop.RouteChange;
import com.lyft.scoop.Screen;
import rx.Observable;

public class DialogRouter {

    private final AppRouter dialogRouter;

    public DialogRouter(AppRouter router) {
        this.dialogRouter = router;
    }

    public boolean dismiss() {
        return dialogRouter.goBack();
    }

    public void show(Screen screen) {
        dialogRouter.replaceAllWith(screen);
    }

    public Observable<RouteChange> observeDialogChange() {
        return dialogRouter.observeRouteChange();
    }
}
