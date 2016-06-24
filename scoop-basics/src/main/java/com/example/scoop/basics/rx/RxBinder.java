package com.example.scoop.basics.rx;

import rx.Observable;
import rx.functions.Action1;

public class RxBinder {

    // TODO only implement bind for now to get things working

    public <T> void bind(Observable<T> observable, Action1<T> callback) {
        observable.subscribe(callback);
    }
}
