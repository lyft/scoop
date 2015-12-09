package com.lyft.scoop;

import android.view.View;
import android.view.ViewGroup;

public interface ScreenTransition {

    void transition(ViewGroup root, View from, View to, TransitionListener transitionListener);
}
