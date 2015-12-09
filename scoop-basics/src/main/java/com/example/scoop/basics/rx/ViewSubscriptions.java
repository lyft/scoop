package com.example.scoop.basics.rx;

import android.os.Looper;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public final class ViewSubscriptions {

    private final Scheduler observeOnScheduler;
    private final Scheduler subscribeOnScheduler;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    public ViewSubscriptions() {
        this.subscribeOnScheduler = Schedulers.io();
        this.observeOnScheduler = AndroidSchedulers.mainThread();
    }

    public <T> void add(Observable<T> observable, Observer<? super T> observer) {
        if (Looper.myLooper() != Looper.getMainLooper()) { throw new AssertionError("Must be on main thread"); }
        this.subscriptions.add(observable.subscribeOn(this.subscribeOnScheduler)
                .observeOn(this.observeOnScheduler)
                .unsubscribeOn(this.observeOnScheduler)
                .subscribe(observer));
    }

    public <T> void add(Observable<T> observable, Action1<? super T> action) {
        if (Looper.myLooper() != Looper.getMainLooper()) { throw new AssertionError("Must be on main thread"); }
        this.subscriptions.add(observable.subscribeOn(this.subscribeOnScheduler).observeOn(this.observeOnScheduler).subscribe(action));
    }

    public void unsubscribe() {
        this.subscriptions.clear();
        this.subscriptions = new CompositeSubscription();
    }
}