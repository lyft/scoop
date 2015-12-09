package com.lyft.scoop;

import android.view.View;

public interface ViewBinder {

    void bind(Object object, View view);

    void unbind(Object object);
}
