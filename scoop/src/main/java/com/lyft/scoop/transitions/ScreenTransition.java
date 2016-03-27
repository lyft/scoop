package com.lyft.scoop.transitions;

import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.TransitionListener;

public interface ScreenTransition {

    void transition(ViewGroup root, View from, View to, TransitionListener transitionListener);
}
