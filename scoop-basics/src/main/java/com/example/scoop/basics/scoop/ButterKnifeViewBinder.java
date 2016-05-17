package com.example.scoop.basics.scoop;

import android.view.View;

import com.lyft.scoop.ViewBinder;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ButterKnifeViewBinder implements ViewBinder {

    private Unbinder unbinder;

    @Override
    public void bind(Object object, View view) {
        unbinder = ButterKnife.bind(object, view);
    }

    @Override
    public void unbind(Object object) {
        unbinder.unbind();
    }
}
