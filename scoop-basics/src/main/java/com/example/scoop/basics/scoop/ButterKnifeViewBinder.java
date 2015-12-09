package com.example.scoop.basics.scoop;

import android.view.View;
import butterknife.ButterKnife;
import com.lyft.scoop.ViewBinder;

public class ButterKnifeViewBinder implements ViewBinder {

    @Override
    public void bind(Object object, View view) {
        ButterKnife.bind(object, view);
    }

    @Override
    public void unbind(Object object) {
        ButterKnife.unbind(object);
    }
}
