package com.example.scoop.basics.rx;

import com.lyft.scoop.Screen;
import com.lyft.scoop.ScreenResult;
import com.lyft.scoop.ViewController;
import rx.Observable;

public abstract class RxViewController extends ViewController {

    protected <T extends ScreenResult> Observable<T> observePreviousResult(Class<T> screenResult) {
        ScreenResult previousResult = Screen.fromController(this).getPreviousScreenResult();

        if (previousResult != null && screenResult.isInstance(previousResult)) {
            return (Observable<T>) Observable.just(previousResult);
        } else {
            return Observable.empty();
        }
    }
}
