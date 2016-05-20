package com.example.scoop.basics.scoop;

import android.view.View;

import com.lyft.scoop.ViewBinder;

import java.util.WeakHashMap;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ButterKnifeViewBinder implements ViewBinder {

    private WeakHashMap<Object, Unbinder> unbinders = new WeakHashMap<>();

    @Override
    public void bind(Object object, View view) {
        unbinders.put(object, ButterKnife.bind(object, view));
    }

    @Override
    public void unbind(Object object) {
        Unbinder unbinder = unbinders.get(object);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
